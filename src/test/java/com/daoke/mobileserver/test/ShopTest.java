package com.daoke.mobileserver.test;

import com.daoke.mobileserver.common.model.CommonJsonResult;
import com.daoke.mobileserver.util.*;
import org.codehaus.jackson.JsonParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * User: chenlong
 * Date: 2015/1/6
 * Time: 14:45
 */
public class ShopTest {

    public static  void main(String args[]) throws JAXBException, IOException {

//        Map<String, String> paramWeibo = new HashMap<String, String>();
//        paramWeibo.put("appKey", ConstantsUtil.appKey);
//        paramWeibo.put("secret",  ConstantsUtil.secret);
//        paramWeibo.put("accountID","Tzslpn6Ulz");
//
//        String signWeibo = SHASignature.sign(ParameterUtil.getDaokeSignData(paramWeibo));
//        paramWeibo.put("sign", signWeibo);
//
//        HttpRequester requesterWeibo = new HttpRequester();
//        HttpRespons responsWeibo = requesterWeibo.sendPost("http://192.168.1.207:80/accountapi/v2/disconnectAccount", paramWeibo);
//        String bizid = "";
//        if (responsWeibo.getCode() == ConstantsUtil.HttpStatusCode.OK) {
//            String content = responsWeibo.getContent();
//            CommonJsonResult jsonResult2 = (CommonJsonResult) JsonMapper.fromJson(content, CommonJsonResult.class);
//            if (jsonResult2.getERRORCODE().equals(ConstantsUtil.ERRORCODE_OK)) {
//                Map<String, String> resParam = (Map<String, String>) jsonResult2.getRESULT();
//            System.out.println("resParam:" + resParam);
//            }else{
//                System.out.print(jsonResult2.toString());
//
//            }
//        }
        SendTemplateSMSUtil.sendTemplateSMS( "13062819626","21109","123456","30");
    }
}
