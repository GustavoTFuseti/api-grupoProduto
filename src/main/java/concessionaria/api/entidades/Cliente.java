package concessionaria.api.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter

public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String nome;

        @Column(unique = true, nullable = false, length = 14)
        private String cpf;

        @Column(length = 20)
        private String telefone;

        public Cliente() {}
}