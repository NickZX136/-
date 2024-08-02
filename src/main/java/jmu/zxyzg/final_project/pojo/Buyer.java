package jmu.zxyzg.final_project.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Buyer {
    private Integer id;
    private String name;
    @JsonIgnore
    private String pwd;
    @JsonIgnore
    private String rePwd;
    private String phone;
}
