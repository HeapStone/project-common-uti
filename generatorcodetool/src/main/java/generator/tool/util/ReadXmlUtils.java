package generator.tool.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>Title:用dom4j解析xml的工具类 </p>
 * <p>Description: 读取xml </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2017年12月16日
 */
public class ReadXmlUtils {
	
	/**
	 * <p>Description:通过文件的路径获取xml的document对象 <p>
	  * @param path  文件的路径 
     * @return      返回文档对象 
	 * @author wanglei 2017年12月16日
	 */
	public static Document getXMLByFilePath(String path) {
        if (null == path) {
            return null;
        }  
        Document document = null;  
        try {  
            SAXReader reader = new SAXReader();  
            document = reader.read(new File(path));
        } catch (Exception e) {
		    throw 	new RuntimeException("找不到配置文件!",e);
        }  
        return document;  
    }  
	/**
	 * <p>Description:<p>
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 * @author wanglei 2017年12月16日
	 */
	public static Document getXMLByString(String xmlstr) throws DocumentException{  
        if(xmlstr==""||xmlstr==null){  
            return null;  
        }  
        Document document = DocumentHelper.parseText(xmlstr);  
        return document;  
    }  
	/**
	 * <p>Description:<p>
	 * @param node
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static List<Element> getChildElements(Element node) {  
        if (null == node) {  
            return null;  
        }  
        @SuppressWarnings("unchecked")  
        List<Element> lists = node.elements();  
        return lists;  
    }  
	/**
	 * <p>Description:<p>
	 * @param node
	 * @param childnode
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static Element getChildElement(Element node,String childnode){  
        if(null==node||null == childnode||"".equals(childnode)){  
            return null;  
        }  
        return node.element(childnode);  
    }  
	/**
	 * <p>Description:<p>
	 * @param node
	 * @param arg
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static Map<String, String> getAttributes(Element node,String...arg){  
        if(node==null||arg.length==0){  
            return null;  
        }  
        Map<String, String> attrMap = new HashMap<String,String>();  
        for(String attr:arg){  
            String attrValue = node.attributeValue(attr);  
            attrMap.put(attr, attrValue);  
        }  
        return attrMap;  
    }  
	/**
	 * <p>Description:<p>
	 * @param node
	 * @param attr
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static String getAttribute(Element node,String attr){  
        if(null == node||attr==null||"".equals(attr)){  
            return "";  
        }  
        return node.attributeValue(attr);  
    }  
	/**
	 * <p>Description:<p>
	 * @param parent
	 * @param childName
	 * @param childValue
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static Element addChild(Element parent, String childName, String childValue) {  
        Element child = parent.addElement(childName);// 添加节点元素  
        child.setText(childValue == null ? "" : childValue); // 为元素设值  
        return child;  
    }  
	 /**
	 * <p>Description:<p>
	 * @param document
	 * @param charset
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static String documentToString(Document document, String charset) {  
	        StringWriter stringWriter = new StringWriter();  
	        OutputFormat format = OutputFormat.createPrettyPrint();// 获得格式化输出流  
	        format.setEncoding(charset);// 设置字符集,默认为UTF-8  
	        XMLWriter xmlWriter = new XMLWriter(stringWriter, format);// 写文件流  
	        try {  
	            xmlWriter.write(document);  
	            xmlWriter.flush();  
	            xmlWriter.close();  
	        } catch (IOException e) {  
	            throw new RuntimeException(e);  
	        }  
	        return stringWriter.toString();  
	    }  
	/**
	 * <p>Description:<p>
	 * @param document
	 * @param charset
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public static String documentToStringNoDeclaredHeader(Document document, String charset) {  
        String xml = documentToString(document, charset);  
        return xml.replaceFirst("\\s*<[^<>]+>\\s*", "");  
    }  
	 /**
	 * <p>Description:<p>
	 * @param xml
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public final static Element parseXml(String xml) {  
	        StringReader sr = new StringReader(xml);  
	        SAXReader saxReader = new SAXReader();  
	        Document document = null;  
	        try {  
	            document = saxReader.read(sr);  
	        } catch (DocumentException e) {  
	            e.printStackTrace();  
	        }  
	        Element rootElement = document != null ? document.getRootElement() : null;  
	        return rootElement;  
	    }  
	 /**
	 * <p>Description:<p>
	 * @param e
	 * @param tag
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public final static String getText(Element e, String tag) {  
	        Element _e = e.element(tag);  
	        if (_e != null)  
	            return _e.getText();  
	        else  
	            return null;  
	    }  
	/**
	 * <p>Description:<p>
	 * @param e
	 * @param tag
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public final static String getTextTrim(Element e, String tag) {  
        Element _e = e.element(tag);  
        if (_e != null)  
            return _e.getTextTrim();  
        else  
            return null;  
    } 
	/**
	 * <p>Description:<p>
	 * @param parent
	 * @param tag
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public final static String getTextTrimNotNull(Element parent, String tag) {  
        Element e = parent.element(tag);  
        if (e == null)  
            throw new NullPointerException("节点为空");  
        else  
            return e.getTextTrim();  
    }  
	/**
	 * <p>Description:<p>
	 * @param parent
	 * @param tag
	 * @return
	 * @author wanglei 2017年12月16日
	 */
	public final static Element elementNotNull(Element parent, String tag) {  
        Element e = parent.element(tag);  
        if (e == null)  
            throw new NullPointerException("节点为空");  
        else  
            return e;  
    } 
	 /**
	 * <p>Description:<p>
	 * @param document
	 * @param path
	 * @throws IOException
	 * @author wanglei 2017年12月16日
	 */
	public final static void writeXMLToFile(Document document,String path) throws IOException{  
	        if(document==null||path==null){  
	            return;  
	        }  
	        XMLWriter writer = new XMLWriter(new FileWriter(path));  
	        writer.write(document);  
	        writer.close();  
	    }  
}
