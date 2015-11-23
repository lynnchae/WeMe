package com.daoke.mobileserver.splitwordsearch.dto;

import com.daoke.mobileserver.splitwordsearch.entity.AskAndAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhuoshaung on 2015/5/13.
 */
public class AskAndAnswerRowMapper implements RowMapper<AskAndAnswer> {
    @Override
    public AskAndAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        AskAndAnswer askAndAnswer = new AskAndAnswer();
        askAndAnswer.setId(rs.getInt("id"));
        askAndAnswer.setAnswer(rs.getString("answer"));
        askAndAnswer.setQuestion(rs.getString("question"));
        askAndAnswer.setFileUrl(rs.getString("fileUrl"));
        askAndAnswer.setQuestionType(rs.getInt("questionType"));
        askAndAnswer.setCreateDate(rs.getDate("createDate"));
        askAndAnswer.setUpdateDate(rs.getDate("updateDate"));
        return askAndAnswer;
    }
}
