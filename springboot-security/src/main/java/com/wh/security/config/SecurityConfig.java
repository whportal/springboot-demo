package com.wh.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wh.security.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailServiceImpl sysUserDetailService;

    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    private EntryPoint entryPoint;

    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义的 UserDetailService
        auth.userDetailsService(sysUserDetailService)
                // 指定密码生成方式
                .passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("/login").permitAll()
                // .antMatchers("/admin/**").hasRole("admin")
                // .antMatchers("/user/**").hasRole("user")
                // /admin 接口必须使用用户名密码登录后访问 若为自动登录的 需要重新输入用户名密码
                .antMatchers("/admin").fullyAuthenticated()
                // /rememberMe 接口允许自动登录后 访问
                // .antMatchers("/rememberMe").rememberMe()
                .anyRequest().authenticated()
                .and()
                .rememberMe()
                // .formLogin()
                // .loginPage("/login")
                // .loginProcessingUrl("/login")
                // 配置登录成功后跳转的URL 设置为TRUE表示无论访问哪个路径，登录成功后都跳转到/index这个路径
                // .defaultSuccessUrl("/index", true)
                // .successForwardUrl("/index")
                // 登录失败的处理器
                // .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutHandler)
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
                    HttpServletResponse response = event.getResponse();
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(401);
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.failed("您已在另一台设备登录，本次登录已下线！")));
                }), ConcurrentSessionFilter.class)
                // 使用自定义的 Filter 替换 默认的 UsernamePasswordAuthenticationFilter
                .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                // .sessionManagement()
                // .maximumSessions(1)

        ;
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        // 认证成功的处理逻辑
        loginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());

        // 认证失败的处理逻辑
        loginFilter.setAuthenticationFailureHandler(failureHandler);

        // 认证管理器
        loginFilter.setAuthenticationManager(authenticationManagerBean());

        // 登录 URL 默认为 /login
        // loginFilter.setFilterProcessesUrl("/login");

        return loginFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // 可以响应一个登录成功的自定义状态码 前端根据此状态码进行处理
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("登录成功 状态码-10000");
            out.flush();
            out.close();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(sysUserDetailService);
        return authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
