package com.moser.moserfood.domain.listener;

import com.moser.moserfood.domain.event.PedidoConfirmadoEvent;
import com.moser.moserfood.domain.model.Pedido;
import com.moser.moserfood.domain.service.EnvioEmailService;
import com.moser.moserfood.domain.service.EnvioSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.text.DecimalFormat;
import java.util.Collections;

import static com.moser.moserfood.domain.service.EnvioEmailService.MensagemEmail;
import static com.moser.moserfood.domain.service.EnvioSmsService.MensagemSms;

/**
 * @author Juliano Moser
 */
@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmailService;
    @Autowired
    private EnvioSmsService envioSmsService;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        Pedido pedido = event.getPedido();

        envioEmailService.enviar(getMensagemEmail(pedido));
        envioSmsService.sendSms(getMensagemSms(pedido));
    }

    private static MensagemEmail getMensagemEmail(Pedido pedido) {
        var mensagem = MensagemEmail.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("emails/pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatarios(Collections.singleton(pedido.getCliente().getEmail()))
                .build();
        return mensagem;
    }

    private static MensagemSms getMensagemSms(Pedido pedido) {

        var mensagem = pedido.getRestaurante().getNome() + "\n" +
                pedido.getCliente().getNome() +
                " , seu pedido foi " + pedido.getStatus().getDescricao().toLowerCase() + " pelo restaurante e já" +
                " está sendo preparado.\n" +
                "Total de R$ " + new DecimalFormat("#,##0.00").format(pedido.getValorTotal());

        return MensagemSms.builder()
                .mensagem(mensagem)
                .build();
    }
}
