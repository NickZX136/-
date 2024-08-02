package jmu.zxyzg.final_project.pojo;

import lombok.Data;

@Data
public class Cart {
    private Integer id;
    private Integer buyerId;
    private Integer productId;
    private Integer productNum;
    private Boolean checked;//是否勾选
}
