package com.daoke.mobileserver.ejsino;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;


/**
 * Created by wangzp on 2014/12/9.
 * 使用jaxb2 操作xml
 */
public class JaxbUtil {

    private static String CHARSET_UTF8 = "utf-8";

    /**
     * 将对象转换为xml
     * @param obj 操作对象
     * @param encoding 编码
     * @return
     */
    public static String object2Xml(Object obj, String encoding) throws  Exception{

        if (null == obj) {
            return null;
        }

        String result = null;
        StringWriter stringWriter = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

            stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);

            result = stringWriter.toString();
        } finally {
            if (null != stringWriter){
                stringWriter.flush();
                stringWriter.close();
            }
        }

        return result;
    }

    /**
     * 将对象转换为xml
     * @param obj
     * @return
     */
    public static String object2Xml(Object obj) throws  Exception{

       return object2Xml(obj, CHARSET_UTF8);
    }

    /**
     * 将xml转换为bean
     * @param xml
     * @param c
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T xml2JavaBean(String xml, Class<T> c) throws Exception{
        T t = null;
        StringReader stringReader = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            stringReader = new StringReader(xml);

            return (T) unmarshaller.unmarshal(stringReader);
        } finally {
            if (null == stringReader){
                stringReader.close();
            }
        }
    }


}
