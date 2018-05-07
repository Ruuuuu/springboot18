package com.example.demo.core.configurer;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * druid 监控配置
 */
@Configuration
public class DruidMonitorConfigurer {

    /**
     * 注册servletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean registrationBean() {

        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        /** 初始化参数配置**/
        //白名单
        bean.addInitParameter("allow", "127.0.0.1");
        bean.addInitParameter("deny", "192.168.1.73");
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "12345");
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }
    /**
     * 注册FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }


}
