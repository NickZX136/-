package jmu.zxyzg.final_project.mapper;

import jmu.zxyzg.final_project.pojo.GoodsReturn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsReturnMapper {
    @Insert("insert into goods_return(buyer_id, seller_id, order_detail_id, seller_opinion, return_reason, status) " +
            "VALUES (#{buyerId},#{sellerId},#{orderDetailId},#{sellerOpinion},#{returnReason},0)")
    void add(GoodsReturn goodsReturn);

    @Select("select * from goods_return where seller_id=#{sellerId}")
    List<GoodsReturn> searchBySellerId(int sellerId);

    @Select("select * from goods_return where id=#{id}")
    GoodsReturn searchById(Integer id);

    @Update("update goods_return set status=#{status} where id=#{id}")
    void updateStatus(Integer id, Integer status);

    @Select("select * from goods_return where buyer_id=#{buyerId}  ORDER BY id DESC LIMIT 1")
    GoodsReturn searchTheLastOneByBuyerId(Integer buyerId);

    @Update("update goods_return set seller_opinion=#{sellerOpinion} where id=#{id}")
    void updateSellerOpinion(Integer id, String sellerOpinion);
}
