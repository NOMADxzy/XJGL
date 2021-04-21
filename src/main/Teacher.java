package main;

public class Teacher {
    private String Tname;
    private String Tsex;
    private int Tage;
    private String Cname;

    public Teacher(String tname, String tsex, int tage) {
        Tname = tname;
        Tsex = tsex;
        Tage = tage;
    }

    public Teacher(String tname,String cname, String tsex,int tage) {
        Tname = tname;
        Tsex = tsex;
        Tage = tage;
        Cname = cname;
    }

    public String getTname() {
        return Tname;
    }

    public String getTsex() {
        return Tsex;
    }

    public int getTage() {
        return Tage;
    }
    public String getCname(){
        return Cname;
    }
}
