package jmu.zxyzg.final_project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailProductSellerDTO {
    private Integer id;
    private Integer buyerId;
    private Integer productId;
    private Integer sellerId;
    private Integer orderTableId;
    private BigDecimal orderDetailPrice;
    private Integer productNum;
    private Integer status;
    /*
    订单状态：
        0：未提交订单，比如点击了购买后跳转订单界面但是订单还没确认
        1：已提交订单，订单确认了但未支付
        2：订单已支付,但未发货
        3：订单已发货
        4：订单确认收货
        -1：等待取消订单状态，比如已提交退货单，在等待商家审核
        -2：交易关闭状态（即订单已取消状态），比如已提交订单后取消订单(1 -> -1)、商品退货退款后的取消订单(2 -> -1)
    */
    private Date createDate;
    private Integer paymentStatus;
    private String payment;
    private BigDecimal postage;//邮费
    private Date paymentDate;
    private Date sendDate;
    private Date endDate;
    private Date closeDate;
    //数据库没有的属性：
    private Integer addressId;

    private String name;
    private String image;

    private String tradeName;
}
