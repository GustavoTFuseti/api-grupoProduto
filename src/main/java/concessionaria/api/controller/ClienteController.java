package concessionaria.api.controller;

import concessionaria.api.entidades.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = entityManager
                .createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        entityManager.persist(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cliente);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Cliente> alterar(
            @PathVariable Long id,
            @RequestBody Cliente dados) {

        Cliente cliente = entityManager.find(Cliente.class, id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        cliente.setNome(dados.getNome());
        cliente.setCpf(dados.getCpf());
        cliente.setTelefone(dados.getTelefone());

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Cliente cliente = entityManager.find(Cliente.class, id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        entityManager.remove(cliente);

        return ResponseEntity.noContent().build();
    }
}