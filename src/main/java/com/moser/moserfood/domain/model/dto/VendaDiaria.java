package com.moser.moserfood.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class VendaDiaria {

    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    public VendaDiaria(java.sql.Date data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = new Date(data.getTime());
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }
}
