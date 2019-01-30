package org.corbin.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Slf4j
public class ResponseDefaultMsg {
    private static final String msgCodeFileName = "msg-code-file.yml";

    private static Properties properties;

    public Properties getProperties() {
        return properties;
    }

    /**
     * 加载配置文件的属性
     *
     * @param location
     * @return
     */
    public static void loadProperties(String location) {
        ResourceLoader loder = new PathMatchingResourcePatternResolver();
        Resource resource = loder.getResource(location);
        properties = new Properties();
        try {
            //防止中文乱码
           // EncodedResource encodedResource=new EncodedResource(resource,"UTF-8");
            //InputStream inputStream = encodedResource.getInputStream();
            /**
             * 主要是properties.load 加载时乱码，reader 是可以指定字符集的，并且默认utf-8
             */
            properties.load(new InputStreamReader(resource.getInputStream()));
            //properties.load(inputStream);
            System.out.println(properties);
        } catch (IOException io) {
            log.error("Could not load properties from path:" + location + io.getMessage());
        }
    }

    /**
     * 获取默认信息
     *
     * @param code
     * @return
     */
    public static String getDefaultMsg(Integer code) {
        if (properties == null) {
            loadProperties(msgCodeFileName);
        }
        if (properties != null) {
            return properties.getProperty(code.toString(), "");
        } else {
            return "";
        }
    }

}
