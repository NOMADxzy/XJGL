package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperateSet {
    public boolean add_student (Student student){
        Connection conn = connectSQL.getConnection();
        String sql = "insert into student values(?,?,?,?,?)";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,student.getSno());
            st.setString(2,student.getSname());
            st.setString(3,student.getSex());
            st.setString(4,student.getBirthday());
            st.setString(5,student.getclass());
            st.execute();
            st.close();
            conn.close();
        }catch (SQLException e){
            return false;
        }
        return true;
    }
    public List<Student> select_by_sno(String Sno)throws SQLException{
        Student stu;
        List<Student> stus = new ArrayList<Student>();
        Connection conn = connectSQL.getConnection();
        String sql = "select * from Student where Sno = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
             stu = new Student(rs.getString("sno"),
                    rs.getString("sname"),
                    rs.getString("ssex"),
                    rs.getString("Birthday"),
                    rs.getString(5));
             stus.add(stu);
        }
        pst.close();
        conn.close();
        return stus;
    }public List select_by_sname(String Sname)throws SQLException{
        List list = new ArrayList<Student>();
        Connection conn = connectSQL.getConnection();
        String sql = "select * from Student where Sname = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sname);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
             Student stu = new Student(rs.getString("sno"),
                    rs.getString("sname"),
                    rs.getString("ssex"),
                    rs.getString("Birthday"),
                    rs.getString(5));
             list.add(stu);
        }
        pst.close();
        conn.close();
        return list;
    }
    public List select_by_major(String major)throws SQLException{
        List<Student> stus = new ArrayList();
        Connection conn = connectSQL.getConnection();
        String sql = "select * from Student where Class in " +
                "(select Class from CM where major = ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,major);

        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            stus.add(new Student(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        pst.close();
        conn.close();
        return stus;
    }

    public boolean del_student(String Sno)throws SQLException{
        Connection conn = connectSQL.getConnection();
        String sql = "delete from Student where Sno = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        pst.execute();
        pst.close();
        conn.close();
        return true;
    }
    //录入一位学生一门课的成绩
    public int set_grade(String Sno,String Cno,float grade)throws SQLException{
        if(grade<0||grade>100) return 2;
        int succ = 0;
        String bk = "无效";
        Connection conn = connectSQL.getConnection();
        String sql = "select * from SC where Sno = ? and Cno = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        pst.setString(2,Cno);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            bk = rs.getString(4);
        }
        if(bk.equals("无效")){
            String sql1 = "insert into SC(Sno,Cno,Grade) values (?,?,?)";
            pst = conn.prepareStatement(sql1);
            pst.setString(1,Sno);
            pst.setString(2,Cno);
            pst.setFloat(3,grade);
            try {
                pst.execute();
            }catch (SQLException e){
                return -1;
            }
            succ = 1;
        }else if(bk.equals("初考")){

            String sql1 = "update SC set Grade = ?,BK = ? where Sno=? and Cno=?";
            pst = conn.prepareStatement(sql1);
            pst.setFloat(1,grade);
            pst.setString(2,"补考");
            pst.setString(3,Sno);
            pst.setString(4,Cno);
            pst.execute();
            succ = 1;
        }
        pst.close();
        conn.close();
        return succ;
    }
//    查询一位学生所修的课程、性质（必修或选修）、学期、学分及成绩
    public List<STinfo> query_s_c(String Sno)throws SQLException{
        List sts = new ArrayList<STinfo>();
        Connection conn = connectSQL.getConnection();
        String sql = "select * from SC_INFO where Sno = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            STinfo st  = new STinfo(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getFloat(4),
                    rs.getFloat(5));
            sts.add(st);

        }
        return sts;
    }
    public float query_avg_grade_Compul(String Sno)throws SQLException{
        float avg = -1;
        Connection conn = connectSQL.getConnection();
        String sql = "select sum(Grade*Ccredit)/sum(Ccredit) from SC_INFO\n" +
                "where Cp='必修' and\n" +
                "      Sno = ?\n" +
                "GROUP BY Sno";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) avg = rs.getFloat(1);
        return avg;
    }
    public float query_avg_grade(String Sno)throws SQLException{
        float avg = -1;
        Connection conn = connectSQL.getConnection();
        String sql = "select sum(Grade*Ccredit)/sum(Ccredit) from SC_INFO\n" +
                "where  Sno = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) avg = rs.getFloat(1);
        return avg;
    }
    public List<Teacher> query_tname(String Sno)throws SQLException{
        List<Teacher> ths = new ArrayList();
        Connection conn = connectSQL.getConnection();
        String sql = "select CT.Tname,Cname,Tsex,Tage from Teacher,CT,Course where CT.Tname=Teacher.Tname and\n" +
                "        CT.Cno=Course.Cno and\n" +
                "        CT.Cno in (select Cno from SC where Sno = ?) and\n" +
                "        Class = (select Class from Student where Sno = ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,Sno);
        pst.setString(2,Sno);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            ths.add(new Teacher(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)));
        }
        return ths;
    }
    public List<Student> query_danger_s()throws SQLException{
        List<Student> stus = new ArrayList<Student>();
        Connection conn = connectSQL.getConnection();
        String sql = "select * from Student where Sno in(select Sno from LOW_COMPUL_SC\n" +
                "    GROUP BY Sno having SUM(Ccredit)>=7 and SUM(Ccredit)<10)\n" +
                "union select * from Student where Sno in(select Sno from LOW_NCOMPUL_SC\n" +
                "    GROUP BY Sno having SUM(Ccredit)>=12 and SUM(Ccredit)<15)";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            Student stu = new Student(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
            stus.add(stu);
        }
        return stus;
    }
    public void show_stinfo(List<STinfo> sts){
        for (STinfo st:sts
        ) {
            String na = String.format("%10s",st.getCname());
            String cp = String.format("%10s",st.getCp());
            String se = String.format("%10s",st.getSemster());
            String cr = String.format("%10s",st.getCcredit());
            String gr = String.format("%10s",st.getGrade());
            System.out.println(na+cp+se+cr+gr);

        }
    }
    public void show_students(List<Student> stus){
        for (Student stu:stus
        ) {
            System.out.printf("%5s%5s%5s%5s%5s\n",
                    stu.getSname(),stu.getSno(),stu.getSex(),stu.getBirthday(),stu.getclass()
            );
        }
    }


    public static void main(String[] args) throws SQLException {
        OperateSet op = new OperateSet();

        op.show_students(op.query_danger_s());

    }
}
