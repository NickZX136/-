package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.ProductMapper;
import jmu.zxyzg.final_project.mapper.SellerMapper;
import jmu.zxyzg.final_project.mapper.SellerReportMapper;
import jmu.zxyzg.final_project.pojo.Product;
import jmu.zxyzg.final_project.pojo.Seller;
import jmu.zxyzg.final_project.service.SellerReportService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class SellerReportServiceImpl implements SellerReportService {

    @Autowired
    private SellerReportMapper sellerReportMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public List<Product> generate() {

        Map<String,Object> map = ThreadLocalUtil.get();
        String sellerName = (String) map.get("name");

        Seller seller = sellerMapper.findSellerByName(sellerName);
        Integer sellerId = seller.getId();

        List<Product> list = productMapper.searchBySellerId(sellerId);
        for(Product product:list){
            BigDecimal goodIncome = product.getPrice().multiply(BigDecimal.valueOf(product.getSales()));
            product.setGoodIncome(goodIncome);
        }
        return list;
    }

    @Override
    public BigDecimal generateAllGoodIncome() {
        List<Product> list = generate();
        BigDecimal allGoodIncome = new BigDecimal(0);
        for(Product product:list){
            allGoodIncome = allGoodIncome.add(product.getGoodIncome());
        }
        return allGoodIncome;
    }
}
