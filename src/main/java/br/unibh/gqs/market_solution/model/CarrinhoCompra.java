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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CARRINHO_COMPRA")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @OneToMany(targetEntity = ItemCompra.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_compra_id")
    private Set<ItemCompra> itens;

    private BigDecimal total;

    @NotNull
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal desconto;

    private BigDecimal totalComDesconto;
    
    public CarrinhoCompra(){
        this.itens = new HashSet<ItemCompra>();
        this.total = new BigDecimal(0);
        this.desconto = new BigDecimal(0);
        this.totalComDesconto = new BigDecimal(0);
    }

    public CarrinhoCompra(@NotNull Cliente cliente, @NotEmpty Set<ItemCompra> itens, BigDecimal total, BigDecimal desconto) {
        this.cliente = cliente;
        this.itens = itens;
        this.desconto = desconto == null ? new BigDecimal(0): desconto;
        this.calcula();
    }

    public void calculaTotal() {
        if (this.itens == null || this.itens.isEmpty()){
            this.total = new BigDecimal(0L);
        } else {
            this.total = itens.stream().map(o -> o.getSubTotal()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        }
    }

    public void calculaTotalComDesconto(){
        if (this.total == null){
            this.totalComDesconto =  new BigDecimal(0L);
        } else if (this.desconto == null) {
            this.totalComDesconto =  this.total;
        } else {
            this.totalComDesconto =  this.total.subtract(this.desconto);
        }
    }

    public void calcula(){
        this.calculaTotal();
        this.calculaTotalComDesconto();
    }

    public void addItemCarrinho(ItemCompra item){
        if (this.itens == null){
            this.itens = new HashSet<ItemCompra>();
        }
        if (!this.itens.contains(item)){
            this.itens.add(item);
            this.calcula();
        }
    }
    
    public void removeItemCarrinho(ItemCompra item){
        if (this.itens == null){
            this.itens = new HashSet<ItemCompra>();
        }
        if (!this.itens.contains(item)){
            this.itens.remove(item);
            this.calcula();
        }
    }

    public void setDesconto(@NotNull BigDecimal desconto){
        this.desconto = desconto;
        this.calcula();
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
