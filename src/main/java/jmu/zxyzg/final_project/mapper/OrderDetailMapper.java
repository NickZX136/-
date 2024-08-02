package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.dto.OrderDetailProductSellerDTO;
import jmu.zxyzg.final_project.pojo.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into order_detail(buyer_id, product_id, order_table_id, order_detail_price, status, " +
            "create_date, payment_status, payment, product_num," +
            "postage, payment_date, send_date, end_date, close_date, seller_id) " +
            "VALUES (#{buyerId},#{productId},#{orderTableId},#{orderDetailPrice}," +
            "0,#{createDate},#{paymentStatus}," +
            "#{payment},#{productNum},#{postage},#{paymentDate}," +
            "#{sendDate},#{endDate},#{closeDate},#{sellerId})")
    void add(OrderDetail orderDetail);

    @Select("select * from order_detail o left join product p on o.product_id = p.id " +
            "left join seller s on o.seller_id=s.id " +
            "where order_table_id=#{orderTableId}")
    List<OrderDetailProductSellerDTO> searchByOrderTableId(Integer orderTableId);

    @Update("update order_detail set status=#{status} where id=#{id}")
    void updateStatus(Integer id, Integer status);

    @Delete("delete from order_detail where id=#{id}")
    void delete(Integer id);

    @Update("update order_detail set order_table_id=#{orderTableId} where id=#{id}")
    void updateOrderTableId(Integer id, Integer orderTableId);

    @Select("select * from order_detail where buyer_id=#{buyerId} order by id desc limit 1")
    OrderDetail searchTheLastOneByBuyerId(Integer buyerId);

    @Select("select * from order_detail where id=#{id}")
    OrderDetail searchById(Integer id);

    @Select("select * from order_detail o left join product p on o.product_id=p.id " +
            "where o.seller_id=#{id};")
    List<OrderDetailProductSellerDTO> searchBySellerId(Integer id);

    @Select("select * from order_detail o left join product p on o.product_id=p.id " +
            "left join seller s on o.seller_id=s.id " +
            "where buyer_id=#{id}")
    List<OrderDetailProductSellerDTO> searchByBuyerId(Integer id);
}
