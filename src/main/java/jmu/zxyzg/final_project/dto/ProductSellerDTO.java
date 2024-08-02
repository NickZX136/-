package jmu.zxyzg.final_project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductSellerDTO {
    private Integer id;
    private Integer sellerId;
    private String name;
    private String image;
    private BigDecimal price;
    private String color;
    private String size;
    private Integer tagId;
    private String detail;
    private String stock;//存货
    private Boolean status;
    /*物品状态：false：缺货，true：有货*/
    private BigDecimal postage;//邮费
    private Integer sales;
    private BigDecimal goodIncome;
    private String tradeName;
}
