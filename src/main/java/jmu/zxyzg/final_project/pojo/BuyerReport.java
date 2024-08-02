package jmu.zxyzg.final_project.pojo;

import lombok.Data;

@Data
public class BuyerReport {
    private Integer id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer reportYear;
    private Integer reportMonth;
    private String reportData;
}
