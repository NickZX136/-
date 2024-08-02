package jmu.zxyzg.final_project.controller;

import jmu.zxyzg.final_project.pojo.Product;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.SellerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(("/sellerReport"))
public class SellerReportController {

    @Autowired
    private SellerReportService sellerReportService;

    @GetMapping("/generate")
    public Result generate(){
        List<Product> list = sellerReportService.generate();
        return Result.success(list);
    }

    @GetMapping("/generateAllGoodIncome")
    public Result generateAllGoodIncome(){
        BigDecimal allGoodIncome = sellerReportService.generateAllGoodIncome();
        return Result.success(allGoodIncome);
    }

}
