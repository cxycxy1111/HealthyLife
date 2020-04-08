package com.alfred.healthylife.Service;

import com.alfred.healthylife.DAO.TipDAO;
import com.alfred.healthylife.DAO.TipLogDAO;
import com.alfred.healthylife.Util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class TipService extends Service{

    private TipDAO tipDAO;
    private TipLogDAO tipLogDAO;

    public TipService() {
        tipDAO = new TipDAO();
        tipLogDAO = new TipLogDAO();
    }

    /**
     * 查询贴士详情
     * @param id
     * @return
     */
    public String query(long id) {
        return Util.transformFromCollection(tipDAO.queryTipById(id));
    }

    /**
     * 模糊查询
     * @param keywords
     * @return
     */
    public String query(String keywords) {
        keywords = keywords.replace(","," ");
        keywords = keywords.replace("，"," ");
        keywords = keywords.replace("+"," ");
        if (keywords.contains("|")) {
            return ILLEGAL;
        }else if (keywords.contains("'")) {
            return ILLEGAL;
        }
        String[] keyword_list;
        if (keywords.contains(" ")) {
            keyword_list = keywords.split(" ");
        }else {
            keyword_list = new String[]{keywords};
        }
        return Util.transformFromCollection(tipDAO.queryTipsByKeywords(keyword_list));
    }

    /**
     * 创建
     * @param title
     * @param summary
     * @param content
     * @param create_time
     * @param creator
     * @param creator_type
     * @return
     */
    public String create(String title,String tag,String summary,String content,String create_time,long creator,
                         int creator_type) {
        if (summary.length()>512 || content.length()>5120) {
            return TOO_LONG;
        }
        if (summary.equals("") || content.equals("")) {
            return TOO_SHORT;
        }
        if (tipDAO.create(title, tag, summary, content, create_time, creator, creator_type)) {
            ArrayList<HashMap<String,Object>> list_tip_log = new ArrayList<>();
            HashMap<String,Object> map_tip_log = new HashMap<>();
            map_tip_log.put("summary",summary);
            map_tip_log.put("content",content);
            list_tip_log.add(map_tip_log);
            tipLogDAO.add(Util.transformFromCollection(list_tip_log),create_time,creator,creator_type);
            return SUCCESS;
        }
        return FAIL;
    }

    /**
     * 删除
     * @param id
     * @param creator
     * @param create_time
     * @param creator_type
     * @return
     */
    public String delete(long id,long creator,String create_time,int creator_type) {
        if (tipDAO.delete(id)) {
            tipLogDAO.delete(id,"",create_time,creator,creator_type);
            return SUCCESS;
        }
        return FAIL;
    }

    /**
     * 恢复
     * @param id
     * @param creator
     * @param create_time
     * @param creator_type
     * @return
     */
    public String recover(long id,long creator,String create_time,int creator_type) {
        if (tipDAO.recover(id)) {
            tipLogDAO.recover(id,"",create_time,creator,creator_type);
            return SUCCESS;
        }
        return FAIL;
    }

    /**
     * 更新
     * @param id
     * @param summary
     * @param content
     * @param creator
     * @param creator_type
     * @param last_modify_time
     * @return
     */
    public String update(long id,String summary,String content,long creator,int creator_type,String last_modify_time) {
        if (Util.isContainIllegalCharCheck(summary) || Util.isContainIllegalCharCheck(content)) {
            return ILLEGAL;
        }
        if (summary.length()>512 || content.length()>5120) {
            return TOO_LONG;
        }
        if (summary.equals("") || content.equals("")) {
            return TOO_SHORT;
        }
        if (tipDAO.update(id,summary,content,last_modify_time)) {
            ArrayList<HashMap<String,Object>> list_tip_log = new ArrayList<>();
            HashMap<String,Object> map_tip_log = new HashMap<>();
            map_tip_log.put("summary",summary);
            map_tip_log.put("content",content);
            list_tip_log.add(map_tip_log);
            tipLogDAO.update(id,Util.transformFromCollection(list_tip_log),last_modify_time,creator,creator_type);
            return SUCCESS;
        }
        return FAIL;
    }

}
