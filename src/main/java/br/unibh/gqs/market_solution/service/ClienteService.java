package br.unibh.gqs.market_solution.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unibh.gqs.market_solution.model.Cliente;
import br.unibh.gqs.market_solution.persistence.ClienteRepository;

@Service
public class ClienteService {
    
    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @Autowired
    private final ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository=clienteRepository;
    }

    public List<Cliente> getAll(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Cliente> lista = this.clienteRepository.findAll();
        return IteratorUtils.toList(lista.iterator());
    }    

    public Cliente getById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Cliente com o codigo {}",id);
        }
        Optional<Cliente> retorno = this.clienteRepository.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Cliente com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Cliente save(Cliente cliente){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Cliente com os detalhes {}",cliente.toString());
        }
        return this.clienteRepository.save(cliente);
    }
    
    public void delete(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Cliente com id {}",id);
        }
        this.clienteRepository.deleteById(id);
    }

    public boolean exists(Long id){
    	Optional<Cliente> retorno = this.clienteRepository.findById(id);
        return retorno.isPresent() ? true:  false;
    }
}
