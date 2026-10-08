package concessionaria.api.service;

import concessionaria.api.entidades.GrupoProduto;
import concessionaria.api.repository.GrupoProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrupoProdutoService {
    private final GrupoProdutoRepository grupoProdutoRepository;

    public GrupoProduto salvarGrupoProduto(GrupoProduto grupoProduto) {
        return grupoProdutoRepository.save(grupoProduto);
    }
}
