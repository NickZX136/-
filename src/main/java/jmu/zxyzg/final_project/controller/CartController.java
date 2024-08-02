package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.dto.CartProductDTO;
import jmu.zxyzg.final_project.pojo.Cart;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/cart"))
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") int id) {
        cartService.delete(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Cart cart) {
        Integer id = cart.getId();
        Integer productNum = cart.getProductNum();
        boolean checked = cart.getChecked();
        cartService.update(id,productNum,checked);
        return Result.success();
    }

    @GetMapping("/searchAllByBuyerId")
    public Result<List<CartProductDTO>> searchAllByBuyerId(){
        List<CartProductDTO> list = cartService.searchAllByBuyerId();
        return Result.success(list);
    }

    @GetMapping("/searchAllCheckByBuyerId")
    public Result<List<CartProductDTO>> searchAllCheckByBuyerId(@RequestParam("buyerId") Integer buyerId){
        List<CartProductDTO> list = cartService.searchAllCheckByBuyerId(buyerId);
        return Result.success(list);
    }

    @GetMapping("/searchAllByBuyer")
    public Result<List<CartProductDTO>> searchAllByBuyer(){
        List<CartProductDTO> list = cartService.searchAllByBuyer();
        return Result.success(list);
    }

    @PutMapping("/updateProductNum")
    public Result updateProductNum(@RequestBody Cart cart) {
        Integer id = cart.getId();
        Integer productNum = cart.getProductNum();
        cartService.updateProductNum(id,productNum);
        return Result.success();
    }
}
