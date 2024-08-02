package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.dto.ProductSellerDTO;
import jmu.zxyzg.final_project.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product(seller_id, name, image, " +
            "price, color, size, tagId, detail, stock, status, postage) " +
            "values (#{sellerId},#{name},#{image}," +
            "#{price},#{color},#{size},#{tagId}," +
            "#{detail},#{stock},#{status},#{postage})")
    void add(Product product);

    @Delete("delete from product where id=#{id}")
    void delete(int id);

    @Update("update product set name=#{name}, image=#{image}," +
            "price=#{price}, color=#{color}, size=#{size}, tagId=#{tagId}, " +
            "detail=#{detail}, stock=#{stock}, status=#{status}, postage=#{postage} " +
            "where id=#{id}")
    void update(Product product);

    @Select("select * from product where id=#{id}")
    Product searchById(int id);

    @Select("select * from product where seller_id=#{sellerId}")
    List<Product> searchBySellerId(int sellerId);

    @Update("update product set sales = sales + #{num} where id=#{id}")
    void addSales(Integer id, Integer num);

    @Update("update product set sales = sales - #{num} where id=#{id}")
    void decreaseSales(Integer id, Integer num);

    @Update("update product set image=#{imagePath} where id=#{id}")
    void updateImage(int id, String imagePath);

    @Select("SELECT * FROM product ORDER BY RAND() LIMIT 20;")
    List<Product> homeProductDisplay();

    @Select("select * from product where name like concat('%',#{name},'%') ")
    List<Product> elasticSearch(String name);

    @Select("select * from product p left join seller s on p.seller_id = s.id where p.id=#{id}")
    ProductSellerDTO displayProductDetail(Integer id);
}
