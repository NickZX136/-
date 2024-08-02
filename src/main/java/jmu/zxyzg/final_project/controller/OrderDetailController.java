package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.dto.OrderDetailProductSellerDTO;
import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(("/orderDetail"))
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/add")
    public Result add(@RequestBody OrderDetail orderDetail) {
        orderDetailService.add(orderDetail);
        return Result.success();
    }

    @PostMapping("/searchById")
    public Result<OrderDetail> searchById(@RequestParam("id") Integer id) {
        OrderDetail orderDetail = orderDetailService.searchById(id);
        return Result.success(orderDetail);
    }

    //买家设置订单详情状态为已支付，不要用这个，用orderTable里的
    @PatchMapping("/updateStatusToPaid")
    public Result updateStatusToPaid(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        orderDetailService.updateStatusToPaid(id);
        return Result.success();
    }

    //买家设置订单详情状态为已收货
    @PatchMapping("/updateStatusToReceived")
    public Result updateStatusToReceived(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        orderDetailService.updateStatusToReceived(id);
        return Result.success();
    }

    //卖家设置订单详情状态为已发货
    @PatchMapping("/updateStatusToShipped")
    public Result updateStatusToShipped(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        System.out.println(id);
        orderDetailService.updateStatusToShipped(id);
        return Result.success();
    }

    @GetMapping("/searchLastOrderDetails")
    public Result<List<OrderDetailProductSellerDTO>> searchLastOrderDetails() {
        List<OrderDetailProductSellerDTO> OrderDetailProductSellerDTO = orderDetailService.searchLastOrderDetails();
        return Result.success(OrderDetailProductSellerDTO);
    }

    @GetMapping("/searchBySellerId")
    public Result<List<OrderDetailProductSellerDTO>> searchBySellerId(Integer productId) {
        List<OrderDetailProductSellerDTO> OrderDetailProductSellerDTO = orderDetailService.searchBySellerId();
        return Result.success(OrderDetailProductSellerDTO);
    }

    @GetMapping("/searchByBuyerId")
    public Result<List<OrderDetailProductSellerDTO>> searchByBuyerId() {
        List<OrderDetailProductSellerDTO> OrderDetailProductSellerDTO = orderDetailService.searchByBuyerId();
        return Result.success(OrderDetailProductSellerDTO);
    }
}
