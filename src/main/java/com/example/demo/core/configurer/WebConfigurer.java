package com.example.demo.core.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.core.ret.RetCode;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.ServiceException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author yangr
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(getHandlerExceptionResolver());
    }

    /**
     * 创建异常处理
     *
     * @return
     */
    private HandlerExceptionResolver getHandlerExceptionResolver() {


        //匿名类
        HandlerExceptionResolver handlerExceptionResolver = new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                                 Object handler, Exception e) {
                RetResult<Object> result = getResuleByHandlerException(request, handler, e);
                responseResult(response, result);
                return new ModelAndView();
            }
        };
        return handlerExceptionResolver;
    }


    /**
     * @param response
     * @param result
     * @Description 响应结果
     */
    private void responseResult(HttpServletResponse response, RetResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * 根据异常类型返回数据
     *
     * @param request
     * @param handler
     * @param e
     * @return
     */
    private RetResult<Object> getResuleByHandlerException(HttpServletRequest request, Object handler, Exception e) {

        RetResult<Object> result = new RetResult<>();
        if (e instanceof ServiceException) {
            result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
            return result;
        }
        if (e instanceof NoHandlerFoundException) {
            result.setCode(RetCode.NOT_FOUND).setMsg("接口：[ " + request.getRequestURI() + " ]不存在");
            return result;
        }
        result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口：[" + request.getRequestURI() + " ]内部错误");

        String message;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            message = String.format("接口[%s]出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURL(), handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
        } else {
            message = e.getMessage();
        }
        System.out.println(message);
        return result;
    }


    /**
     * 重新指定静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}