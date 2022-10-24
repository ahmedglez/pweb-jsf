package cu.edu.cujae.pweb.utils.reportTables;

import cu.edu.cujae.pweb.service.ServicesLocator;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeAnnualReport {

    private String month;
    private float incomeMonthly;

    public IncomeAnnualReport(String moth, float incomeMonthly) {
        this.month = moth;
        this.incomeMonthly = incomeMonthly;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getIncomeMonthly() {
        return incomeMonthly;
    }

    public void setIncomeMonthly(float incomeMonthly) {
        this.incomeMonthly = incomeMonthly;
    }

    public static ArrayList<IncomeAnnualReport> generatedIncomeAnnualReport(){
        ArrayList<IncomeAnnualReport> report = new ArrayList<>();
        String[] moths = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER","TOTAL"};
        float amount = 0;
        for(String m : moths){
            report.add(new IncomeAnnualReport(m,amount));
        }
        return report;
    }

    public static ArrayList<IncomeAnnualReport> getIncomeAnnualReport() throws SQLException, ClassNotFoundException {
        return ServicesLocator.getContractServices().incomeAnnualReport();
    }
}
