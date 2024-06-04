package br.unibh.gqs.market_solution.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.unibh.gqs.market_solution.model.Cliente;
import br.unibh.gqs.market_solution.service.ClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
 
    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    Cliente getCliente(@PathVariable Long id){
        return  clienteService.getById(id);
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    Cliente addCliente(@RequestBody Cliente cliente){
        Cliente savedCliente = clienteService.save(cliente);
        return savedCliente;
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.PUT)
    Cliente updateCliente(@RequestBody Cliente cliente){
        Cliente updatedCliente = clienteService.save(cliente);
        return updatedCliente;
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.DELETE)
    Map<String, String> deleteCliente(@RequestParam Long id){
        Map<String, String> status = new HashMap<>();
        if(clienteService.exists(id)) {
            clienteService.delete(id);
            status.put("Status", "Cliente deleted successfully");
        }
        else {
            status.put("Status", "Cliente not exist");
        }
        return status;
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    List<Cliente> getAllCliente(){
        return clienteService.getAll();
    }
}
