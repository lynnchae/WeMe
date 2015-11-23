package com.daoke.mobileserver.ejsino.service.impl;

import com.daoke.mobileserver.common.service.ServiceException;
import com.daoke.mobileserver.ejsino.dao.IEjsinoDao;
import com.daoke.mobileserver.ejsino.model.EjsinoCityInfo;
import com.daoke.mobileserver.ejsino.model.EjsinoFormModel;
import com.daoke.mobileserver.ejsino.service.IEjsinoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzp on 2014/12/16.
 */
@Service
public class EjsinoServiceImpl  implements IEjsinoService {

    private static  Logger log = LoggerFactory.getLogger(EjsinoServiceImpl.class);

    @Autowired
    private IEjsinoDao ejsinoDao;

    @Override
    @PostConstruct
    public List<EjsinoCityInfo> getCityInfo() {
        return ejsinoDao.getCityInfo();
    }


    public List<EjsinoCityInfo> getParentCityList(){
        List<EjsinoCityInfo> srcList = this.getCityInfo();

        if (null == srcList || srcList.size() <= 0){
            return null;
        }

        List<EjsinoCityInfo> target = new ArrayList<EjsinoCityInfo>();

        for(EjsinoCityInfo city : srcList) {
            if (city == null)
                continue;

            if (0 == city.getParentCode()){
                target.add(city);
            }
        }

        return  target;
    }

    public Map<Integer, List<EjsinoCityInfo>> getCityMap(){
        List<EjsinoCityInfo> srcList = this.getCityInfo();

        if (null == srcList || srcList.size() <= 0){
            return null;
        }

        Map<Integer, List<EjsinoCityInfo>>  map = new HashMap<Integer, List<EjsinoCityInfo>>();
        for(EjsinoCityInfo city : srcList) {
            if (city == null)
                continue;

            if (0 == city.getParentCode()){
                map.put(city.getCityCode(), null);
            }
        }

        for(EjsinoCityInfo city : srcList) {
            if (city == null)
                continue;

            if (map.containsKey(city.getParentCode())) {
                List<EjsinoCityInfo> subList = map.get(city.getParentCode());

                if (subList == null) {
                    subList = new ArrayList<EjsinoCityInfo>();
                    map.put(city.getParentCode(), subList);
                }

                subList.add(city);

            }
        }

        return map;
    }

    @Override
    public int insertEjsinoInfo(EjsinoFormModel ejsinoFormModel) throws ServiceException{
        try {
            return ejsinoDao.insertEjsinoInfo(ejsinoFormModel);
        }catch(DataAccessException dae){
            log.error("ExceptionMessage: 记录e车险表单信息发生数据库访问异常" , dae.getMessage());
            log.error("StackTrace: ",dae);
            dae.printStackTrace();
            throw new ServiceException("记录e车险表单信息发生数据库访问异常 ",dae);
        }
    }

    @Override
    public int updateEjsinoInfo(EjsinoFormModel ejsinoFormModel) throws  ServiceException{
        try{
             return ejsinoDao.updateEjsinoInfo(ejsinoFormModel);
        }catch(DataAccessException dae){
            log.error("ExceptionMessage: 修改e车险表单信息发生数据库访问异常", dae.getMessage());
            log.error("StackTrace: ",dae);
            dae.printStackTrace();
            throw new ServiceException(" 修改e车险表单信息发生数据库访问异常",dae);
        }
    }

    @Override
    public int getEjsinoCountByOuterno(String outerno) {
        return ejsinoDao.getEjsinoCountByOuterno(outerno);
    }

}
