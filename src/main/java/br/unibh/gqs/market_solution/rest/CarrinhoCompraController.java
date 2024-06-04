package br.unibh.gqs.market_solution.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.unibh.gqs.market_solution.model.CarrinhoCompra;
import br.unibh.gqs.market_solution.model.ItemCompra;
import br.unibh.gqs.market_solution.service.CarrinhoCompraService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CarrinhoCompraController {
 
    @Autowired
    private CarrinhoCompraService carrinhoCompraService;

    @RequestMapping(value = "/carrinhocompra/{id}", method = RequestMethod.GET)
    CarrinhoCompra getCarrinhoCompra(@PathVariable Long id){
        return  carrinhoCompraService.getById(id);
    }

    @RequestMapping(value = "/carrinhocompra", method = RequestMethod.POST)
    CarrinhoCompra addCarrinhoCompra(@RequestBody CarrinhoCompra carrinhocompra){
        CarrinhoCompra savedCarrinhoCompra = carrinhoCompraService.save(carrinhocompra);
        return savedCarrinhoCompra;
    }

    @RequestMapping(value = "/carrinhocompra", method = RequestMethod.PUT)
    CarrinhoCompra updateCarrinhoCompra(@RequestBody CarrinhoCompra carrinhocompra){
        CarrinhoCompra updatedCarrinhoCompra = carrinhoCompraService.save(carrinhocompra);
        return updatedCarrinhoCompra;
    }

    @RequestMapping(value = "/carrinhocompra", method = RequestMethod.DELETE)
    Map<String, String> deleteCarrinhoCompra(@RequestParam Long id){
        Map<String, String> status = new HashMap<>();
        if(carrinhoCompraService.exists(id)) {
            carrinhoCompraService.delete(id);
            status.put("Status", "CarrinhoCompra deleted successfully");
        }
        else {
            status.put("Status", "CarrinhoCompra not exist");
        }
        return status;
    }

    @RequestMapping(value = "/carrinhocompras", method = RequestMethod.GET)
    List<CarrinhoCompra> getAllCarrinhoCompra(){
        return carrinhoCompraService.getAll();
    }

    @RequestMapping(value = "/carrinhocompra/{id}", method = RequestMethod.POST)
    CarrinhoCompra addItemCarrinhoCompra(@PathVariable Long id, @RequestBody ItemCompra itemCompra){
        CarrinhoCompra carrinhoCompra = carrinhoCompraService.addItemCarrinhoCompra(id, itemCompra);
        return carrinhoCompra;
    }

    @RequestMapping(value = "/carrinhocompra/{id}", method = RequestMethod.DELETE)
    CarrinhoCompra removeItemCarrinhoCompra(@PathVariable Long id, @RequestBody ItemCompra itemCompra){
        CarrinhoCompra carrinhoCompra = carrinhoCompraService.removeItemCarrinhoCompra(id, itemCompra);
        return carrinhoCompra;
    }

}
