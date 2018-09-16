package generator.tool.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * <p>Title: xml 与list、josn、map之间转换</p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月16日
 */
public class XmlConverUtil {
	// map to xml 
    /**
     * <p>Description:map转xml<p>
     * @param map
     * @return
     * @author wanglei 2017年12月16日
     */
    public static String maptoXml(Map<?,?> map) {  
        Document document = DocumentHelper.createDocument();  
        Element nodeElement = document.addElement("node");  
        for (Object obj : map.keySet()) {  
            Element keyElement = nodeElement.addElement("key");  
            keyElement.addAttribute("label", String.valueOf(obj));  
            keyElement.setText(String.valueOf(map.get(obj)));  
        }  
        return doc2String(document);  
    }  
  
      // list to xml 
    /**
     * <p>Description:list转换xml<p>
     * @param list
     * @return
     * @throws Exception
     * @author wanglei 2017年12月16日
     */
    public static String listtoXml(List<?> list) throws Exception {  
        Document document = DocumentHelper.createDocument();  
        Element nodesElement = document.addElement("nodes");  
        int i = 0;  
        for (Object o : list) {  
            Element nodeElement = nodesElement.addElement("node");  
            if (o instanceof Map) {  
                for (Object obj : ((Map<?,?>) o).keySet()) {  
                    Element keyElement = nodeElement.addElement("key");  
                    keyElement.addAttribute("label", String.valueOf(obj));  
                    keyElement.setText(String.valueOf(((Map<?,?>) o).get(obj)));  
                }  
            } else {  
                Element keyElement = nodeElement.addElement("key");  
                keyElement.addAttribute("label", String.valueOf(i));  
                keyElement.setText(String.valueOf(o));  
            }  
            i++;  
        }  
        return doc2String(document);  
    }  
  
     // json to xml 
    /**
     * <p>Description:json转xml<p>
     * @param json
     * @return
     * @author wanglei 2017年12月16日
     */
    public static String jsontoXml(String json) {  
        try {  
            XMLSerializer serializer = new XMLSerializer();  
            JSON jsonObject = JSONSerializer.toJSON(json);  
            return serializer.write(jsonObject);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    // xml to map 
    /**
     * <p>Description:xml转map<p>
     * @param xml
     * @return
     * @author wanglei 2017年12月16日
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map xmltoMap(String xml) {  
        try {  
            Map map = new HashMap<>();  
            Document document = DocumentHelper.parseText(xml);  
            Element nodeElement = document.getRootElement();  
            List<?> node = nodeElement.elements();  
            for (Iterator<?> it = node.iterator(); it.hasNext();) {  
                Element elm = (Element) it.next();  
                map.put(elm.attributeValue("label"), elm.getText());  
                elm = null;  
            }  
            node = null;  
            nodeElement = null;  
            document = null;  
            return map;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    //xml to list 
    /**
     * <p>Description:xml转list<p>
     * @param xml
     * @return
     * @author wanglei 2017年12月16日
     */
    @SuppressWarnings("rawtypes")
	public static List xmltoList(String xml) {  
        try {  
            List<Map> list = new ArrayList<Map>();  
            Document document = DocumentHelper.parseText(xml);  
            Element nodesElement = document.getRootElement();  
            List nodes = nodesElement.elements();  
            for (Iterator its = nodes.iterator(); its.hasNext();) {  
                Element nodeElement = (Element) its.next();  
                Map map = xmltoMap(nodeElement.asXML());  
                list.add(map);  
                map = null;  
            }  
            nodes = null;  
            nodesElement = null;  
            document = null;  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
     // xml to json 
    /**
     * <p>Description:xml转json<p>
     * @param xml
     * @return
     * @author wanglei 2017年12月16日
     */
    public static String xmltoJson(String xml) {  
        XMLSerializer xmlSerializer = new XMLSerializer();  
        return xmlSerializer.read(xml).toString();  
    }  
  
    // Document to xml
    /**
     * <p>Description:Document转xml<p>
     * @param document
     * @return
     * @author wanglei 2017年12月16日
     */
    public static String doc2String(Document document) {  
        String s = "";  
        try {  
            // 
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            //
            OutputFormat format = new OutputFormat(" ", true, "UTF-8");  
            XMLWriter writer = new XMLWriter(out, format);  
            writer.write(document);  
            s = out.toString("UTF-8");  
            out.close();
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return s;  
    }  
    
}
