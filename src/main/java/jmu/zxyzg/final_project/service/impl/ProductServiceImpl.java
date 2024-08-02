package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.dto.ProductSellerDTO;
import jmu.zxyzg.final_project.mapper.ProductMapper;
import jmu.zxyzg.final_project.mapper.SellerMapper;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.Product;
import jmu.zxyzg.final_project.pojo.Seller;
import jmu.zxyzg.final_project.service.ProductService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public void add(Product product) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Seller seller = sellerMapper.findSellerByName(name);
        product.setSellerId(seller.getId());
        productMapper.add(product);
    }

    @Override
    public void delete(int id) {
        productMapper.delete(id);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product searchById(int id) {
        return productMapper.searchById(id);
    }

    @Override
    public List<Product> searchBySellerId(int sellerId) {
        return productMapper.searchBySellerId(sellerId);
    }

    @Override
    public void addSales(Integer id, Integer num) {
        productMapper.addSales(id,num);
    }

    @Override
    public void decreaseSales(Integer id, Integer num) {
        productMapper.decreaseSales(id,num);
    }

    @Override
    public void updateImage(int id, String imagePath) {
        productMapper.updateImage(id, imagePath);
    }

    @Override
    public String searchImagePathById(Integer id) {
        Product product = productMapper.searchById(id);
        return product.getImage();
    }

    @Override
    public List<Product> homeProductDisplay() {
        return productMapper.homeProductDisplay();
    }

    @Override
    public List<Product> elasticSearch(String name) {
        return productMapper.elasticSearch(name);
    }

    @Override
    public ProductSellerDTO displayProductDetail(Integer id) {
        return productMapper.displayProductDetail(id);
    }

}
