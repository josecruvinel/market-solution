package br.unibh.gqs.market_solution.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unibh.gqs.market_solution.model.Produto;
import br.unibh.gqs.market_solution.persistence.ProdutoRepository;

@Service
public class ProdutoService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @Autowired
    private final ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository=produtoRepository;
    }

    public List<Produto> getAll(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Produto> lista = this.produtoRepository.findAll();
        return IteratorUtils.toList(lista.iterator());
    }    

    public Produto getById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Produto com o codigo {}",id);
        }
        Optional<Produto> retorno = this.produtoRepository.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Produto com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Produto save(Produto produto){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Produto com os detalhes {}",produto.toString());
        }
        return this.produtoRepository.save(produto);
    }
    
    public void delete(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Produto com id {}",id);
        }
        this.produtoRepository.deleteById(id);
    }

    public boolean exists(Long id){
    	Optional<Produto> retorno = this.produtoRepository.findById(id);
        return retorno.isPresent() ? true:  false;
    }

}
