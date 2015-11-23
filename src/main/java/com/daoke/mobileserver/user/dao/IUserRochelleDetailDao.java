package com.daoke.mobileserver.user.dao;


import com.daoke.mobileserver.user.entity.UserRochelleDetail;

import java.util.List;
import java.util.Map;

/**用户谢尔明细Dao
 * Created by chenmaomao on 2015/4/1.
 */
public interface IUserRochelleDetailDao {
    /**
     * 添加用户谢尔明细
     * @param userRochelleDetail
     * @throws Exception
     */
    public void insertUserRochelleDetail(UserRochelleDetail userRochelleDetail)throws Exception;

    /**
     * 根据用户ID和规则码查询用户谢尔明细
     * @param userRochelleDetail
     * @return
     * @throws Exception
     */
    public List<UserRochelleDetail> queryByAccountIDAndRuleCode(UserRochelleDetail userRochelleDetail)throws Exception;

    /**
     * 獲取用戶首頁數據詳情
     * @param accountID
     * @return
     * @throws Exception
     */
    public Map<String,Object> getDetailUserList(String accountID,List accountIDlist);

    /**
     * 修改谢尔值 并修改用户等级
     * @param map
     * @return
     */
    public int updateUserGradeWhitTotalChelle(Map map);

    /**
     * 修改明细表中领取状态
     * @param receiveStatus
     * @param accountID
     * @return
     */
    public int updateUserRochelleReceiveStatus(int receiveStatus,String accountID ,Integer recordID);
}
