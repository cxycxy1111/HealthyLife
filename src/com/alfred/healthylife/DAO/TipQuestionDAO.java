package com.alfred.healthylife.DAO;

import com.alfred.healthylife.Util.SQLHelper;

import java.sql.SQLException;

public class TipQuestionDAO extends DAO {

    private SQLHelper helper;

    public TipQuestionDAO() {
        helper = new SQLHelper();
    }

    public boolean create(long tip_id, String title, long creator, String create_time) {
        String sql = "INSERT INTO tip_question (tip_id,title,creator,creator_type,create_time) " +
                "VALUES (" + tip_id + ",'" + title + "'," + creator + ",1,'" + create_time + "')";
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
