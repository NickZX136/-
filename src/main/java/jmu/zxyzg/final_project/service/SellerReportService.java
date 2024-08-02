package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.Product;

import java.math.BigDecimal;
import java.util.List;

public interface SellerReportService {
    List<Product> generate();

    BigDecimal generateAllGoodIncome();
}
