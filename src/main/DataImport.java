package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DataImport {

    // 数据库连接
    private Connection conn;
    
    // 构造函数，获取数据库连接
    public DataImport() {
        this.conn = connectSQL.getConnection();
    }
    
    // 导入所有数据
    public void importAllData() {
        System.out.println("开始导入数据...");
        
        try {
            // 禁用外键约束以加速导入
            disableForeignKeyChecks();
            
            // 清空所有表数据
            truncateTables();
            
            // 导入班级专业信息（从学生表中提取）
            importClassMajor();
            
            // 导入学生信息
            importStudents();
            
            // 导入教师信息
            importTeachers();
            
            // 导入课程信息
            importCourses();
            
            // 导入学生课程成绩信息
            importScores();
            
            // 导入教学计划信息（从Schedule.txt）
            importTeachingPlan();
            
            // 启用外键约束
            enableForeignKeyChecks();
            
            System.out.println("数据导入完成！");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据导入失败！");
        } finally {
            // 确保启用外键约束
            try {
                enableForeignKeyChecks();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // 禁用外键约束
    private void disableForeignKeyChecks() throws SQLException {
        String sql = "SET FOREIGN_KEY_CHECKS = 0";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
    }
    
    // 启用外键约束
    private void enableForeignKeyChecks() throws SQLException {
        String sql = "SET FOREIGN_KEY_CHECKS = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
    }
    
    // 清空所有表数据
    private void truncateTables() {
        try {
            // 按照依赖关系排序，先清空引用表，再清空被引用表
            String[] tables = {"StudentCourseScore", "TeachingPlan", "ClassTeacher", "Student", "Course", "Teacher", "ClassMajor"};
            
            for (String table : tables) {
                try {
                    String sql = "TRUNCATE TABLE " + table;
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println("表 " + table + " 已清空！");
                } catch (SQLException e) {
                    // 如果TRUNCATE失败，尝试使用DELETE
                    System.out.println("使用TRUNCATE清空表 " + table + " 失败，尝试使用DELETE...");
                    String sql = "DELETE FROM " + table;
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    int rowsAffected = pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println("使用DELETE清空表 " + table + " 成功，共删除 " + rowsAffected + " 行数据！");
                }
            }
            System.out.println("所有表数据已清空！");
        } catch (SQLException e) {
            System.out.println("清空表数据失败！");
            e.printStackTrace();
        }
    }
    
    // 导入班级专业信息
    private void importClassMajor() throws IOException, SQLException {
        System.out.println("导入班级专业信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/学生.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        // 使用Set来存储唯一的班级信息
        Set<String> classSet = new HashSet<>();
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\t");
            if (parts.length >= 5) {
                String className = parts[4]; // 班级信息在第5列
                classSet.add(className);
            }
        }
        reader.close();
        
        // 插入班级专业信息
        String sql = "INSERT INTO ClassMajor (Class, Major) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        for (String className : classSet) {
            pstmt.setString(1, className);
            // 由于文件中没有明确的专业信息，这里假设专业为CS
            pstmt.setString(2, "CS");
            pstmt.addBatch();
        }
        
        pstmt.executeBatch();
        pstmt.close();
    }
    
    // 导入学生信息
    private void importStudents() throws IOException, SQLException {
        System.out.println("导入学生信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/学生.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        String sql = "INSERT INTO Student (Sno, Sname, Ssex, Birthday, Class) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int batchSize = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\t");
            if (parts.length >= 5) {
                pstmt.setString(1, parts[0]); // 学号
                pstmt.setString(2, parts[1]); // 姓名
                pstmt.setString(3, parts[2]); // 性别
                pstmt.setString(4, parts[3]); // 生日
                pstmt.setString(5, parts[4]); // 班级
                
                pstmt.addBatch();
                batchSize++;
                
                // 每1000条记录执行一次批处理
                if (batchSize % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
        }
        
        // 执行剩余的批处理
        pstmt.executeBatch();
        
        reader.close();
        pstmt.close();
    }
    
    // 导入教师信息
    private void importTeachers() throws IOException, SQLException {
        System.out.println("导入教师信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/Teacher.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        String sql = "INSERT INTO Teacher (Tname, Tsex, Tage) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int batchSize = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\t");
            if (parts.length >= 3) {
                pstmt.setString(1, parts[0]); // 教师姓名
                pstmt.setString(2, parts[2]); // 性别
                pstmt.setInt(3, Integer.parseInt(parts[1])); // 年龄
                
                pstmt.addBatch();
                batchSize++;
                
                if (batchSize % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
        }
        
        pstmt.executeBatch();
        
        reader.close();
        pstmt.close();
    }
    
    // 导入课程信息
    private void importCourses() throws IOException, SQLException {
        System.out.println("导入课程信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/课程.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        String sql = "INSERT INTO Course (Cno, Cname, Ccredit) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int batchSize = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                pstmt.setString(1, parts[0]); // 课程号
                pstmt.setString(2, parts[1]); // 课程名
                pstmt.setFloat(3, Float.parseFloat(parts[2])); // 学分
                
                pstmt.addBatch();
                batchSize++;
                
                if (batchSize % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
        }
        
        pstmt.executeBatch();
        
        reader.close();
        pstmt.close();
    }
    
    // 导入学生课程成绩信息
    private void importScores() throws IOException, SQLException {
        System.out.println("导入学生课程成绩信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/成绩.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        // 使用INSERT IGNORE语句，让MySQL自动忽略重复记录
        String sql = "INSERT IGNORE INTO StudentCourseScore (Sno, Cno, Grade, BK) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int batchSize = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                pstmt.setString(1, parts[0]); // 学号
                pstmt.setString(2, parts[1]); // 课程号
                pstmt.setFloat(3, Float.parseFloat(parts[2])); // 成绩
                pstmt.setString(4, "考试"); // 考试性质，默认设为"考试"
                
                pstmt.addBatch();
                batchSize++;
                
                if (batchSize % 1000 == 0) {
                    System.out.println("已处理 " + batchSize + " 条成绩记录...");
                    int[] updateCounts = pstmt.executeBatch();
                    
                    // 统计实际插入的记录数（排除被忽略的重复记录）
                    int insertedCount = 0;
                    for (int count : updateCounts) {
                        if (count > 0) {
                            insertedCount++;
                        }
                    }
                    System.out.println("本次批处理成功插入 " + insertedCount + " 条记录。");
                }
            }
        }
        
        // 执行剩余的批处理
        if (batchSize > 0) {
            System.out.println("执行最后一批成绩记录批处理...");
            pstmt.executeBatch();
            System.out.println("成绩记录批处理执行完成！");
        }
        
        System.out.println("学生课程成绩信息导入完成！MySQL已自动忽略任何重复记录。");
        
        reader.close();
        pstmt.close();
    }
    
    // 导入教学计划信息
    private void importTeachingPlan() throws IOException, SQLException {
        System.out.println("导入教学计划信息...");
        String filePath = "/Users/Apple/Desktop/code/XJGL/data/Schedule.txt";
        // 使用GBK编码读取文件，解决中文乱码问题
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String line;
        
        // 使用INSERT IGNORE语句，让MySQL自动忽略重复记录
        String sql = "INSERT IGNORE INTO TeachingPlan (Major, Cno, Cp, Semster) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        int batchSize = 0;
        
        // 使用Set来存储已经处理过的专业+课程组合，避免重复添加到批处理
        Set<String> planSet = new HashSet<>();
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\t");
            if (parts.length >= 4) {
                String key = parts[0] + ":" + parts[1]; // 专业:课程号
                
                // 只添加未处理过的组合到批处理
                if (!planSet.contains(key)) {
                    pstmt.setString(1, parts[0]); // 专业
                    pstmt.setString(2, parts[1]); // 课程号
                    pstmt.setString(3, parts[2]); // 课程性质
                    
                    // 安全地解析学期号
                    try {
                        pstmt.setInt(4, Integer.parseInt(parts[3])); // 学期
                    } catch (NumberFormatException e) {
                        // 如果解析失败，设置默认值
                        pstmt.setInt(4, 1);
                        System.out.println("警告：学期号解析失败，使用默认值 1: " + parts[3]);
                    }
                    
                    pstmt.addBatch();
                    planSet.add(key); // 添加到已处理集合
                    batchSize++;
                    
                    // 每500条记录执行一次批处理
                    if (batchSize % 500 == 0) {
                        System.out.println("已处理 " + batchSize + " 条教学计划记录...");
                        int[] updateCounts = pstmt.executeBatch();
                        
                        // 统计实际插入的记录数（排除被忽略的重复记录）
                        int insertedCount = 0;
                        for (int count : updateCounts) {
                            if (count > 0) {
                                insertedCount++;
                            }
                        }
                        System.out.println("本次批处理成功插入 " + insertedCount + " 条教学计划记录。");
                    }
                }
            }
        }
        
        // 执行剩余的批处理
        if (batchSize > 0) {
            System.out.println("执行最后一批教学计划记录批处理...");
            int[] updateCounts = pstmt.executeBatch();
            
            // 统计实际插入的记录数（排除被忽略的重复记录）
            int insertedCount = 0;
            for (int count : updateCounts) {
                if (count > 0) {
                    insertedCount++;
                }
            }
            System.out.println("最后一批批处理成功插入 " + insertedCount + " 条教学计划记录。");
            System.out.println("教学计划记录批处理执行完成！");
        }
        
        System.out.println("教学计划信息导入完成！共处理 " + batchSize + " 条唯一记录，MySQL已自动忽略任何重复记录。");
        
        reader.close();
        pstmt.close();
    }
    
    // 主方法，用于测试
    public static void main(String[] args) {
        DataImport importTool = new DataImport();
        importTool.importAllData();
    }
}