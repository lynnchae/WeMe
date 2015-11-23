package com.daoke.mobileserver.ejsino.dao;

import com.daoke.mobileserver.ejsino.model.EjsinoCityInfo;
import com.daoke.mobileserver.ejsino.model.EjsinoFormModel;

import java.util.List;

/**
 * Created by wangzp on 2014/12/16.
 */
public interface IEjsinoDao {

    public List<EjsinoCityInfo> getCityInfo();

    /**
     * 记录e车险订单 根据业务场景因此业务量少，做使用一个表做记录即可
     * @param ejsinoFormModel
     * @return
     */
    public int insertEjsinoInfo(EjsinoFormModel ejsinoFormModel);

    /**
     * 完善e车险订单信息
     * @param ejsinoFormModel
     * @return
     */
    public int updateEjsinoInfo(EjsinoFormModel ejsinoFormModel);

    /**
     * 判断交易号是否存在
     * @param outerno
     * @return
     */
    public int getEjsinoCountByOuterno(String outerno);
}
