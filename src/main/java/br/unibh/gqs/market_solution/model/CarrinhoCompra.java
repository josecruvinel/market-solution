package br.unibh.gqs.market_solution.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CARRINHO_COMPRA")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(targetEntity = ItemCompra.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_compra_id")
    private Set<ItemCompra> itens;

    @Transient
    private BigDecimal total;

    @NotNull
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal desconto;

    @Transient
    private BigDecimal totalComDesconto;
    
    public CarrinhoCompra(){
        this.total = new BigDecimal(0);
        this.desconto = new BigDecimal(0);
        this.totalComDesconto = new BigDecimal(0);
    }

    public CarrinhoCompra(@NotNull Cliente cliente, @NotEmpty Set<ItemCompra> itens, BigDecimal total, BigDecimal desconto) {
        this.cliente = cliente;
        this.itens = itens;
        this.total = this.calculaTotal();
        this.desconto = desconto == null ? new BigDecimal(0): desconto;
        this.totalComDesconto = this.calculaTotalComDesconto();
    }

    public BigDecimal calculaTotal() {
        if (this.itens == null || this.itens.isEmpty()){
            return new BigDecimal(0L);
        } else {
            return itens.stream().map(o -> o.getSubTotal()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        }
    }

    public BigDecimal calculaTotalComDesconto(){
        if (this.total == null){
            return new BigDecimal(0L);
        } else if (this.desconto == null) {
            return this.total;
        } else {
            return this.total.subtract(this.desconto);
        }
    }

    public void addItemCarrinho(ItemCompra item){
        if (this.itens == null){
            this.itens = new HashSet<ItemCompra>();
        }
        if (!this.itens.contains(item)){
            this.itens.add(item);
            this.total = this.calculaTotal();
            this.totalComDesconto = this.calculaTotalComDesconto();
        }
    }
    
    public void removeItemCarrinho(ItemCompra item){
        if (this.itens == null){
            this.itens = new HashSet<ItemCompra>();
        }
        if (!this.itens.contains(item)){
            this.itens.remove(item);
            this.total = this.calculaTotal();
            this.totalComDesconto = this.calculaTotalComDesconto();
        }
    }

    public void setDesconto(@NotNull BigDecimal desconto){
        this.total = this.calculaTotal();
        this.desconto = desconto;
        this.totalComDesconto = this.calculaTotalComDesconto();
    }

    public Set<ItemCompra> getItens() {
        return itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getTotalComDesconto() {
        return totalComDesconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarrinhoCompra [id=" + id + ", cliente=" + cliente + ", itens=" + itens + ", total=" + total
                + ", desconto=" + desconto + ", totalComDesconto=" + totalComDesconto + "]";
    }

    
    

}
