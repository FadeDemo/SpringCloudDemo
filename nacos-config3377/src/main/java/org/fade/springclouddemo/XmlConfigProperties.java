package org.fade.springclouddemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * xml配置属性
 *
 * @author fade
 * @date 2022/08/05
 */
@Data
@ConfigurationProperties("configuration.trans-list")
@Configuration
public class XmlConfigProperties {

    private List<Tran> trans;

    @Data
    public static class Tran {

        private String transCode;

        private String transName;

        private String md5Key;

        private String transClassName;

        private boolean needCheckMD5;

        private boolean test;

    }

}
