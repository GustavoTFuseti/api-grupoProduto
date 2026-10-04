package controller;

import concessionaria.api.entidades.GrupoProduto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo-produto")
public class GrupoProdutoController {

    @PersistenceContext
    private EntityManager entityManager;



    // LISTAR TODOS OS GRUPOS DE PRODUTO

    @GetMapping
    public ResponseEntity<List<GrupoProduto>> listar() {

        List<GrupoProduto> grupos = entityManager
                .createQuery(
                        "SELECT g FROM GrupoProduto g",
                        GrupoProduto.class
                )
                .getResultList();

        return ResponseEntity.ok(grupos);
    }



    // BUSCAR GRUPO DE PRODUTO PELO ID

    @GetMapping("/{id}")
    public ResponseEntity<GrupoProduto> buscarPorId(
            @PathVariable Long id) {

        GrupoProduto grupo = entityManager.find(
                GrupoProduto.class,
                id
        );

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(grupo);
    }



    // CADASTRAR NOVO GRUPO DE PRODUTO

    @PostMapping
    @Transactional
    public ResponseEntity<GrupoProduto> cadastrar(
            @RequestBody GrupoProduto grupo) {

        entityManager.persist(grupo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(grupo);
    }



    // ALTERAR GRUPO DE PRODUTO

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GrupoProduto> alterar(
            @PathVariable Long id,
            @RequestBody GrupoProduto dados) {

        GrupoProduto grupo = entityManager.find(
                GrupoProduto.class,
                id
        );

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        grupo.setNome(dados.getNome());

        return ResponseEntity.ok(grupo);
    }



    // EXCLUIR GRUPO DE PRODUTO

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        GrupoProduto grupo = entityManager.find(
                GrupoProduto.class,
                id
        );

        if (grupo == null) {
            return ResponseEntity.notFound().build();
        }

        entityManager.remove(grupo);

        return ResponseEntity.noContent().build();
    }
}