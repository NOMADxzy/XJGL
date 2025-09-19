-- 班级专业信息表
CREATE TABLE ClassMajor (
    Class char(10) PRIMARY KEY,
    Major char(10) NOT NULL
);

-- 学生信息表
CREATE TABLE Student (
    Sno char(10) PRIMARY KEY,
    Sname char(20) NOT NULL,
    Ssex char(10) NOT NULL,
    Birthday char(10) NOT NULL,
    Class char(10) NOT NULL,
    FOREIGN KEY (Class) REFERENCES ClassMajor(Class)
);

-- 教师信息表
CREATE TABLE Teacher (
    Tname char(10) PRIMARY KEY,
    Tsex char(2) NOT NULL,
    Tage smallint NOT NULL
);

-- 课程信息表
CREATE TABLE Course (
    Cno char(10) PRIMARY KEY,
    Cname char(50) NOT NULL,
    Ccredit float NOT NULL
);

-- 班级教师信息表
CREATE TABLE ClassTeacher (
    Class char(10) PRIMARY KEY,
    Cno char(10) NOT NULL,
    Tname char(10) NOT NULL,
    FOREIGN KEY (Class) REFERENCES ClassMajor(Class),
    FOREIGN KEY (Cno) REFERENCES Course(Cno),
    FOREIGN KEY (Tname) REFERENCES Teacher(Tname)
);

-- 学生课程成绩信息表
CREATE TABLE StudentCourseScore (
    Sno char(10) PRIMARY KEY,
    Cno char(10) NOT NULL,
    Grade float NOT NULL,
    BK char(4) NOT NULL,
    FOREIGN KEY (Sno) REFERENCES Student(Sno),
    FOREIGN KEY (Cno) REFERENCES Course(Cno)
);

-- 教学计划信息表
CREATE TABLE TeachingPlan (
    Major char(10) PRIMARY KEY,
    Cno char(10) NOT NULL,
    Cp char(10) NOT NULL,
    Semster Smallint NOT NULL,
    FOREIGN KEY (Cno) REFERENCES Course(Cno)
);