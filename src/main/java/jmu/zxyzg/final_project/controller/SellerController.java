package jmu.zxyzg.final_project.controller;

import jakarta.validation.constraints.Pattern;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.pojo.Seller;
import jmu.zxyzg.final_project.service.SellerService;
import jmu.zxyzg.final_project.utils.JwtUtil;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,16}$") String name, @Pattern(regexp = "^\\S{5,16}$") String pwd, String rePwd) {

        Seller seller = new Seller();
        seller.setName(name);
        seller.setPwd(pwd);
        seller.setRePwd(rePwd);
        //查询用户
        Seller sellerObject = sellerService.findSellerByName(seller.getName());
        //判断是否存在用户
        if (sellerObject == null) {
            //注册
            Integer status = sellerService.register(seller.getName(),seller.getPwd(),seller.getRePwd());
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
        Seller seller = new Seller();
        seller.setName(name);
        seller.setPwd(pwd);
        Seller sellerObject = sellerService.findSellerByName(seller.getName());
        if (sellerObject == null) {
            return Result.error("1","用户不存在");
        }else {
            if(sellerService.loginCheck(seller)){
                //生成token
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", seller.getId());
                claims.put("name", seller.getName());
                String token = JwtUtil.generateToken(claims);
                return Result.success(token);
            }else {
                return Result.error("1","密码错误");
            }
        }
    }

    @GetMapping("/info")
    public Result<Seller> SellerInfo(){

        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");

        Seller seller = sellerService.findSellerByName(name);
        return Result.success(seller);
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
            sellerService.updatePwd(md5pwd);
        }else {
            return Result.error("3","两次密码输入不相同");
        }

        return Result.success();
    }

    @PatchMapping("/updateTradeName")
    public Result updateTradeName(@RequestBody Map<String, String> params){
        String tradeName = params.get("tradeName");
        sellerService.updateTradeName(tradeName);
        return Result.success();
    }

    //点击商家详情页用的
    @GetMapping("/searchById")
    public Result<List<Seller>> searchById(@RequestParam("id") Integer id){
        List<Seller> list = sellerService.searchById(id);
        return Result.success(list);
    }

    //模糊搜索商家
    @GetMapping("/searchByTradeName")
    public Result<List<Seller>> searchByTradeName(@RequestParam("tradeName") String tradeName){
        List<Seller> list = sellerService.searchByTradeName(tradeName);
        return Result.success(list);
    }

    @PutMapping("/updateInfo")
    public Result updateInfo(@RequestBody Seller seller){
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Seller seller1 = sellerService.findSellerByName(name);
        seller.setId(seller1.getId());
        sellerService.updateInfo(seller);
        return Result.success();
    }

}
