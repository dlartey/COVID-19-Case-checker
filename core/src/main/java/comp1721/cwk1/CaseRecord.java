package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord {
    private LocalDate date;
    private int staffCases;
    private int studentCases;
    private int otherCases;

    // TODO: Write stub for constructor

    public CaseRecord(LocalDate d, int staffC, int studentC, int otherC) {

        if (staffC < 0 || studentC < 0 || otherC < 0){
            throw new DatasetException("Cases can't be <0");
        }else{
            studentCases = studentC;
            staffCases = staffC;
            otherCases = otherC;
            date = d;
        }

    }

    // TODO: Write stubs for four getter methods

    public int getStaffCases() {
        return staffCases;
    }

    public int getOtherCases() {
        return otherCases;
    }

    public int getStudentCases() {
        return studentCases;
    }

    public LocalDate getDate() {
        return date;
    }

    //TODO: Write stub for totalCases()
    public int totalCases() {
        return studentCases + staffCases + otherCases;
    }

    // TODO: Write stub for toString()

    @Override
    public String toString() {
        return date + ": " + staffCases +
                " staff, " + studentCases +
                " students, " + otherCases +
                " other";
    }
}
