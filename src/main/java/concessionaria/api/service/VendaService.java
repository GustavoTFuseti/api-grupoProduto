package concessionaria.api.service;

import concessionaria.api.entidades.Venda;
import concessionaria.api.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;

    public Venda salvarVenda(Venda venda) {
        return vendaRepository.save (venda);
    }
}
