package br.unibh.gqs.market_solution.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unibh.gqs.market_solution.model.ItemCompra;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long>{
}
