package com.hmdp.config;

import com.hmdp.utils.LoginInterceptor;
import com.hmdp.utils.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/shop/**",
                        "/voucher/**",
                        "/shop-type/**",
                        "/upload/**",
                        "/blog/hot",
                        "/user/code",
                        "/user/login"
                ).order(1);
        // token刷新的拦截器

        // Spring Boot 2.x 及之前，需要通过构造器手动传递stringRedisTemplate依赖
        // 拦截器的注册是 Servlet 容器的行为，与 Spring 容器的生命周期分离，需手动桥接两者的依赖。
        // Spring Boot 3.x 及之后，可以直接使用 @Bean 注解将拦截器声明为 Bean，拦截器的依赖会自动注入。
        // 原说法不再严格成立，但底层仍存在两种容器的协作（只是对开发者透明）
        // @Bean // 将拦截器声明为Bean

        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0);
    }
}
