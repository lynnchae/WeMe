package com.daoke.mobileserver.ejsino.dao.impl;

import com.daoke.mobileserver.ejsino.dao.IEjsinoDao;
import com.daoke.mobileserver.ejsino.model.EjsinoCityInfo;
import com.daoke.mobileserver.ejsino.model.EjsinoCityInfoRowMapper;
import com.daoke.mobileserver.ejsino.model.EjsinoFormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangzp on 2014/12/16.
 */
@Repository
public class EjsinoDaoImpl implements IEjsinoDao {


    @Autowired
    private JdbcTemplate wemeJdbcTemplate;

    @Override
    public List<EjsinoCityInfo> getCityInfo() {

        String sql = "select cityCode,cityName,pinYin,abbreviate,parentCode from ejsinoCityInfo";

        return this.wemeJdbcTemplate.query(sql, new EjsinoCityInfoRowMapper());
    }

    @Override
    public int insertEjsinoInfo(EjsinoFormModel ejsinoFormModel) {
        String sql = "insert into ejsinoOrder (version,ordertype,username,outerno,companyno,insuredperson,plcstartdate,plcenddate,licenseno," +
                "           nolicenseflag,ownername,owneridno,ownercerttype,ownercertno,citycode,cardno,firstregisterdate,vehiclemodelname," +
                "               vehicleid,vehicleframeno,engineno,vehicleinvoiceno,vehicleinvoicedate,runcardcertificatedate,forcebegindate,bizbegindate," +
                "taxvehicletype,fueltype,specialcarflag,specialcardate,persontaxcode,taxticketno,taxtickettype,taxbureauname,settledaddress,vehicleseats," +
                "averagemile,trafficviolation,bizquestion,bizanswer,forcequestion,forceanswer,vehiclecode,vehiclecodename,sessionid,trafficinsurance," +
                "traveltax,remark1,remark2,remark3,remark4,remark5,remark6,remark7,remark8,remark9,remark10,remark11,remark12,remark13,remark14,remark15," +
                "step,linkInfo_name,linkInfo_mobile,linkInfo_address,linkInfo_invoice,linkInfo_zipcode,linkInfo_email,linkInfo_paytype,linkInfo_realpaymode," +
                "insurInfo_name,insurInfo_certtype,insurInfo_certno,insurInfo_sex,insurInfo_birth,insurInfo_email,aplInfo_name,aplInfo_certtype," +
                "aplInfo_certno,aplInfo_sex,aplInfo_birth,aplInfo_email,businesspremium,forcepremium,vehicletaxamount,realpremium,totalremium," +
                "configbeforejudge,createDate,isValid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] args = new Object[]{ejsinoFormModel.getVersion(), ejsinoFormModel.getOrdertype(), ejsinoFormModel.getUsername(), ejsinoFormModel.getOuterno(), ejsinoFormModel.getCompanyno(), ejsinoFormModel.getInsuredperson(), ejsinoFormModel.getPlcstartdate(), ejsinoFormModel.getPlcenddate(), ejsinoFormModel.getLicenseno(), ejsinoFormModel.getNolicenseflag(), ejsinoFormModel.getOwnername(), ejsinoFormModel.getOwneridno(), ejsinoFormModel.getOwnercerttype(), ejsinoFormModel.getOwnercertno(), ejsinoFormModel.getCitycode(), ejsinoFormModel.getCardno(), ejsinoFormModel.getFirstregisterdate() == "" ? null : ejsinoFormModel.getFirstregisterdate(), ejsinoFormModel.getVehiclemodelname(), ejsinoFormModel.getVehicleid(), ejsinoFormModel.getVehicleframeno(), ejsinoFormModel.getEngineno(), ejsinoFormModel.getVehicleinvoiceno(), ejsinoFormModel.getVehicleinvoicedate(), ejsinoFormModel.getRuncardcertificatedate(), ejsinoFormModel.getForcebegindate(), ejsinoFormModel.getBizbegindate(), ejsinoFormModel.getTaxvehicletype(), ejsinoFormModel.getFueltype(), ejsinoFormModel.getSpecialcarflag(), ejsinoFormModel.getSpecialcardate(), ejsinoFormModel.getPersontaxcode(), ejsinoFormModel.getTaxticketno(), ejsinoFormModel.getTaxtickettype(), ejsinoFormModel.getTaxbureauname(), ejsinoFormModel.getSettledaddress(), ejsinoFormModel.getVehicleseats(), ejsinoFormModel.getAveragemile(), ejsinoFormModel.getTrafficviolation(), ejsinoFormModel.getBizquestion(), ejsinoFormModel.getBizanswer(), ejsinoFormModel.getForcequestion(), ejsinoFormModel.getForceanswer(), ejsinoFormModel.getVehiclecode(), ejsinoFormModel.getVehiclecodename(), ejsinoFormModel.getSessionid(), ejsinoFormModel.getTrafficinsurance(), ejsinoFormModel.getTraveltax(), ejsinoFormModel.getRemark1(), ejsinoFormModel.getRemark2(), ejsinoFormModel.getRemark3(), ejsinoFormModel.getRemark4(), ejsinoFormModel.getRemark5(), ejsinoFormModel.getRemark6(), ejsinoFormModel.getRemark7(), ejsinoFormModel.getRemark8(), ejsinoFormModel.getRemark9(), ejsinoFormModel.getRemark10(), ejsinoFormModel.getRemark11(), ejsinoFormModel.getRemark12(), ejsinoFormModel.getRemark13(), ejsinoFormModel.getRemark14(), ejsinoFormModel.getRemark15(), ejsinoFormModel.getStep(), ejsinoFormModel.getLinkInfo_name(), ejsinoFormModel.getLinkInfo_mobile(), ejsinoFormModel.getLinkInfo_address(), ejsinoFormModel.getLinkInfo_invoice(), ejsinoFormModel.getLinkInfo_zipcode(), ejsinoFormModel.getLinkInfo_email(), ejsinoFormModel.getLinkInfo_paytype(), ejsinoFormModel.getLinkInfo_realpaymode(), ejsinoFormModel.getInsurInfo_name(), ejsinoFormModel.getInsurInfo_certtype(), ejsinoFormModel.getInsurInfo_certno(), ejsinoFormModel.getInsurInfo_sex(), ejsinoFormModel.getInsurInfo_birth(), ejsinoFormModel.getInsurInfo_email(), ejsinoFormModel.getAplInfo_name(), ejsinoFormModel.getAplInfo_certtype(), ejsinoFormModel.getAplInfo_certno(), ejsinoFormModel.getAplInfo_sex(), ejsinoFormModel.getAplInfo_birth(), ejsinoFormModel.getAplInfo_email(), ejsinoFormModel.getBusinesspremium(), ejsinoFormModel.getForcepremium(), ejsinoFormModel.getVehicletaxamount(), ejsinoFormModel.getRealpremium(), ejsinoFormModel.getTotalremium(), ejsinoFormModel.getConfigbeforejudge(),new Date(),1};
        return this.wemeJdbcTemplate.update(sql, args);
    }

    @Override
    public int updateEjsinoInfo(EjsinoFormModel ejsinoFormModel) {
        String sql = "update ejsinoOrder set version=?,ordertype=?,username=?,companyno=?,insuredperson=?,plcstartdate=?,plcenddate=?,licenseno=?,nolicenseflag=?,ownername=?,owneridno=?,ownercerttype=?,ownercertno=?,citycode=?,cardno=?,firstregisterdate=?,vehiclemodelname=?,vehicleid=?,vehicleframeno=?,engineno=?,vehicleinvoiceno=?,vehicleinvoicedate=?,runcardcertificatedate=?,forcebegindate=?,bizbegindate=?,taxvehicletype=?,fueltype=?,specialcarflag=?,specialcardate=?,persontaxcode=?,taxticketno=?,taxtickettype=?,taxbureauname=?,settledaddress=?,vehicleseats=?,averagemile=?,trafficviolation=?,bizquestion=?,bizanswer=?,forcequestion=?,forceanswer=?,vehiclecode=?,vehiclecodename=?,sessionid=?,trafficinsurance=?,traveltax=?,remark1=?,remark2=?,remark3=?,remark4=?,remark5=?,remark6=?,remark7=?,remark8=?,remark9=?,remark10=?,remark11=?,remark12=?,remark13=?,remark14=?,remark15=?,step=?,linkInfo_name=?,linkInfo_mobile=?,linkInfo_address=?,linkInfo_invoice=?,linkInfo_zipcode=?,linkInfo_email=?,linkInfo_paytype=?,linkInfo_realpaymode=?,insurInfo_name=?,insurInfo_certtype=?,insurInfo_certno=?,insurInfo_sex=?,insurInfo_birth=?,insurInfo_email=?,aplInfo_name=?,aplInfo_certtype=?,aplInfo_certno=?,aplInfo_sex=?,aplInfo_birth=?,aplInfo_email=?,businesspremium=?,forcepremium=?,vehicletaxamount=?,realpremium=?,totalremium=?,configbeforejudge=? ,modifyDate = ? where outerno = ?";
        Object[] args = new Object[]{ejsinoFormModel.getVersion(), ejsinoFormModel.getOrdertype(), ejsinoFormModel.getUsername(), ejsinoFormModel.getCompanyno(), ejsinoFormModel.getInsuredperson(), ejsinoFormModel.getPlcstartdate(), ejsinoFormModel.getPlcenddate(), ejsinoFormModel.getLicenseno(), ejsinoFormModel.getNolicenseflag(), ejsinoFormModel.getOwnername(), ejsinoFormModel.getOwneridno(), ejsinoFormModel.getOwnercerttype(), ejsinoFormModel.getOwnercertno(), ejsinoFormModel.getCitycode(), ejsinoFormModel.getCardno(), ejsinoFormModel.getFirstregisterdate() == "" ? null : ejsinoFormModel.getFirstregisterdate(), ejsinoFormModel.getVehiclemodelname(), ejsinoFormModel.getVehicleid(), ejsinoFormModel.getVehicleframeno(), ejsinoFormModel.getEngineno(), ejsinoFormModel.getVehicleinvoiceno(), ejsinoFormModel.getVehicleinvoicedate(), ejsinoFormModel.getRuncardcertificatedate(), ejsinoFormModel.getForcebegindate(), ejsinoFormModel.getBizbegindate(), ejsinoFormModel.getTaxvehicletype(), ejsinoFormModel.getFueltype(), ejsinoFormModel.getSpecialcarflag(), ejsinoFormModel.getSpecialcardate(), ejsinoFormModel.getPersontaxcode(), ejsinoFormModel.getTaxticketno(), ejsinoFormModel.getTaxtickettype(), ejsinoFormModel.getTaxbureauname(), ejsinoFormModel.getSettledaddress(), ejsinoFormModel.getVehicleseats(), ejsinoFormModel.getAveragemile(), ejsinoFormModel.getTrafficviolation(), ejsinoFormModel.getBizquestion(), ejsinoFormModel.getBizanswer(), ejsinoFormModel.getForcequestion(), ejsinoFormModel.getForceanswer(), ejsinoFormModel.getVehiclecode(), ejsinoFormModel.getVehiclecodename(), ejsinoFormModel.getSessionid(), ejsinoFormModel.getTrafficinsurance(), ejsinoFormModel.getTraveltax(), ejsinoFormModel.getRemark1(), ejsinoFormModel.getRemark2(), ejsinoFormModel.getRemark3(), ejsinoFormModel.getRemark4(), ejsinoFormModel.getRemark5(), ejsinoFormModel.getRemark6(), ejsinoFormModel.getRemark7(), ejsinoFormModel.getRemark8(), ejsinoFormModel.getRemark9(), ejsinoFormModel.getRemark10(), ejsinoFormModel.getRemark11(), ejsinoFormModel.getRemark12(), ejsinoFormModel.getRemark13(), ejsinoFormModel.getRemark14(), ejsinoFormModel.getRemark15(), ejsinoFormModel.getStep(), ejsinoFormModel.getLinkInfo_name(), ejsinoFormModel.getLinkInfo_mobile(), ejsinoFormModel.getLinkInfo_address(), ejsinoFormModel.getLinkInfo_invoice(), ejsinoFormModel.getLinkInfo_zipcode(), ejsinoFormModel.getLinkInfo_email(), ejsinoFormModel.getLinkInfo_paytype(), ejsinoFormModel.getLinkInfo_realpaymode(), ejsinoFormModel.getInsurInfo_name(), ejsinoFormModel.getInsurInfo_certtype(), ejsinoFormModel.getInsurInfo_certno(), ejsinoFormModel.getInsurInfo_sex(), ejsinoFormModel.getInsurInfo_birth(), ejsinoFormModel.getInsurInfo_email(), ejsinoFormModel.getAplInfo_name(), ejsinoFormModel.getAplInfo_certtype(), ejsinoFormModel.getAplInfo_certno(), ejsinoFormModel.getAplInfo_sex(), ejsinoFormModel.getAplInfo_birth(), ejsinoFormModel.getAplInfo_email(), ejsinoFormModel.getBusinesspremium(), ejsinoFormModel.getForcepremium(), ejsinoFormModel.getVehicletaxamount(), ejsinoFormModel.getRealpremium(), ejsinoFormModel.getTotalremium(), ejsinoFormModel.getConfigbeforejudge(),new Date(), ejsinoFormModel.getOuterno()};

        return this.wemeJdbcTemplate.update(sql,args);
    }

    @Override
    public int getEjsinoCountByOuterno(String outerno) {
        String sql = "select count(*) from ejsinoOrder where outerno = ?";
        return this.wemeJdbcTemplate.queryForInt(sql,new Object[]{outerno});
    }


}
