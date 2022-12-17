package com.moser.moserfood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @author Juliano Moser
 */
public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedContentTypes;

    @Override
    public void initialize(FileContentType constraint) {
        this.allowedContentTypes = Arrays.asList(constraint.allowed());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile == null
                || this.allowedContentTypes.contains(multipartFile.getContentType());
    }

}
