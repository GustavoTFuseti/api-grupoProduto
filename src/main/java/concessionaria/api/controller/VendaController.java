package concessionaria.api.controller;

import concessionaria.api.entidades.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        List<Venda> vendas = entityManager
                .createQuery("SELECT v FROM Venda v", Venda.class)
                .getResultList();

        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        Venda venda = entityManager.find(Venda.class, id);

        if (venda == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(venda);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Venda> cadastrar(@RequestBody Venda venda) {
        entityManager.persist(venda);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(venda);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Venda> alterar(
            @PathVariable Long id,
            @RequestBody Venda dados) {

        Venda venda = entityManager.find(Venda.class, id);

        if (venda == null) {
            return ResponseEntity.notFound().build();
        }

        venda.setDataVenda(dados.getDataVenda());
        venda.setValorTotal(dados.getValorTotal());
        venda.setCliente(dados.getCliente());
        venda.setVeiculo(dados.getVeiculo());

        return ResponseEntity.ok(venda);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Venda venda = entityManager.find(Venda.class, id);

        if (venda == null) {
            return ResponseEntity.notFound().build();
        }

        entityManager.remove(venda);

        return ResponseEntity.noContent().build();
    }
}