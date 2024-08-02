package jmu.zxyzg.final_project.controller;

import jakarta.annotation.Resource;
import jmu.zxyzg.final_project.dto.ProductSellerDTO;
import jmu.zxyzg.final_project.pojo.Buyer;
import jmu.zxyzg.final_project.pojo.Product;
import jmu.zxyzg.final_project.pojo.Result;
import jmu.zxyzg.final_project.pojo.Seller;
import jmu.zxyzg.final_project.service.ProductService;
import jmu.zxyzg.final_project.service.SellerService;
import jmu.zxyzg.final_project.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(("/product"))
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add")
    private Result add(@RequestBody Product product){
        productService.add(product);
        return Result.success();
    }

    @DeleteMapping("/delete")
    private Result delete(@RequestParam("id") int id){
        productService.delete(id);
        return Result.success();
    }

    @PutMapping("/update")
    private Result update(@RequestBody Product product){
        productService.update(product);
        return Result.success();
    }

    @GetMapping("/searchById")
    public Result searchById(@RequestParam("id") int id){
        Product product = productService.searchById(id);
        return Result.success(product);
    }

    @GetMapping("/searchBySellerId")
    public Result<List<Product>> searchBySellerId(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("name");
        Seller seller = sellerService.findSellerByName(name);
        Integer sellerId = seller.getId();
        List<Product> list = productService.searchBySellerId(sellerId);
        return Result.success(list);
    }

    @PatchMapping("/addSales")
    public Result addSales(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        Integer num = params.get("num");//传入增加的数量
        productService.addSales(id,num);
        return Result.success();
    }

    @PatchMapping("/decreaseSales")
    public Result decreaseSales(@RequestBody Map<String, Integer> params){
        Integer id = params.get("id");
        Integer num = params.get("num");//传入增加的数量
        productService.decreaseSales(id,num);
        return Result.success();
    }

    @PostMapping("/addImage")
    public Result addImage(@RequestParam("id")Integer id, @RequestPart("image") MultipartFile image) throws IOException {
        String fileName = null;

        if(!image.isEmpty()){
            //获取源照片名
            String originalFilename = image.getOriginalFilename();
            //获取照片后缀名
            String suffixName= null;
            if (originalFilename != null) {
                suffixName = originalFilename.substring(originalFilename.lastIndexOf('.'));
            }
            //使用UUID
            fileName= UUID.randomUUID().toString()+suffixName;
            //保存照片到磁盘
            image.transferTo(new File("D://taobao_image//"+fileName));
        }

        //保存到数据库
        productService.updateImage(id, fileName);

        //重定向到照片展示页面
        return Result.success("D://taobao_image//"+fileName);
    }

    @GetMapping("/getProductImage")
    public Result getProductImage(@RequestParam Integer id) {
        String imagePath = "D://taobao_image//" + productService.searchImagePathById(id);
        return Result.success(imagePath);
    }

    //首页商品显示
    @GetMapping("/homeProductDisplay")
    public Result<List<Product>> homeProductDisplay(){
        List<Product> list = productService.homeProductDisplay();
        return Result.success(list);
    }

    //模糊搜索
    @GetMapping("/elasticSearch")
    public Result<List<Product>> elasticSearch(@RequestParam("name") String name){
        List<Product> list = productService.elasticSearch(name);
        return Result.success(list);
    }

    @GetMapping("/displayProductDetail")
    public Result<ProductSellerDTO> displayProductDetail(@RequestParam("id") Integer id){
        ProductSellerDTO productSellerDTO = productService.displayProductDetail(id);
        return Result.success(productSellerDTO);
    }

}
