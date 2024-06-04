package br.unibh.gqs.market_solution.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.unibh.gqs.market_solution.model.Produto;
import br.unibh.gqs.market_solution.service.ProdutoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
    Produto getProduto(@PathVariable Long id){
        return  produtoService.getById(id);
    }

    @RequestMapping(value = "/produto", method = RequestMethod.POST)
    Produto addProduto(@RequestBody Produto produto){
        Produto savedProduto = produtoService.save(produto);
        return savedProduto;
    }

    @RequestMapping(value = "/produto", method = RequestMethod.PUT)
    Produto updateProduto(@RequestBody Produto produto){
        Produto updatedProduto = produtoService.save(produto);
        return updatedProduto;
    }

    @RequestMapping(value = "/produto", method = RequestMethod.DELETE)
    Map<String, String> deleteProduto(@RequestParam Long id){
        Map<String, String> status = new HashMap<>();
        if(produtoService.exists(id)) {
            produtoService.delete(id);
            status.put("Status", "Produto deleted successfully");
        }
        else {
            status.put("Status", "Produto not exist");
        }
        return status;
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    List<Produto> getAllProduto(){
        return produtoService.getAll();
    }

}
