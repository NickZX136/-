package jmu.zxyzg.final_project.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Pay {
    private Integer id;
    private Integer orderId;
    private Integer buyerId;
    private String payPlatform;
    private LocalDateTime createTime;
}
