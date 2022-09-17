package com.moser.moserfood.domain.service;

import com.moser.moserfood.domain.filter.VendaDiariaFilter;
import com.moser.moserfood.domain.model.dto.VendaDiaria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Service
public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
