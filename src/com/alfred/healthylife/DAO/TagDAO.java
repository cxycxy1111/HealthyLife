package com.alfred.healthylife.DAO;

import com.alfred.healthylife.Util.SQLHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TagDAO extends DAO{

    private SQLHelper helper;

    public TagDAO() {
        helper = new SQLHelper();
    }

    /**
     * 创建标签
     * @param title
     * @return
     */
    public boolean create(String title) {
        String sql = "INSERT INTO tag (title) VALUES ('" + title + "')";
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    public boolean delete(long id) {
        String sql = "UPDATE tag SET del = 1 WHERE id=" + id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复标签
     * @param id
     * @return
     */
    public boolean recover(long id) {
        String sql = "UPDATE tag SET del = 0 WHERE id=" + id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新标签
     * @param id
     * @return
     */
    public boolean update(long id,String title) {
        String sql = "UPDATE tag SET title = " + title + "' WHERE id=" + id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新标签
     * @return
     */
    public ArrayList<HashMap<String, Object>> query() {
        String sql = "SELECT * FROM ";
        return helper.query(sql);
    }

}
