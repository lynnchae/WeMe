package com.daoke.mobileserver.splitwordsearch.service.impl;

import com.daoke.mobileserver.splitwordsearch.service.SplitSearchService;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 拆分词搜索 service 实现层
 * @author zhuoshuang
 * @data 2015-5-13
 */
@Service
public class SplitSearchServiceImpl implements SplitSearchService{

    @Override
    public String[] getKeyWords(String text) {
        //初始化词典
        initDic();
        KeyWordComputer kwc = new KeyWordComputer(3);
        Collection<Keyword> Keywordresult = kwc.computeArticleTfidf(text);
        String[] strArr = new String[Keywordresult.size()+1];
        int index = 0;
        //把当前要搜的全部内容放进去
        strArr[index++] = text;
        Iterator<Keyword> it = Keywordresult.iterator();
        while(it.hasNext()){
            strArr[index] = it.next().getName();
            index++;
        }
        return strArr;
    }

    @Override
    public String[] getToAnalysis(String text) {
        //初始化词典
        initDic();

        List<String> wordList = new ArrayList<String>();
        List<Term> parse = ToAnalysis.parse(text);
        wordList.add(text);
        int index = 1;
        for(int i=0; parse!=null && i<parse.size(); i++){
            //根据词性删选
            if(wordType(parse.get(i))){
                if(judgeRepeat(wordList,parse.get(i).getName())){
                    wordList.add(parse.get(i).getName());
                }
            }

        }

        //转化成数组
        String[] arrStr = new String[wordList.size()];
        arrStr =  wordList.toArray(arrStr);

        return arrStr;
    }

    /**
     * 初始化词典,添加自定义词语
     */
    public void initDic(){
        String userDefineArr = "weme,WeMe,WEME,设置,设备,数据,系统音,提示音,自动分享,家人连线,语音记事本,短信,app,注册,账户,押金密码,忘记密码,登陆密码,手机号,用户名,昵称,播报,频道,微频道," +
                "功能,臣妾做不到,无法定位,微密听你的,+键,++键,吐槽见,重新插电,重启,三色灯,红灯,蓝灯,绿灯,关机,重启,断电,静音,检测,工程模式,麦坏了,按键坏了,申请返修,运费," +
                "换机,换货,包装,变哑巴,开不了机,收不到语音,里程数据,轨迹不准,有效里程,实际里程,gps,成绩单,公里数,摄像头,测速,运营商,网络,信号差,无网络,语音延迟,丢失,道客账户," +
                "换货流程,微密,押金,里程奖励,代理,流量,免费,奖金,怎么提现,提现慢,支付宝,话费,密点,提现时间段,充值,结算,验证短息,验证码,捆绑,绑定,说话时间短,语音短,客服电话,意见反馈";
        String[] arr = userDefineArr.split(",");
        for(String word:arr){
            UserDefineLibrary.insertWord(word, "selfDefine", 1000);
        }
    }

    /**
     * 词性判断
     * @return
     */
    public boolean wordType(Term term){
        boolean flag = true;
        String[] typeArr = new String[]{"w","r","m","q","d","p","c","u","y","o","h","k","a"};
        for(int i=0; i<typeArr.length; i++){
            if(term.getNatrue().natureStr.startsWith(typeArr[i])){
                flag = false;
            }
        }
        return flag;
    }

    /**
     *
     */
    public boolean judgeRepeat(List<String> wordList,String word){
        boolean flag = true;
        if(wordList != null && word != null && !"".equals(word)){
            for(int i=0; i<wordList.size(); i++){
                if(word.equals(wordList.get(i))){
                    flag = false;
                }
            }
        }else{
            flag = false;
        }
        return flag;
    }
}
