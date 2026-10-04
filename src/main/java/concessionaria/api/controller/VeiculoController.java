package concessionaria.api.controller;

import concessionaria.api.entidades.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {
        List<Veiculo> veiculos = entityManager
                .createQuery("SELECT v FROM Veiculo v", Veiculo.class)
                .getResultList();

        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        Veiculo veiculo = entityManager.find(Veiculo.class, id);

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        entityManager.persist(veiculo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veiculo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Veiculo> alterar(
            @PathVariable Long id,
            @RequestBody Veiculo dados) {

        Veiculo veiculo = entityManager.find(Veiculo.class, id);

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        veiculo.setMarca(dados.getMarca());
        veiculo.setModelo(dados.getModelo());
        veiculo.setAno(dados.getAno());
        veiculo.setPreco(dados.getPreco());
        veiculo.setGrupo(dados.getGrupo());

        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Veiculo veiculo = entityManager.find(Veiculo.class, id);

        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        entityManager.remove(veiculo);

        return ResponseEntity.noContent().build();
    }
}