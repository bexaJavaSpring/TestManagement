package uz.pdp.online.TestManagement.service.admin.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.online.TestManagement.entity.Question;
import uz.pdp.online.TestManagement.utils.DbConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static uz.pdp.online.TestManagement.service.admin.action.QuestionService.showQuestion;

public class QuestionMenu {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);
//    public static List<Question> getList(Integer s_id, String type) throws SQLException {
//        Connection connection= DbConfig.ulanish();
//        CallableStatement callableStatement=connection.prepareCall("{call getquestion(?,?)}");
//        callableStatement.setInt(1,s_id);
//        callableStatement.setString(2,type);
//        ResultSet resultSet=callableStatement.executeQuery();
//        Question[] questions=new Question[1000];
//        while (resultSet.next()){
//            Gson gson=new GsonBuilder().setPrettyPrinting().create();
//            questions=gson.fromJson(resultSet.getString(1),Question[].class);
//        }
//        return new ArrayList<>(Arrays.asList(questions));
//    }
    public static void questionCRUD() throws SQLException {
        while (true){
            System.out.println("1.AddQuestion");
            System.out.println("2.UpdateQuestion");
            System.out.println("3.ShowQuestion");
            System.out.println("4.DeleteQuestion");
            System.out.println("0.Back");
            System.out.println("Select:");
            int option=SCANNER_NUM.nextInt();
            switch (option){
                case 1: {
                    System.out.println("question( max=20) ");
                    System.out.println("Enter subjectid:");
                    int subjectid=SCANNER_NUM.nextInt();
                    System.out.println("Enter type:");
                    String type=SCANNER_STR.nextLine();
                    System.out.println("Enter id:");
                    int id=SCANNER_NUM.nextInt();
                    System.out.println("Enter text:");
                    String text=SCANNER_STR.nextLine();
                    System.out.println("Enter correct_answer:");
                    String correct_answer=SCANNER_STR.nextLine();
                    if(id< 20 && subjectid<4){
                        System.out.println("This question already exist");
                    }else {
                        QuestionService.addQuestion(id, text, subjectid, type, true, correct_answer);
                    }
                }break;
                case 2: {
                    System.out.println("Enter subjectid:");
                    int subjectid=SCANNER_NUM.nextInt();
                    System.out.println("Enter type:");
                    String type=SCANNER_STR.nextLine();
                    System.out.println("Enter id:");
                    int id=SCANNER_NUM.nextInt();
                    System.out.println("Enter text:");
                    String text=SCANNER_STR.nextLine();
                    System.out.println("Enter correct_answer:");
                    String correct_answer=SCANNER_STR.nextLine();
                    if(subjectid>4){
                        System.out.println("This subjectid not found");
                    }else {
                        QuestionService.updateQuestion(id, text, subjectid, type, true, correct_answer);
                    }
                }break;
                case 3:{
                    for (Question question:showQuestion()){
                        System.out.println(question);
                    }
                }break;
                case 4: {
                    System.out.println("Enter subjectid:");
                    int subjectid=SCANNER_NUM.nextInt();
                    System.out.println("Enter type:");
                    String type=SCANNER_STR.nextLine();
                    System.out.println("Enter id:");
                    int id=SCANNER_NUM.nextInt();
                    System.out.println("Enter text:");
                    String text=SCANNER_STR.nextLine();
                    System.out.println("Enter correct_answer:");
                    String correct_answer=SCANNER_STR.nextLine();
                    QuestionService.deleteQuestion(id,text,subjectid,type,true,correct_answer);
                }break;
                case 0:return;
            }
        }
    }
}
