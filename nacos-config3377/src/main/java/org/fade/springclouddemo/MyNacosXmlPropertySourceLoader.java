package org.fade.springclouddemo;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.cloud.nacos.parser.AbstractPropertySourceLoader;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>custom {@link com.alibaba.cloud.nacos.parser.NacosXmlPropertySourceLoader}</p>
 *
 * @author fade
 * @date 2022/08/04
 */
public class MyNacosXmlPropertySourceLoader extends AbstractPropertySourceLoader implements PriorityOrdered {

    private static final String DOT = ".";

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    @Override
    public String[] getFileExtensions() {
        return new String[] { "xml" };
    }

    @Override
    protected List<PropertySource<?>> doLoad(String name, Resource resource)
            throws IOException {
        // todo 属性解析不知道是否是必要的
        Map<String, Object> result = new LinkedHashMap<>(32);
        Map<String, Object> nacosDataMap = parseXml2Map(resource);
        flattenedMap(result, nacosDataMap, null);
        return Collections.singletonList(
                new OriginTrackedMapPropertySource(name, result, true));

    }

    private Map<String, Object> parseXml2Map(Resource resource) throws IOException {
        Map<String, Object> map = new LinkedHashMap<>(32);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document document = documentBuilder.parse(resource.getInputStream());
            if (null == document) {
                return null;
            }
//            parseNodeList(document.getChildNodes(), map, "");
            map.putAll(XmlUtil.xmlToMap(document));
        }
        catch (Exception e) {
            throw new IOException("The xml content parse error.", e.getCause());
        }
        return map;
    }

//    private void parseNodeList(NodeList nodeList, Map<String, Object> map,
//                               String parentKey) {
//        if (nodeList == null || nodeList.getLength() < 1) {
//            return;
//        }
//        parentKey = parentKey == null ? "" : parentKey;
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            String value = node.getNodeValue();
//            value = value == null ? "" : value.trim();
//            String name = node.getNodeName();
//            name = name == null ? "" : name.trim();
//
//            if (StringUtils.isEmpty(name)) {
//                continue;
//            }
//
//            String key = StringUtils.isEmpty(parentKey) ? name : parentKey + DOT + name;
//            NamedNodeMap nodeMap = node.getAttributes();
//            parseNodeAttr(nodeMap, map, key);
//            if (node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
//                parseNodeList(node.getChildNodes(), map, key);
//                continue;
//            }
//            if (value.length() < 1) {
//                continue;
//            }
//            map.put(parentKey, value);
//        }
//    }
//
//    private void parseNodeAttr(NamedNodeMap nodeMap, Map<String, Object> map,
//                               String parentKey) {
//        if (null == nodeMap || nodeMap.getLength() < 1) {
//            return;
//        }
//        for (int i = 0; i < nodeMap.getLength(); i++) {
//            Node node = nodeMap.item(i);
//            if (null == node) {
//                continue;
//            }
//            if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
//                if (StringUtils.isEmpty(node.getNodeName())) {
//                    continue;
//                }
//                if (StringUtils.isEmpty(node.getNodeValue())) {
//                    continue;
//                }
//                map.put(String.join(DOT, parentKey, node.getNodeName()),
//                        node.getNodeValue());
//            }
//        }
//    }

}
