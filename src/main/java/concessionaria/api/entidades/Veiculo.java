package concessionaria.api.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter

public class Veiculo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 50)
        private String marca;

        @Column(nullable = false, length = 100)
        private String modelo;

        @Column(nullable = false)
        private Integer ano;

        @Column(nullable = false)
        private Double preco;

        @ManyToOne
        @JoinColumn(name = "grupo_id")
        private GrupoProduto grupo;

        public Veiculo() {}
}