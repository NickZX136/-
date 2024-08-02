package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.OrderTable;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface OrderTableMapper {
    @Insert("insert into order_table(address_id, total_price,status,buyer_id,create_time,flag) " +
            "values (#{addressId},#{totalPrice},0,#{buyerId},#{createTime},#{flag})")
    void add(OrderTable orderTable);

    @Delete("delete from order_table where id=#{id}")
    void delete(Integer id);

    @Select("select * from order_table where buyer_id=#{buyerId} order by id desc limit 1")
    OrderTable searchTheLastOneByBuyerId(int buyerId);

    @Update("update order_table set total_price=#{totalPrice} where id=#{id}")
    void updateTotalPrice(BigDecimal totalPrice, Integer id);

    @Update("update order_table set status=#{status} where id=#{id}")
    void updateStatus(Integer id, Integer status);

    @Select("select * from order_table where id=#{id}")
    OrderTable searchById(Integer id);

    @Update("update order_table set create_time=#{createTime} where id=#{id}")
    void updateCreateTime(Integer id, Date createTime);

    @Update("update order_table set status=2 where id=#{id}")
    void updateStatusToPaid(Integer id);
}
