package uz.pdp.online.TestManagement.service.admin.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.entity.Response;
import uz.pdp.online.TestManagement.entity.Subject;
import uz.pdp.online.TestManagement.repository.Database;
import uz.pdp.online.TestManagement.utils.DbConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static uz.pdp.online.TestManagement.entity.Message.DELETED;
import static uz.pdp.online.TestManagement.entity.Message.UPDATED;

public class QuestionService {

    public static Response deleteQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {

        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call delete_question(?,?,?,?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, text);
        callableStatement.setInt(3, s_id);
        callableStatement.setString(4,type);
        callableStatement.setBoolean(5,active);
        callableStatement.setString(6,correct_answer);
        callableStatement.registerOutParameter(7, Types.BOOLEAN);
        callableStatement.registerOutParameter(8, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(7));
        response.setMessage(callableStatement.getString(8));
        System.out.println(DELETED);
        return response;
    }

    public static List<Question> showQuestion() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = null;
        try {
            statement = ulanish.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = statement.executeQuery("select*from question");
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            Question question  = new Question();

            question.setId(resultSet.getInt(1));
            question.setText(resultSet.getString(2));
            question.setS_id(resultSet.getInt(3));
            question.setType(resultSet.getString(4));
            question.setActive(resultSet.getBoolean(5));
            question.setCorrect_answer(resultSet.getString(6));
            questionList.add(question);
        }
        return questionList;
    }

    public static Response updateQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {
        Response response = new Response();

        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call edit_question1(?,?,?,?,?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setString(2, text);
        callableStatement.setInt(3, s_id);
        callableStatement.setString(4,type);
        callableStatement.setBoolean(5,active);
        callableStatement.setString(6,correct_answer);
        callableStatement.registerOutParameter(7, Types.BOOLEAN);
        callableStatement.registerOutParameter(8, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(7));
        response.setMessage(callableStatement.getString(8));
        System.out.println(UPDATED);
        return response;
    }

    public static boolean addQuestion(Integer id,String text,Integer s_id,String type,boolean active,String correct_answer) throws SQLException {
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into question values(?,?,?,?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, text);
        preparedStatement.setInt(3, s_id);
        preparedStatement.setString(4, type);
        preparedStatement.setBoolean(5,true);
        preparedStatement.setString(6,correct_answer);
        boolean execute = preparedStatement.execute();
        System.out.println("Added");
        return execute;
    }
}
