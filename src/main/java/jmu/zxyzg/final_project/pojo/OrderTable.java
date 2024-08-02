package jmu.zxyzg.final_project.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderTable {
    private Integer id;
    private Integer addressId;
    private Integer status;
    /*
    订单状态：
        0：未提交订单，比如点击了购买后跳转订单界面但是订单还没确认
        1：已提交订单，订单确认了但未支付
        2：订单已支付
        -1：等待取消订单状态，比如已提交退货单，在等待商家审核
        -2：交易关闭状态（即订单已取消状态），比如已提交订单后取消订单(1 -> -1)、商品退货退款后的取消订单(2 -> -1)
    */
    private Integer buyerId;
    private BigDecimal totalPrice;
    private Date createTime;
    private Integer flag;
    /*
    flag:
        1：从购物车购买
        2：从商品页购买
    */
}
