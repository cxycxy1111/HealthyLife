package com.alfred.healthylife.UserService;

import com.alfred.healthylife.DAO.QuestionDAO;

public class UQuestionUService extends UService {

    private QuestionDAO questionDAO;

    public UQuestionUService() {
        questionDAO = new QuestionDAO();
    }

    public String create(String title, String create_time) {
        title.replace("'", "''");
        if (title.contains("--")) {
            return ILLEGAL;
        }
        if (questionDAO.create(title, create_time) != 0) {
            return SUCCESS;
        }
        return FAIL;
    }

}
