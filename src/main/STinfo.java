package main;

public class STinfo {
    private String Cname;
    private String Cp;
    private String Semster;
    private float Ccredit;
    private float Grade;

    public STinfo(String cname, String cp, String semster, float ccredit, float grade) {
        Cname = cname;
        Cp = cp;
        Semster = semster;
        Ccredit = ccredit;
        Grade = grade;
    }

    public String getCname() {
        return Cname;
    }

    public String getCp() {
        return Cp;
    }

    public String getSemster() {
        return Semster;
    }

    public float getCcredit() {
        return Ccredit;
    }

    public float getGrade() {
        return Grade;
    }
    public String[] toArray(){
        String[] arr = {Cname,Cp,Semster,Ccredit+"",Grade+""};
        return arr;
    }
}
