package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.Buyer;

public interface BuyerService {
    Buyer findBuyerByName(String name);

    Integer register(String name, String pwd, String rePwd);

    boolean loginCheck(Buyer buyer);

    void updatePwd(String pwd);
}
