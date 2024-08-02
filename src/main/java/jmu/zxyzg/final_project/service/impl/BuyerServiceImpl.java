package jmu.zxyzg.final_project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jmu.zxyzg.final_project.mapper.BuyerMapper;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.service.BuyerService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;

    @Override
    public Buyer findBuyerByName(String name) {

        return buyerMapper.findBuyerByName(name);
    }

    @Override
    public Integer register(String name, String pwd, String rePwd) {
        String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        String md5rePwd = DigestUtils.md5DigestAsHex(rePwd.getBytes());

        if (md5Pwd.equals(md5rePwd)) {
            Buyer buyer = new Buyer();
            buyer.setName(name);
            buyer.setPwd(md5Pwd);
            int rows = buyerMapper.register(buyer);
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
    public boolean loginCheck(Buyer buyer) {
        //用户提供的密码
        String md5Pwd = DigestUtils.md5DigestAsHex(buyer.getPwd().getBytes());

        Buyer buyer1 = buyerMapper.findBuyerByName(buyer.getName());
        return buyer1.getPwd().equals(md5Pwd);
    }

    @Override
    public void updatePwd(String pwd) {

        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");

        buyerMapper.updatePwd(name,pwd);
    }
}
