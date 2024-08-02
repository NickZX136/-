package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.pojo.OrderTable;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping(("/orderTable"))
public class OrderTableController {

    @Autowired
    private OrderTableService orderTableService;

    @PostMapping("/add")
    public Result add(@RequestBody OrderTable orderTable) {
        orderTableService.add(orderTable);
        return Result.success();
    }

    //当从购物车中结算的时候就使用这个函数生成订单。
    //前端只需传入一个addressId
    @PostMapping("/addFromCart")
    public Result addFromCart(@RequestBody OrderTable orderTable) throws ParseException {
        orderTableService.addFromCart(orderTable);
        return Result.success();
    }

    //直接从商品页购买时，调用这个函数就能生成的订单
    @PostMapping("/addFromProductPage")
    public Result addFromProductPage(@RequestBody OrderDetail orderDetail) throws ParseException {
        orderTableService.addFromProductPage(orderDetail);
        return Result.success();
    }

    //买家设置订单详情状态为已支付
    @PatchMapping("/updateStatusToPaid")
    public Result updateStatusToPaid(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        orderTableService.updateStatusToPaid(id);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        orderTableService.delete(id);
        return Result.success();
    }

    //这个函数前端应该不用用到
    @GetMapping(("/searchTheLastOneByBuyerId"))
    public Result searchTheLastOneByBuyerId(@RequestParam int buyerId) {
        OrderTable orderTable = orderTableService.searchTheLastOneByBuyerId(buyerId);
        return Result.success(orderTable);
    }

    //提交订单
    @PatchMapping("/submit")
    public Result submit(@RequestBody Map<String, Integer> params){
        //接收orderTable的id
        Integer id = params.get("id");
        orderTableService.submit(id);
        return Result.success();
    }



}
