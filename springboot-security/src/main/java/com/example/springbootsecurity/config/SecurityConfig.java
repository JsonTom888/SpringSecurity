package com.example.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/6 21:14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**", "/").permitAll()//配置不用登陆就可以访问的页面
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/content/**").access("hasRole('ADMIN') or hasRole('USER')")
//                .antMatchers("/admin/**").hasIpAddress("192.168.11.11")//只有固定IP可以访问
                .anyRequest().authenticated()//表示其他请求都要经过登陆认证
                .and()
                .formLogin()//定制登录信息
//                .loginPage("/login")//自定义登陆地址，若注释掉表示使用默认登录页面
                .permitAll()
                .and()
                .logout()//退出功能
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/logout");//忽略退出请求的同源限制
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password(new BCryptPasswordEncoder()
                        .encode("123456")).roles("USER")
                .and()
                .withUser("admin")
                .password(new BCryptPasswordEncoder()
                        .encode("admin")).roles("ADMIN", "USER");
    }
}
