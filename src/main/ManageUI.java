package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ManageUI {
    public void mainmenu()throws SQLException{
        int key;
        Scanner in = new Scanner(System.in);
        System.out.println("******************************");
        System.out.println("=======欢迎进入学生信息管理系统=======");
        do{
            System.out.println("1.新增学生");

            System.out.println("3.录入一位学生一门课的成绩");

            System.out.println("2.查询学生信息");
            System.out.println("4.查询一位学生所修的课程详情");
            System.out.println("5.查询一位学生的平均成绩");
            System.out.println("6.查询一位学生被哪些教师教过课");
            System.out.println("7.查询快要被开除的学生");

            System.out.println("8.退出该系统");
            System.out.println("请选择（1-8）：");
            //
            key = in.nextInt();
            System.out.println("******************************");
            switch (key){
                case 1: add_stu();
                break;
                case 2: query_stu();
                break;
                case 3: set_grade();
                break;
                case 4: query_stu_cinfo();
                break;
                case 5: query_avg();
                break;
                case 6: query_tname();
                break;
                case 7: query_danger_stu();
                break;
                case 8:
                    System.out.println("您选择了退出系统，确定要退出吗？(y/n)");
                    String confirm=in.next();
                    if(confirm.equals("y")){
                        System.exit(-1);
                        System.out.println("您已成功退出系统，欢迎您再次使用！");
                    }
                    break;
                default:
                    break;

            }
        }while (key!=8);
    }

    public void add_stu()throws SQLException {
        String again;
        Scanner in=new Scanner(System.in);
        do {

            String Sno,Sname,Ssex,Birthday,Class;
            System.out.println("====新增学生====");
            System.out.println("学号：");
            Sno=in.next();
            System.out.println("姓名：");
            Sname=in.next();
            System.out.println("性别：");
            Ssex=in.next();
            System.out.println("生日：");
            Birthday=in.next();
            System.out.println("班级：");
            Class=in.next();
            Student stu = new Student(Sno,Sname,Ssex,Birthday,Class);
            OperateSet op = new OperateSet();
            boolean succ = op.add_student(stu);
            if (succ) System.out.println("保存成功!");
            else System.out.println("保存失败!");
            System.out.println("是否继续添加(y/n)：");
            again = in.next();
        }while (again.equals("y"));
    }
    public void query_stu()throws SQLException{
        Scanner in = new Scanner(System.in);
        String keyword;
        int type;
        int num_of_stus;
        String again;
        OperateSet op = new OperateSet();
        System.out.println("====查询学生====");
        do{
            System.out.println("通过何种方式查询？");
            System.out.println("1，学号");
            System.out.println("2，姓名");
            System.out.println("3，专业");
            System.out.println("4，返回主菜单");
            System.out.println("请选择（1-4），");
            type = in.nextInt();
            switch (type){
                case 1:
                    System.out.println("请输入学号：");
                    keyword = in.next();
                    num_of_stus = op.select_by_sno(keyword).size();
                    if(num_of_stus>0) op.show_students(op.select_by_sno(keyword));
                    else System.out.println("结果为空");
                    System.out.println("是否继续查询？(y/n)");
                    again = in.next();
                break;
                case 2:
                    System.out.println("请输入姓名：");
                    keyword = in.next();
                    num_of_stus = op.select_by_sname(keyword).size();
                    if(num_of_stus>0) op.show_students(op.select_by_sname(keyword));
                    else System.out.println("结果为空");
                    System.out.println("是否继续查询？(y/n)");
                    again = in.next();
                break;
                case 3:
                    System.out.println("请输入专业：");
                    keyword = in.next();
                    num_of_stus = op.select_by_major(keyword).size();
                    if(num_of_stus>0) op.show_students(op.select_by_major(keyword));
                    else System.out.println("结果为空");
                    System.out.println("是否继续查询？(y/n)");
                    again = in.next();
                case 4:
                    return;
                default:
                    System.out.println("输入有误，是否继续查询？(y/n)");
                    again = in.next();
                break;
            }
        }while (again.equals("y"));
    }
    public void set_grade()throws SQLException{
        Scanner in = new Scanner(System.in);
        float grade;
        int succ;
        String Sno,Cno,again="n";
        OperateSet op = new OperateSet();
        System.out.println("====录入成绩====");
        do{
            System.out.println("请输入学号：");
            Sno = in.next();
            System.out.println("请输入课程号：");
            Cno = in.next();
            System.out.println("请输入成绩：");
            grade = in.nextFloat();
            succ = op.set_grade(Sno,Cno,grade);
            if(succ==1) System.out.println("保存成功！");
            else if(succ==0) System.out.println("保存失败，该学生已经补考过了");
            else System.out.println("学号或课程号不存在");
            System.out.println("是否继续录入？(y/n)");
            again = in.next();
        }while (again.equals("y"));
    }
    public void query_stu_cinfo()throws SQLException{
        Scanner in = new Scanner(System.in);
        System.out.println("====查询一位学生所修的课程详情====");
        String again,Sno;
        List<STinfo> sts;
        OperateSet op = new OperateSet();
        do {
            System.out.println("请输入学号：");
            Sno = in.next();
            sts = op.query_s_c(Sno);
            if(sts.size()>0){
                op.show_stinfo(sts);
            }
            else System.out.println("结果为空");
            System.out.println("是否继续查询？(y/n)");
            again = in.next();
        }while (again.equals("y"));
    }
    public void query_avg ()throws SQLException{
        Scanner in = new Scanner(System.in);
        String again,Sno,flag;
        OperateSet op = new OperateSet();
        System.out.println("====查询平均成绩====");
        do{
            System.out.println("请输入学号：");
            Sno = in.next();
            System.out.println("1,所有课程\n2,必修\n请选择(1,2)：");
            flag = in.next();
            if (flag.equals("1")) System.out.printf("%.2f\n",op.query_avg_grade(Sno));
            else if(flag.equals("2")) System.out.printf("%.2f\n",op.query_avg_grade_Compul(Sno));
            else System.out.println("输入有误");
            System.out.println("是否继续查询？(y/n)");
            again = in.next();
        }while (again.equals("y"));
    }
    public void query_tname()throws SQLException{
        Scanner in = new Scanner(System.in);
        String again,Sno;
        OperateSet op = new OperateSet();
        System.out.println("====查询一位学生被哪些教师教过课====");
        do {
            System.out.println("请输入学号：");
            Sno = in.next();
            for (Teacher t:op.query_tname(Sno)
                 ) {
                System.out.printf("%s%s%s%s\n",t.getTname(),t.getCname(),
                        t.getTsex(),t.getTage());
            }
            System.out.println("\n是否继续查询？(y/n)");
            again = in.next();
        }while (again.equals("y"));
    }
    public void query_danger_stu()throws SQLException{
        Scanner in = new Scanner(System.in);
        OperateSet op = new OperateSet();
        List<Student> sts;
        System.out.println("====查询快要被开除的学生====");
        sts = op.query_danger_s();
        if(sts.size()==0) System.out.println("结果为空");
        else op.show_students(sts);
        System.out.println("按任意键返回主菜单");
        in.next();
    }
}
