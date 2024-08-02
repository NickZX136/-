package jmu.zxyzg.final_project.config;

import jmu.zxyzg.final_project.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        // TODO:将来还有其他用户的登录、注册接口没写
        String[] page = new String[]
                {
                        "/buyer/register","/buyer/login","/seller/register","/seller/login",
                        "/product/elasticSearch","/product/homeProductDisplay","/product/searchById",
                        "/seller/searchById","/seller/searchByTradeName"
                };
        registry.addInterceptor(loginInterceptor).excludePathPatterns(page);
    }
}
