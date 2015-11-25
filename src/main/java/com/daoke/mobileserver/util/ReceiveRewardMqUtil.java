//package com.daoke.mobileserver.util;
//
//import com.daoke.mobileserver.user.service.IUserGradeService;
//import me.daoke.common.mq.client.ReceiveMessage;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
//// * User: chenlong
// * Date: 2015/5/15
// * Time: 13:37
// */
//@Service
//public class ReceiveRewardMqUtil {
//
//    Log log = LogFactory.getLog(ReceiveRewardMqUtil.class);
//
//    @Autowired
//    private ReceiveMessage receiveRochelleMessage;
//
//    @Autowired
//    private IUserGradeService userGradeService;
//
////    @Value("#{apiConfig[sendPersonalWeibo]}")
////    private String sendPersonalWeibo;
////
////
////    @Value("#{apiConfig[callBackReceiveRochelle]}")
////    private String callBackReceiveRochelle;
//
//  // @PostConstruct
//    public void initMq2() {
//        try {
//
//            PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
//
//            final String sendPersonalWeibo = propertiesUtil.getProperty("sendPersonalWeibo");
//            final String callBackReceiveRochelle = propertiesUtil.getProperty("callBackReceiveRochelle");
//
//            //创建新线程运行接收消息
//            ExecutorService threadPool = Executors.newSingleThreadExecutor();
//            final ExecutorService threadPool_thread = Executors.newFixedThreadPool(4);
//            threadPool.execute(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            while (true) {
//                                try {
//                                    //获取消息队列信息（订单ID）
//                                    final String message = receiveRochelleMessage.receive();
//                                    threadPool_thread.execute(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                RuleRewardMq ruleRewardMq = (RuleRewardMq) me.daoke.common.mq.util.JsonMapper.fromJson(message, RuleRewardMq.class);
//                                                System.out.println("ruleRewardMq: "+ ruleRewardMq);
//                                                //预奖励
//                                                Object[] obj = userGradeService.toReward(ruleRewardMq.getAccountID(), ruleRewardMq.getIMEI(), ruleRewardMq.getRuleCode());
//                                                //推送消息
//                                                SendMessageUtil.sendToTerminal(sendPersonalWeibo, callBackReceiveRochelle, (Integer) obj[2], ruleRewardMq.getAccountID());
//                                            } catch (Exception e) {
//                                                log.error("接收消息 处理异常", e);
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    });
//                                } catch (Exception e) {
//                                    log.error("error", e);
//                                    e.printStackTrace();
//                                    log.error("ExceptionMessage: {" + e.getMessage() + "}");
//                                    log.error("StackTrace: ", e);
//                                }
//                            }
//                        }
//                    });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
