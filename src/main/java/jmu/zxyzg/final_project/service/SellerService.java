package jmu.zxyzg.final_project.service;

import jmu.zxyzg.final_project.pojo.Seller;

import java.util.List;

public interface SellerService {
    Seller findSellerByName(String name);

    Integer register(String name, String pwd, String rePwd);

    boolean loginCheck(Seller seller);

    void updatePwd(String pwd);

    void updateTradeName(String tradeName);

    List<Seller> searchById(Integer id);

    List<Seller> searchByTradeName(String tradeName);

    void updateInfo(Seller seller);
}
