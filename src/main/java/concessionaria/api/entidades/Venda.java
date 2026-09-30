package concessionaria.api.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "vendas")
@Getter
@Setter

public class Venda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private LocalDate dataVenda;

        @Column(nullable = false)
        private Double valorTotal;

        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "veiculo_id")
        private Veiculo veiculo;

        public Venda() {}
}