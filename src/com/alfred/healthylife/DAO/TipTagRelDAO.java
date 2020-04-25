package com.alfred.healthylife.DAO;

import com.alfred.healthylife.Util.SQLHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TipTagRelDAO extends DAO {

    private SQLHelper helper;

    public TipTagRelDAO() {
        helper = new SQLHelper();
    }

    public boolean create(long tip_id, long tag_id) {
        String sql = "INSERT INTO tip_tag (tip_id,tag_id) VALUES (" + tip_id + "," + tag_id + ")";
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long tip_id, long tag_id) {
        String sql = "UPDATE tip_tag SET del=1 " +
                "WHERE tip_id=" + tip_id + " AND tag_id=" + tag_id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean recover(long tip_id, long tag_id) {
        String sql = "UPDATE tip_tag SET del=0 " +
                "WHERE tip_id=" + tip_id + " AND tag_id=" + tag_id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<HashMap<String, Object>> query(long tip_id, long tag_id) {
        String sql = "SELECT * FROM tip_tag " +
                "WHERE tip_id=" + tip_id + " AND tag_id=" + tag_id;
        return helper.query(sql);
    }

    public ArrayList<HashMap<String, Object>> queryByTip(long tip_id) {
        String sql = "SELECT b.id,b.name FROM tip_tag a " +
                "LEFT JOIN tag b ON a.tip_id=b.id " +
                "WHERE a.del=0 AND b.del=0 AND a.tip_id=" + tip_id;
        return helper.query(sql);
    }

    public ArrayList<HashMap<String, Object>> queryByTag(long tag_id, int page_no, int num_lmt) {
        int loc = (page_no - 1) * num_lmt;
        String sql = "SELECT b.title,b.summary,b.id FROM tip_tag a " +
                "LEFT JOIN tip b ON a.tip_id=b.id " +
                "WHERE a.del=0 AND b.del=0 AND a.tag_id=" + tag_id + " " +
                "ORDER BY b.favourite_count DESC " +
                "LIMIT " + loc + "," + num_lmt;
        return helper.query(sql);
    }

    public ArrayList<HashMap<String, Object>> simpleQueryByTip(long tip_id) {
        String sql = "SELECT * FROM tip_tag a " +
                "WHERE del=0 AND tip_id=" + tip_id;
        return helper.query(sql);
    }

    public ArrayList<HashMap<String, Object>> simpleQueryByTipWithDeleted(long tip_id) {
        String sql = "SELECT * FROM tip_tag a " +
                "WHERE tip_id=" + tip_id;
        return helper.query(sql);
    }

}
