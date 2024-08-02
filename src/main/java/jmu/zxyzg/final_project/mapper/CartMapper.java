package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.dto.CartProductDTO;
import jmu.zxyzg.final_project.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert("insert into cart(buyer_id, product_id, product_num, checked) " +
            "values (#{buyerId},#{productId},#{productNum},#{checked})")
    void add(Cart cart);

    @Delete("delete from cart where id=#{id}")
    void delete(int id);

    @Update("update cart set product_num=#{productNum}, checked=#{checked} " +
            "where id=#{id}")
    void update(Integer id, Integer productNum, boolean checked);

    @Select("select * from cart c left join taobao_2.product p on c.product_id = p.id " +
            "where buyer_id=#{buyerId}")
    List<CartProductDTO> searchAllByBuyerId(Integer buyerId);

    @Select("select * from cart c left join taobao_2.product p on c.product_id = p.id " +
            "where buyer_id=#{buyerId} and checked = 1")
    List<CartProductDTO> searchAllCheckByBuyerId(Integer buyerId);

    @Update("update cart set product_num=#{productNum} where id=#{id}")
    void updateProductNum(Integer id, Integer productNum);
}
