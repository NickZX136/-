package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.dto.ProductSellerDTO;
import jmu.zxyzg.final_project.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);

    void delete(int id);

    void update(Product product);

    Product searchById(int id);

    List<Product> searchBySellerId(int sellerId);

    void addSales(Integer id, Integer num);

    void decreaseSales(Integer id, Integer num);

    void updateImage(int id, String imagePath);

    String searchImagePathById(Integer id);

    List<Product> homeProductDisplay();

    List<Product> elasticSearch(String name);

    ProductSellerDTO displayProductDetail(Integer id);
}
