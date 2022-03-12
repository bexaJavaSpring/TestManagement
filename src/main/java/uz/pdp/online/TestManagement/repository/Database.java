package uz.pdp.online.TestManagement.repository;

import uz.pdp.online.TestManagement.entity.*;
import uz.pdp.online.TestManagement.utils.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    public static List<Question>questionList=new ArrayList<>();
    public static List<Subject>subjectList=new ArrayList<>();
    public static List<Users>usersList=new ArrayList<Users>(Arrays.asList(new Users("b","0",UserRole.ADMIN)));
    public static List<History>historyList=new ArrayList<>();
    public static List<User_answer>user_answers=new ArrayList<>();
    public static List<Variant_answer>variant_answers=new ArrayList<>();
}
