package jmu.zxyzg.final_project.controller;

import jakarta.validation.constraints.Pattern;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.service.BuyerService;
import jmu.zxyzg.final_project.utils.JwtUtil;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,16}$") String name, @Pattern(regexp = "^\\S{5,16}$") String pwd, String rePwd) {

        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setPwd(pwd);
        buyer.setRePwd(rePwd);
        //查询用户
        Buyer buyerObject = buyerService.findBuyerByName(buyer.getName());
        //判断是否存在用户
        if (buyerObject == null) {
            //注册
            Integer status = buyerService.register(buyer.getName(),buyer.getPwd(),buyer.getRePwd());
            if(status == 0) {
                return Result.success();
            }else if(status == 1){
                return Result.error("2","两次密码输入不相同");
            }else {
                return Result.error("-1","注册失败");
            }

        }else {
            return Result.error("1","用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result login(String name, String pwd) {
        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setPwd(pwd);
        Buyer buyerObject = buyerService.findBuyerByName(buyer.getName());
        if(buyerObject == null) {
            return Result.error("1","用户不存在");
        }else {
            if(buyerService.loginCheck(buyer)) {
                //生成token
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", buyer.getId());
                claims.put("name", buyer.getName());
                String token = JwtUtil.generateToken(claims);
                return Result.success(token);
            }else {
                return Result.error("1","密码错误");
            }
        }
    }

    @GetMapping("/info")
    public Result<Buyer> buyerInfo(){

        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Buyer buyer = buyerService.findBuyerByName(name);
        return Result.success(buyer);
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){

        String pwd = params.get("pwd");
        String rePwd = params.get("rePwd");

        if(!StringUtils.hasLength(pwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("1","缺少必要的参数");
        }

        if((pwd.length()<5 || pwd.length()>16) || (rePwd.length()<5 || rePwd.length()>16)){
            return Result.error("2","密码长度必须为5-16位");
        }

        String md5pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        String md5rePwd = DigestUtils.md5DigestAsHex(rePwd.getBytes());

        if(md5pwd.equals(md5rePwd)) {
            buyerService.updatePwd(md5pwd);
        }else {
            return Result.error("3","两次密码输入不相同");
        }

        return Result.success();
    }

}
