package cn.tedu.store.config.LoginInterceptorConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeafDust
 * @create 2019-10-16 9:23
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList<>();
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/css/**");
        patterns.add("/bootstrap3/**");
        patterns.add("/images/**");
        patterns.add("/js/**");

        registry.addInterceptor((HandlerInterceptor) (new LoginInterceptor()))
                .addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
