/*
package com.daoke.mobileserver.ejsino;

import com.daoke.mobileserver.ejsino.model.request.*;
import com.daoke.mobileserver.ejsino.model.response.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

*/
/**
 * Created by wangzp on 2014/12/9.
 *//*

public class EjsinoTest {

    private String URL = "http://testcar.ejsino.net/agentApiCarServlet";


    @Test
    public void queryCarType() {

        StringBuilder sb = new StringBuilder();


        String version = "2";

        String ordertype = "G";

        String username = "test";

        String password = "123";

        String companyno = "0012";
        String citycode = "110100";
        String name = "test";

        String plcstartdate = "2014-12-12";
        String plcenddate = "2015-12-12";
        String licenseno = "";
        String nolicenseflag = "1";
        String  ownername ="test";
        String ownercerttype ="01";
        String ownercertno = "411323199012314410";


        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><PackageList><Package><Header><version>");
        sb.append(version);
        sb.append("</version><ordertype>");
        sb.append(ordertype);
        sb.append("</ordertype><username>");
        sb.append(username);
        sb.append("</username><password>");
        sb.append(password);
        sb.append("</password></Header>");
        sb.append("<Request><AreaInfo><companyno>");
        sb.append(companyno);
        sb.append("</companyno><citycode>");
        sb.append(citycode);
        sb.append("</citycode></AreaInfo><InsurInfo><name>");
        sb.append(name);
        sb.append("</name></InsurInfo><PlcInfo><plcstartdate>");
        sb.append(plcstartdate);
        sb.append("</plcstartdate><plcenddate>");
        sb.append(plcenddate);
        sb.append("</plcenddate></PlcInfo><VehicleInfo><licenseno>");
        sb.append(licenseno);
        sb.append("</licenseno><nolicenseflag>");
        sb.append(nolicenseflag);
        sb.append("</nolicenseflag><ownername>");
        sb.append(ownername);
        sb.append("</ownername><ownercerttype>");
        sb.append(ownercerttype);
        sb.append("</ownercerttype><ownercertno>");
        sb.append(ownercertno);
        sb.append("</ownercertno></VehicleInfo></Request></Package></PackageList>");


        System.out.print(sb.toString());

        String url = "http://testcar.ejsino.net/agentApiCarServlet";

        try {
            String content = ECarHttpRequestUtil.sendRequest(url, sb.toString());

            _Res_PackageList packageList = JaxbUtil.xml2JavaBean(content, _Res_PackageList.class);

            int statuscode = packageList.get_package().getHeader().getStatuscode();

            if (EjsinoConstants.ResponseStatus.COMPLETE_ELEMENTS == statuscode) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testJaxb(){
        _Res_PackageList packageList = new _Res_PackageList();

        _Res_Header header = new _Res_Header();
        header.setMessage("sss");
        header.setOrdertype("2");
        header.setOuterno("22222");
        header.setVersion(2);
        header.setStatuscode(2);


        _Res_Package pack = new _Res_Package();
        pack.setHeader(header);

        packageList.set_package(pack);


        _Res_Response response = new _Res_Response();

        List<_Res_Tag> tags1 = new ArrayList<_Res_Tag>();
        _Res_Tag tag1 = new _Res_Tag();

        List<_Res_Definition> definitions1 = new ArrayList<_Res_Definition>();

        _Res_Definition definition1 =  new _Res_Definition();
        definition1.setValue("sdfdsfds");
        definition1.setName("1111");
        _Res_Definition definition2 =  new _Res_Definition();
        definition2.setName("1111");
        definitions1.add(definition1);
        definitions1.add(definition2);
        tag1.setDefinitions(definitions1);

        _Res_Tag tag2 = new _Res_Tag();
        List<_Res_Definition> definitions2 = new ArrayList<_Res_Definition>();

        _Res_Definition definition3 =  new _Res_Definition();
        definition3.setName("1111");
        definitions2.add(definition3);
        tag2.setDefinitions(definitions2);


        tags1.add(tag1);
        tags1.add(tag2);

       // response.setTagsCarRiskList(tags1);

        response.setTagsCarSubjList(tags1);

        pack.setResponse(response);

        try {
            String context = JaxbUtil.object2Xml(packageList);
            System.out.print(context);

            _Res_PackageList p = JaxbUtil.xml2JavaBean(context, _Res_PackageList.class);

            System.out.print(p.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        @Test
    public void test1() throws  Exception{
            int version = 2;

            String ordertype = "G";

            String username = "test";

            String password = "123";

            String companyno = "0012";
            String citycode = "110100";
            String name = "test";

            String licenseno = "";
            int nolicenseflag = 1;
            String ownername = "test";
            String ownercerttype = "01";
            String ownercertno = "411323199012314410";


            _Req_PackageList packageList = new _Req_PackageList();


            _Req_Package req_package = new _Req_Package();

            // =========== _Req_Header  ===========
            _Req_Header req_header =  new _Req_Header();
            req_header.setOrdertype(ordertype);
            req_header.setPassword(password);
            req_header.setUsername(username);
            req_header.setVersion(version);

            req_package.setHeader(req_header);

            // =========== _Req_Header  ===========

            _Req_Request req_request =  new _Req_Request();

            _Req_AreaInfo req_areaInfo = new _Req_AreaInfo();
            req_areaInfo.setCompanyno(companyno);
            req_areaInfo.setCitycode(citycode);

            req_request.setAreaInfo(req_areaInfo);


            _Req_InsurInfo req_insurInfo = new _Req_InsurInfo();
            req_insurInfo.setName(name);

            req_request.setInsurInfo(req_insurInfo);

            _Req_PlcInfo req_plcInfo = new _Req_PlcInfo();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = new Date();
            c.setTime(startdate);
            c.add(Calendar.YEAR, 1);
            req_plcInfo.setPlcenddate(sdf.format(c.getTime()));
            req_plcInfo.setPlcstartdate(sdf.format(startdate));

            req_request.setPlcInfo(req_plcInfo);


            _Req_VehicleInfo req_vehicleInfo = new _Req_VehicleInfo();
            req_vehicleInfo.setLicenseno(licenseno);
            req_vehicleInfo.setNolicenseflag(nolicenseflag);
            req_vehicleInfo.setOwnername(ownername);
            req_vehicleInfo.setOwnercerttype(ownercerttype);
            req_vehicleInfo.setOwnercertno(ownercertno);

            req_request.setVehicleInfo(req_vehicleInfo);


            req_package.setRequest(req_request);
            packageList.set_package(req_package);

            String xml = JaxbUtil.object2Xml(packageList, "GBK");

            System.out.print(xml);
            String url = "http://testcar.ejsino.net/agentApiCarServlet";

            try {
                String content = ECarHttpRequestUtil.sendRequest(url, xml);

                _Res_PackageList res_packageList = JaxbUtil.xml2JavaBean(content, _Res_PackageList.class);

                int statuscode = res_packageList.get_package().getHeader().getStatuscode();

                if (EjsinoConstants.ResponseStatus.COMPLETE_ELEMENTS == statuscode) {

                    String vehicleid = "car1418195787541";
                    String vehiclemodelname = "雅阁HG7301A 3.0 自动档 2006 参考价（303700）";
                    String vehicleframeno = "HJHUIYIYIYUIY7867";
                    String engineno = "2343241234";
                    String firstregisterdate = "2014-12-10";
                    String bizbegindate = "2014-12-11";
                    req_vehicleInfo.setSpecialcarflag(0);
                    String forcebegindate = "2014-12-19";
                    String vehicleinvoicedate = "2014-12-10";
                    String vehicleinvoiceno = "32432151234";
                    String owneridno = "411323199012314410";

                    String outerno = "0000002194";
                    String sessionId = "JSESSIOIN3423432434324";
                    String taxvehicletype = "K11";

                    String specialcardate = "";
                    req_vehicleInfo.setSpecialcardate(specialcardate);

                    req_vehicleInfo.setVehicleid(vehicleid);
                    req_vehicleInfo.setVehiclemodelname(vehiclemodelname);
                    req_vehicleInfo.setVehicleframeno(vehicleframeno);
                    req_vehicleInfo.setEngineno(engineno);

                    req_vehicleInfo.setFirstregisterdate(firstregisterdate);


                    req_vehicleInfo.setBizbegindate(bizbegindate);

                    req_vehicleInfo.setForcebegindate(forcebegindate);

                    req_vehicleInfo.setVehicleinvoicedate(vehicleinvoicedate);


                    req_vehicleInfo.setVehicleinvoiceno(vehicleinvoiceno);

                    req_vehicleInfo.setTaxvehicletype(taxvehicletype);

                    req_vehicleInfo.setOwneridno(owneridno);




                    req_header.setOuterno(outerno);

                    req_vehicleInfo.setSessionid(sessionId);

                    String xml2 = JaxbUtil.object2Xml(packageList, "GBK");
                    String content2 = ECarHttpRequestUtil.sendRequest(url, xml2);

                    _Res_PackageList res_packageList2 = JaxbUtil.xml2JavaBean(content, _Res_PackageList.class);

                    int statuscode2 = res_packageList.get_package().getHeader().getStatuscode();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    @Test
    public void test222() throws Exception{
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\n" +
                "<PackageList xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "\t<Package>\n" +
                "\t\t<Header>\n" +
                "\t\t\t<version>2</version>\n" +
                "\t\t\t<ordertype>6</ordertype>\n" +
                "\t\t\t<statuscode>0</statuscode>\n" +
                "\t\t\t<outerno>7887979233</outerno>\n" +
                "\t\t\t<message>\n" +
                "\t\t\t\t<![CDATA[确认告知单!]]>\n" +
                "\t\t\t</message>\n" +
                "\t\t</Header>\n" +
                "\t\t<Response>\n" +
                "\t\t\t<Inform>\n" +
                "\t\t\t\t<bizInform>\n" +
                "\t\t\t\t\thttp://220.171.28.152:9080/nsp/vehicle/initFloatNotice.do?orderNo=110143D0110101201412000090&businessCode=11\n" +
                "\t\t\t\t</bizInform>\n" +
                "\t\t\t\t<forceInform>\n" +
                "\t\t\t\t\thttp://220.171.28.152:9080/nsp/vehicle/initFloatNotice.do?orderNo=110143D0110101201412000090&businessCode=12\n" +
                "\t\t\t\t</forceInform>\n" +
                "\t\t\t\t<appnt>\n" +
                "\t\t\t\t\t1.本保单在保险期限内，免费享有全国范围内在线故障排除、电瓶搭电、紧急加水、紧急送油、换胎、现场小修、紧急拖车等非事故车辆道路救援服务，详情请致电我司客服专线95585咨询。\n" +
                "\t\t\t\t</appnt>\n" +
                "\t\t\t\t<remark>\n" +
                "\t\t\t\t\t本投保人兹声明本投保单各项填写内容及提供的相关资料真实有效。如上年在贵公司投保，仍使用上年的投保资料，且保证所有的资料在续保时亦真实有效，如相关资料信息发生变更，则重新提交，保险人应以最新提交的投保资料为准。\n" +
                "\t\t\t\t</remark>\n" +
                "\t\t\t</Inform>\n" +
                "\t\t</Response>\n" +
                "\t</Package>\n" +
                "</PackageList>";


        xml.indexOf("<bizInform>");

        _Res_PackageList res_packageList = JaxbUtil.xml2JavaBean(xml, _Res_PackageList.class);

        int statuscode = res_packageList.get_package().getHeader().getStatuscode();
    }
}
*/
