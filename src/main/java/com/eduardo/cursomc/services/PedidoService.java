package com.eduardo.cursomc.services;

import com.eduardo.cursomc.domain.OrderedItem;
import com.eduardo.cursomc.domain.PaymentWithBankSlip;
import com.eduardo.cursomc.domain.Order;
import com.eduardo.cursomc.domain.enums.PaymentStatus;
import com.eduardo.cursomc.domain.repositories.ItemPedidoRepository;
import com.eduardo.cursomc.domain.repositories.PagamentoRepository;
import com.eduardo.cursomc.domain.repositories.PedidoRepository;
import com.eduardo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EmailService emailService;

    public Order find(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
    }

    public Order insert(Order obj) {
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setClient(clienteService.find(obj.getClient().getId()));
        obj.getPayment().setState(PaymentStatus.PENDING);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentWithBankSlip) {
            PaymentWithBankSlip pagto = (PaymentWithBankSlip) obj.getPayment();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPayment());
        for (OrderedItem ip : obj.getItems()) {
            ip.setDiscount(0.0);
            ip.setProduct(produtoService.find(ip.getProduct().getId()));
            ip.setPrice(ip.getProduct().getPrice());
            ip.setOrder(obj);
        }
        itemPedidoRepository.saveAll(obj.getItems());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }
}
