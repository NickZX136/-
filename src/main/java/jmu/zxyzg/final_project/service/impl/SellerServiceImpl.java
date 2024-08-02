package jmu.zxyzg.final_project.service.impl;

import jmu.zxyzg.final_project.mapper.SellerMapper;
import jmu.zxyzg.final_project.pojo.Seller;
import jmu.zxyzg.final_project.service.SellerService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public Seller findSellerByName(String name) {

        return sellerMapper.findSellerByName(name);
    }

    @Override
    public Integer register(String name, String pwd, String rePwd) {
        String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        String md5rePwd = DigestUtils.md5DigestAsHex(rePwd.getBytes());

        if (md5Pwd.equals(md5rePwd)) {
            Seller seller = new Seller();
            seller.setName(name);
            seller.setPwd(md5Pwd);
            int rows = sellerMapper.register(seller);
            if(rows>0){
                return 0;
            }else {
                return -1;
            }

        }else {
            return 1;
        }
    }

    @Override
    public boolean loginCheck(Seller seller) {
        //用户提供的密码
        String md5Pwd = DigestUtils.md5DigestAsHex(seller.getPwd().getBytes());

        Seller seller1 = sellerMapper.findSellerByName(seller.getName());
        return seller1.getPwd().equals(md5Pwd);
    }

    @Override
    public void updatePwd(String pwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");

        sellerMapper.updatePwd(name,pwd);
    }

    @Override
    public void updateTradeName(String tradeName) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");

        sellerMapper.updateTradeName(name,tradeName);
    }

    @Override
    public List<Seller> searchById(Integer id) {
        return sellerMapper.searchById(id);
    }

    @Override
    public List<Seller> searchByTradeName(String tradeName) {
        return sellerMapper.searchByTradeName(tradeName);
    }

    @Override
    public void updateInfo(Seller seller) {
        sellerMapper.updateInfo(seller);
    }


}
