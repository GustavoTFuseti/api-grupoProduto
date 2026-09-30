package concessionaria.api.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grupo_produto")
@Getter
@Setter

public class GrupoProduto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String nome;

        public GrupoProduto() {}
}