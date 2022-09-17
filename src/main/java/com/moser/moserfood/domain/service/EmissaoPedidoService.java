package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.exception.NegocioException;
import com.moser.moserfood.domain.exception.PedidoNaoEncontradoException;
import com.moser.moserfood.domain.model.*;
import com.moser.moserfood.domain.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Juliano Moser
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmissaoPedidoService {

    private final PedidoRepository pedidoRepository;

    private final RestauranteService restauranteService;

    private final UsuarioService usuarioService;

    private final ProdutoService produtoService;

    private final FormaPagamentoService formaPagamentoService;

    private final CidadeService cidadeService;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.definirFrete();
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cidadeService.findOrFail(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = usuarioService.findOrFail(pedido.getCliente().getId());
        Restaurante restaurante = restauranteService.findOrFail(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.findOrFail(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoaceitaFormPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    public void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.findOrFail(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    public Pedido findOrFail(String condigoPedido) {
        return pedidoRepository.findByCodigo(condigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(condigoPedido));
    }
}
