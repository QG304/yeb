package com.cc.config.security;

import com.cc.pojo.Admin;
import com.cc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private AdminService adminService;

    @Autowired(required = false)
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired(required = false)
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/websocket/**",
                "/**.html",
                "/login/**",
                "//hello/**",
                "/register/**",
                "/logout/**",
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
                "favicon.ico",
                "/doc.html",                    // 放行 swagger 资源
                "/webjars/**",                  // 放行 swagger 资源
                "/swagger-resources/**",        // 放行 swagger 资源
                "/v2/api-docs/**"             // 放行 swagger 资源
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用JWT,不需要csrf
        http.csrf()
                .disable()
                //基于token,不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许登录访问
                .antMatchers("/login","/logout")
                .permitAll()
                //除了上面，所有请求都需要认证
                .anyRequest()
                .authenticated()
                .and()
                //禁用缓存
                .headers()
                .cacheControl();
        //添加jwt 登录授权过滤器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);


    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
          Admin admin =   adminService.getAdminByUserName(username);
            if (null!=admin){
                return admin;
            }
            return null;
        };
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }


}
