package com.moser.moserfood.core.security.authorizationserver;

import com.moser.moserfood.domain.model.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Juliano Moser
 */
@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;

    public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.userId = usuario.getId();
        this.fullName = usuario.getNome();
    }
}
