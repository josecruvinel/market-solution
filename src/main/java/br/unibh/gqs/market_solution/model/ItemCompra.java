package br.unibh.gqs.market_solution.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "TB_ITEM_COMPRA")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Positive
    private int quantidade;

    private BigDecimal subTotal;

    public ItemCompra(@NotEmpty Produto produto, @Positive int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = this.calculaSubTotal();
    }

    public void setProdutoQualidade(@NotEmpty Produto produto, @Positive int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = this.calculaSubTotal();
    }

    public BigDecimal calculaSubTotal() {
        if (this.produto == null || this.quantidade == 0){
            return new BigDecimal(0L);
        } else {
            return this.produto.getPreco().multiply(new BigDecimal(this.quantidade));
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItemCompra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", subTotal=" + subTotal
                + "]";
    }    

    

}
