package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.GoodsReturn;
import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.GoodsReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(("/goodsReturn"))
public class GoodsReturnController {

    @Autowired
    private GoodsReturnService goodsReturnService;

    @PostMapping("/add")
    private Result add(@RequestBody GoodsReturn goodsReturn) {
        goodsReturnService.add(goodsReturn);
        return Result.success();
    }

    @PostMapping("/searchBySellerId")
    private Result<List<GoodsReturn>> searchBySellerId(@RequestParam("sellerId") int sellerId) {
        List<GoodsReturn> list = goodsReturnService.searchBySellerId(sellerId);
        return Result.success(list);
    }

    //卖家同意退货
    @PatchMapping("/agreeReturn")
    private Result agreeReturn(@RequestBody GoodsReturn goodsReturn) {
        Integer id = goodsReturn.getId();
        String sellerOpinion = goodsReturn.getSellerOpinion();
        goodsReturnService.agreeReturn(id, sellerOpinion);
        return Result.success();
    }

    //卖家拒绝退货
    @PatchMapping("/refuseReturn")
    private Result refuseReturn(@RequestBody GoodsReturn goodsReturn) {
        Integer id = goodsReturn.getId();
        String sellerOpinion = goodsReturn.getSellerOpinion();
        goodsReturnService.refuseReturn(id, sellerOpinion);
        return Result.success();
    }

}
