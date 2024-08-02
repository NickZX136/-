package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BuyerReportMapper {

    @Select("select * " +
            "from order_detail o left join product p on o.product_id=p.id " +
            "where buyer_id=#{buyerId} and seller_id=#{sellerId} and YEAR(o.create_date) = #{reportYear} and MONTH(o.create_date) = #{reportMonth} " +
            "and o.status=2")
    List<OrderDetail> searchOrderDetailForGenerate(Integer buyerId, Integer sellerId, Integer reportYear, Integer reportMonth);
}
