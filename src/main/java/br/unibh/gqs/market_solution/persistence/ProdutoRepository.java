package br.unibh.gqs.market_solution.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unibh.gqs.market_solution.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findByDescricaoStartsWith(String descricao);    

}
