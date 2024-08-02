package jmu.zxyzg.final_project.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Seller {
    private Integer id;
    private String name;
    private String tradeName;
    @JsonIgnore
    private String pwd;
    @JsonIgnore
    private String rePwd;
    private String description;
    private String phone;
    private String contactPerson;
}
