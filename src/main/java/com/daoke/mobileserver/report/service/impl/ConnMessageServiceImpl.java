package com.daoke.mobileserver.report.service.impl;

import com.daoke.mobileserver.report.dao.ConnMessgeDao;
import com.daoke.mobileserver.report.dao.HomeConnDao;
import com.daoke.mobileserver.report.dto.ConnMessage;
import com.daoke.mobileserver.report.dto.HomeMessage;
import com.daoke.mobileserver.report.service.ConnMessageService;
import com.daoke.mobileserver.report.service.HomeConnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author wangliming
 * @date 2014-8-11 上午10:10:45
 * @version 1.0
 */
@Service
public class ConnMessageServiceImpl implements ConnMessageService {

	@Autowired
	private ConnMessgeDao connMessgeDao;
	
	@Override
	public boolean addConnMessage(ConnMessage connMessage) {
		return connMessgeDao.addConnMessage(connMessage);
	}

	@Override
	public List<ConnMessage> getConnMessages(String phoneImei, String accountID) {
		return connMessgeDao.getConnMessages(phoneImei, accountID);
	}

	@Override
	public boolean updateConnMessage(String phoneImei, String accountID) {
		return connMessgeDao.updateConnMessage(phoneImei, accountID);
	}

    /**
     *
     * @author wangliming
     * @date 2014-8-8 下午5:35:11
     * @version 1.0
     */
    @Service
    public static class HomeConnServiceImpl implements HomeConnService {

        @Autowired
        private HomeConnDao homeConnDao;

        @Override
        public boolean addHomeMessage(HomeMessage homeMessage) {
            return homeConnDao.addHomeMessage(homeMessage);
        }

        @Override
        public HomeMessage getHomeMessage(String accountID) {
            return homeConnDao.getHomeMessage(accountID);
        }

        @Override
        public boolean updateHomeMessage(String accountID, String phoneImei) {
            return homeConnDao.updateHomeMessage(accountID, phoneImei);
        }

        @Override
        public HomeMessage getHomeMessage(String accountID, String phoneImei) {
            return homeConnDao.getHomeMessage(accountID, phoneImei);
        }

    }
}
