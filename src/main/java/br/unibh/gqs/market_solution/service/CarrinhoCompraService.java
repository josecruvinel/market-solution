package br.unibh.gqs.market_solution.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unibh.gqs.market_solution.model.CarrinhoCompra;
import br.unibh.gqs.market_solution.model.ItemCompra;
import br.unibh.gqs.market_solution.persistence.CarrinhoCompraRepository;

@Service
public class CarrinhoCompraService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @Autowired
    private final CarrinhoCompraRepository carrinhoCompraReporitory;
    
    public CarrinhoCompraService(CarrinhoCompraRepository carrinhoCompraReporitory){
        this.carrinhoCompraReporitory=carrinhoCompraReporitory;
    }

    public List<CarrinhoCompra> getAll(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<CarrinhoCompra> lista = this.carrinhoCompraReporitory.findAll();
        return IteratorUtils.toList(lista.iterator());
    }    

    public CarrinhoCompra getById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando CarrinhoCompra com o codigo {}",id);
        }
        Optional<CarrinhoCompra> retorno = this.carrinhoCompraReporitory.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("CarrinhoCompra com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public CarrinhoCompra save(CarrinhoCompra carrinhocompra){
        if(logger.isInfoEnabled()){
            logger.info("Salvando CarrinhoCompra com os detalhes {}",carrinhocompra.toString());
        }
        return this.carrinhoCompraReporitory.save(carrinhocompra);
    }
    
    public void delete(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo CarrinhoCompra com id {}",id);
        }
        this.carrinhoCompraReporitory.deleteById(id);
    }

    public boolean exists(Long id){
    	Optional<CarrinhoCompra> retorno = this.carrinhoCompraReporitory.findById(id);
        return retorno.isPresent() ? true:  false;
    }

    public CarrinhoCompra addItemCarrinhoCompra(Long id, ItemCompra itenCompra){
        if(logger.isInfoEnabled()){
            logger.info("Buscando CarrinhoCompra com o codigo {}",id);
        }
        Optional<CarrinhoCompra> retorno = this.carrinhoCompraReporitory.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("CarrinhoCompra com o id "+id+" nao encontrada");
        }
        CarrinhoCompra carrinhoCompra = retorno.get();
        carrinhoCompra.addItemCarrinho(itenCompra);
        return carrinhoCompra;
    }

    public CarrinhoCompra removeItemCarrinhoCompra(Long id, ItemCompra itenCompra){
        if(logger.isInfoEnabled()){
            logger.info("Buscando CarrinhoCompra com o codigo {}",id);
        }
        Optional<CarrinhoCompra> retorno = this.carrinhoCompraReporitory.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("CarrinhoCompra com o id "+id+" nao encontrada");
        }
        CarrinhoCompra carrinhoCompra = retorno.get();
        carrinhoCompra.removeItemCarrinho(itenCompra);
        return carrinhoCompra;
    }

}