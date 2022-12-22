package cu.edu.cujae.pweb.dto.reportTables;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeAnnualReport {

    private String month;
    private double incomeMonthly;

    public IncomeAnnualReport(String moth, double incomeMonthly) {
        this.month = moth;
        this.incomeMonthly = incomeMonthly;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getIncomeMonthly() {
        return incomeMonthly;
    }

    public void setIncomeMonthly(double incomeMonthly) {
        this.incomeMonthly = incomeMonthly;
    }

    public static ArrayList<IncomeAnnualReport> generatedIncomeAnnualReport(){
        ArrayList<IncomeAnnualReport> report = new ArrayList<>();
        String[] moths = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER","TOTAL"};
        double amount = 0;
        for(String m : moths){
            report.add(new IncomeAnnualReport(m,amount));
        }
        return report;
    }

}
