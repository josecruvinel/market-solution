package br.unibh.gqs.market_solution.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unibh.gqs.market_solution.model.CarrinhoCompra;

public interface CarrinhoCompraRepository extends JpaRepository<CarrinhoCompra, Long> {

}
