package com.alfred.healthylife.DAO;

import com.alfred.healthylife.Util.SQLHelper;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class TipDAO extends DAO{

    private SQLHelper helper;

    public TipDAO(){
        helper = new SQLHelper();
    }

    /**
     * 通过帖子ID查询
     * @param id
     * @return
     */
    public ArrayList<HashMap<String, Object>> query(long id) {
        String sql = "SELECT * FROM tip a WHERE id=" + id;
        return helper.query(sql);
    }

    /**
     * 通过关键字查询标题
     * @param keywords
     * @return
     */
    public ArrayList<HashMap<String, Object>> query(String[] keywords) {
        StringBuilder builder_keywords = new StringBuilder();
        for (int i = 0;i < keywords.length;i++) {
            builder_keywords.append(" title LIKE '%").append(keywords[i]).append("%' OR");
        }
        String sql = "SELECT * FROM tip WHERE " + builder_keywords.toString();
        return helper.query(sql);
    }

    /**
     * 创建帖子
     * @param title
     * @param summary
     * @param content
     * @param create_time
     * @param creator
     * @param creator_type
     * @return
     */
    public long create(String title, String tag, String summary, String content, String create_time, long creator,
                       int creator_type) {
        String sql = "INSERT INTO tip (title,tag,summary,content,create_time,last_modify_time,creator,creator_type) VALUES" +
                        " ('" + title + "','" + tag + "','" + summary + "','" + content + "','" + create_time + "','" +
                        create_time + "'," + creator + "," + creator_type + ")";
        try {
            return helper.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除帖子
     * @param id
     * @return
     */
    public boolean delete(long id) {
        String sql = "UPDATE tip SET del=1 WHERE id=" + id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 恢复帖子
     * @param id
     * @return
     */
    public boolean recover(long id) {
        String sql = "UPDATE tip SET del=0 WHERE id=" + id;
        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新帖子
     * @param id
     * @param summary
     * @param content
     * @param last_modify_time
     * @return
     */
    public boolean update(long id,String summary,String content,String last_modify_time) {
        String sql =
                "UPDATE tip SET summary='" + summary + "',content='" + content + "',last_modify_time='" + last_modify_time
                        + "' WHERE id=" + id;

        try {
            return helper.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
