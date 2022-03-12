package uz.pdp.online.TestManagement.service.admin.action;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import uz.pdp.online.TestManagement.entity.Users;
import uz.pdp.online.TestManagement.utils.DbConfig;
import uz.pdp.online.TestManagement.utils.UserRole;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserServiceAction {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public void divideUser() throws SQLException {
      while (true){
          System.out.println("1.UerList");
          System.out.println("2.UserListPdf");
          System.out.println("0.Back");
          System.out.println("Select:");
          int option=SCANNER_NUM.nextInt();
          switch (option){
              case 1:{
                  for(Users users:userlist()){
                      System.out.println(users);
                  }
              }break;
              case 2:{
                  UserServiceAction.userlistPdf();
              }break;
              case 0:return;

          }
      }
    }

    public static List<Users> userlist() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement = ulanish.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users");

        List<Users> usersList = new ArrayList<>();
        while (resultSet.next()) {
            Users users=new Users();
            users.setId( resultSet.getInt(1));
           users.setName(resultSet.getString(2));
           users.setPhone(resultSet.getString(3));
           users.setRole(resultSet.getString(4));
           users.setActive(resultSet.getBoolean(5));
           users.setDeleted(resultSet.getBoolean(6));
           users.setPassword(resultSet.getString(7));
           users.setUserName(resultSet.getString(8));
          usersList.add(users);
        }
        return usersList;
    }

    private static void userlistPdf() {
        try{
        String path = "src/main/resources";
        File file = new File(path, "userList.pdf");
        file.createNewFile();
        PdfWriter writer = new PdfWriter(file);
            PdfDocument document = new PdfDocument(writer);
            document.addNewPage();
            Document document1 = new Document(document);
            FileOutputStream out = new FileOutputStream("src/main/resources/userList.pdf");

            Paragraph paragraph = new Paragraph("USERLIST");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            paragraph.setBold();
            paragraph.setFontSize(22);
            document1.add(paragraph);

            float[] floats = {400f, 400f,400f,400f};
            Table table = new Table(floats);
            table.addCell("id");
            table.addCell("name");
            table.addCell("phone");
            table.addCell("Role");

            for (Users users : userlist()) {
               table.addCell(users.getId()+"");
               table.addCell(users.getName());
               table.addCell(users.getPhone());
               table.addCell(users.getRole()+"");
            }

            document1.add(table);
            out.close();
            Desktop.getDesktop().open(file);
            document1.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
