package com.wjx.springboot.config;

import com.wjx.springboot.component.LoginHandlerInterceptor;
import com.wjx.springboot.component.MyLocaleResolver;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.util.Set;

//使用WebMvcConfigurer可以扩展SpringMVC的功能
//@EnableWebMvc //该注解作用：接管MVC
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /wjx 请求来到 success页面
        registry.addViewController("/wjx").setViewName("success");
    }

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/user/login");
            }
        };

        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
