package com.moser.moserfood.infrastructure.service.sms;


import com.moser.moserfood.core.sms.SmsProperties;
import com.moser.moserfood.domain.service.EnvioSmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Juliano Moser
 */
@Slf4j
@Service
public class TwilioSmsEnvioService implements EnvioSmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void sendSms(MensagemSms mensagem) {

        try {
            Twilio.init(smsProperties.getTwilio().getSid(), smsProperties.getTwilio().getKey());

            PhoneNumber to = new PhoneNumber(smsProperties.getTwilio().getPhoneTo());
            PhoneNumber from = new PhoneNumber(smsProperties.getTwilio().getPhoneFrom());

            Message message = Message.creator(to, from, mensagem.getMensagem()).create();

            log.info("Sid {}", message.getSid());
        } catch (Exception e) {
            throw new SmslException("Não foi possível enviar sms", e);
        }
    }
}
