package com.example.demo.core.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @date
 * @Description 重写请求参数处理函数
 * @author yangr
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;
    private boolean isIncludeRichText = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText){

        super(request);
        this.orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    /**
     * 覆盖getParameter 方法，将参数名和参数值都做xss过滤
     * 如果需要获得原始的值 ，则通过 super.getParameterValues(name)
     * getParamerterNames, getParameterValues, getParameterMap 也可能需要覆盖
     *
     */
    @Override
    public String getParameter(String name) {
        if(("content".equals(name) || name.endsWith("WithHtml") && !isIncludeRichText)){
            return super.getParameter(name);
        }
        name = XssFilterUtil.clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)){
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = XssFilterUtil.clean(values[i]);
            }
        }
        return values;
    }
    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {
        name = XssFilterUtil.clean(name);
        String header = super.getHeader(name);
        if(StringUtils.isNotBlank(header)){
            header = XssFilterUtil.clean(header);
        }
        return header;
    }

    /**
     * 获取request
     */
    public HttpServletRequest getOrgRequest(){
        return orgRequest;
    }
    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper)req).getOrgRequest();
        }
        return req;
    }


}
