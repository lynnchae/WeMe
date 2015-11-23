//package com.daoke.mobileserver.filter;
//
//import com.daoke.mobileserver.user.service.IUserGradeService;
//import com.daoke.mobileserver.util.RuleRewardMq;
//import com.daoke.mobileserver.util.SendMessageUtil;
//import org.apache.commons.codec.language.bm.Rule;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * User: chenlong
// * Date: 2015/5/14
// * Time: 15:39
// */
//public class ReceiveRochellePushListener implements ServletContextListener {
//
//    Log log = LogFactory.getLog(ReceiveRochellePushListener.class);
//
//
//    @Autowired
//    private ReceiveMessage receiveRochelleMessage;
//
//    @Autowired
//    private IUserGradeService userGradeService;
//
//    @Value("#{apiConfig[sendPersonalWeibo]}")
//    private String sendPersonalWeibo;
//
//
//    @Value("#{apiConfig[callBackReceiveRochelle]}")
//    private String callBackReceiveRochelle;
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//
//        //注入
//       // ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
//       // receiveRochelleMessage = (ReceiveMessage) ac.getBean("receiveRochelleMessage");
//        //userGradeService = (IUserGradeService) ac.getBean("userGradeService");
//
//        //创建新线程运行接收消息
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        final ExecutorService threadPool_thread = Executors.newFixedThreadPool(20);
//        threadPool.execute(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            try {
//                                //获取消息队列信息（订单ID）
//                                final String message = receiveRochelleMessage.receive();
//                                threadPool_thread.execute(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            RuleRewardMq ruleRewardMq = (RuleRewardMq) JsonMapper.fromJson(message, RuleRewardMq.class);
//                                             //预奖励
//                                            Object[] obj = userGradeService.toReward(ruleRewardMq.getAccountID(), ruleRewardMq.getIMEI(), ruleRewardMq.getRuleCode());
//                                            //推送消息
//                                            SendMessageUtil.sendToTerminal(sendPersonalWeibo,callBackReceiveRochelle,(Integer)obj[2], ruleRewardMq.getAccountID());
//                                        } catch (Exception e) {
//                                            log.error("接收消息 处理异常", e);
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
//                            } catch (Exception e) {
//                                log.error("error", e);
//                                e.printStackTrace();
//                                log.error("ExceptionMessage: {" + e.getMessage() + "}");
//                                log.error("StackTrace: ", e);
//                            }
//                        }
//                    }
//                });
//
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//
//    }
//}
