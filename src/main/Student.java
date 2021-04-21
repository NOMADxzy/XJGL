package main;
import java.sql.*;

public class Student {
    private String Sno;
    private String Sname;
    private String Sex;
    private String Birthday;
    private String Cla;
    public Student(){

    }

    public Student(String sno, String sname, String sex, String birthday, String Class) {
        Sno = sno;
        Sname = sname;
        Sex = sex;
        Birthday = birthday;
        Cla = Class;
    }
    public String[] toArray(){
        String[] arr = {Sno,Sname,Sex,Birthday,Cla};
        return arr;
    }

    public String getSno() {
        return Sno;
    }

    public String getSname() {
        return Sname;
    }

    public String getSex() {
        return Sex;
    }

    public String getBirthday() {
        return Birthday;
    }

    public String getclass() {
        return Cla;
    }

}
