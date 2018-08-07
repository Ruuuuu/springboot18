package com.example.demo.core.filter;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * @Description xss非法标签过滤
 * @author yangr
 * @date
 */
public class XssFilterUtil {

    /**
     * 使用自带的 basicWithImages 白名单
     */
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    private static final Whitelist whitelist2 = Whitelist.basicWithImages();

    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);
    static {
        /**
         * 给所有标签添加style 属性
         */
        whitelist.addAttributes(":all","style");
    }

    public static String clean(String content){
        return Jsoup.clean(content, "", whitelist, OUTPUT_SETTINGS);

    }



}
