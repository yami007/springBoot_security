package com.yami.springboot_security.config;

import com.yami.springboot_security.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*//可以设置内存指定登录的账号密码,指定角色
        //不加.passwordEncoder(new MyPasswordEncoder(),就不是以明文的方式进行匹配，会报错
        auth.inMemoryAuthentication().withUser("admin").password("321654").roles("ADMIN");
        //Spring boot 2.0.3引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报错误：
        //这样，页面提交时候，密码以明文的方式进行匹配。
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("yami").password("yami").roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangsan").password("zhangsan").roles("USER");
        */
        auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()  // 放行
                .anyRequest().authenticated() // 需要登录
                .and()
                .logout().permitAll() // 注销可以访问
                .and()
                .formLogin(); // 允许表单登录
        http.csrf().disable(); // 关闭csrf认证
    }
}
