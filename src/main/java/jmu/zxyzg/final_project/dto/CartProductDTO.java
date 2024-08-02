package jmu.zxyzg.final_project.dto;

import lombok.Data;

@Data
public class CartProductDTO {
    private Integer id;
    private Integer buyerId;
    private Integer productId;
    private Integer productNum;
    private Boolean checked;//是否勾选
    private Integer sellerId;
    private String name;
    private byte[] image;
    private Double price;
    private String color;
    private String size;
    private Integer tagId;
    private String detail;
    private String stock;//存货
    private Boolean status;
}
