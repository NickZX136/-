package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.OrderDetail;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.BuyerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/buyerReport"))
public class BuyerReportController {

    @Autowired
    private BuyerReportService buyerReportService;

    @GetMapping("/generate")
    public Result generate(@RequestParam("sellerId") Integer sellerId,
                           @RequestParam("reportYear") Integer reportYear,
                           @RequestParam("reportMonth") Integer reportMonth){
        List<OrderDetail> list = buyerReportService.generate(sellerId, reportYear,reportMonth);
        System.out.println(list);

        return Result.success(list);
    }
}
