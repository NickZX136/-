package jmu.zxyzg.final_project.pojo;

import lombok.Data;

@Data
public class GoodsReturn {
    private Integer id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer orderDetailId;
    private String sellerOpinion;
    private String returnReason;
    private Integer status;
    /*
       状态：
       0：生成退货单的默认状态
       1：卖家同意退货
       2: 订单未发货，自动退货
       -1：卖家拒绝退货
    */
}
