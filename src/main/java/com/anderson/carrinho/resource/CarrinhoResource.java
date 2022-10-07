package com.anderson.carrinho.resource;

import com.anderson.carrinho.model.Carrinho;
import com.anderson.carrinho.model.Item;
import com.anderson.carrinho.resource.dto.ItemDto;
import com.anderson.carrinho.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/carrinhos")
@RequiredArgsConstructor
public class CarrinhoResource {

    private final CarrinhoService carrinhoService;
    @PostMapping
    public Item incluirItemNoCarrinho(@RequestBody ItemDto itemDto){

        return carrinhoService.incluirItemNoCarrinho(itemDto);
    }
    @GetMapping ("/{id}")
    public Carrinho verCarrinho(@PathVariable("id") Long id){
        return carrinhoService.verCarrinho(id);
    }
    @PatchMapping("/fecharCarrinho/{carrinhoId}")
    public Carrinho fecharCarrinho(@PathVariable("carrinhoId") Long carrinhoId, @RequestParam("formaPagamento") int formaPagamento){
        return carrinhoService.fecharCarrinho(carrinhoId, formaPagamento);
    }
}
