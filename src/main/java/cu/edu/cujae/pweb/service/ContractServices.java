package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.utils.reportTables.ContractForBrandAndModelReport;
import cu.edu.cujae.pweb.utils.reportTables.ContractForCountryReport;
import cu.edu.cujae.pweb.utils.reportTables.ContractReport;
import cu.edu.cujae.pweb.utils.reportTables.IncomeAnnualReport;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContractServices {

    public ArrayList<ContractDto> listContract() throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contracts = new ArrayList<ContractDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_contract()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            int code = result.getInt(1);
            TouristDto tourist = ServicesLocator.getTouristServices().getTouristById(result.getInt(2));
            CarDto car = ServicesLocator.getCarsServices().getCarById(result.getInt(3));
            BillDto bill = ServicesLocator.getBillServices().returnBill(result.getInt(4));
            PaymentsDto payment = ServicesLocator.getPaymentsServices().getPaymentById(result.getInt(5));
            DriverDto driver = null;
            if (result.getInt(6) != 0) {
                driver = ServicesLocator.getDriverServices().getDriverById(result.getInt(6));
            }
            LocalDate starting_date = result.getDate(7).toLocalDate();
            LocalDate final_date = result.getDate(8).toLocalDate();
            int extension = result.getInt(9);
            contracts.add(new ContractDto(code, tourist, car, bill, payment, driver, starting_date, final_date, extension));
        }
        call.close();
        connection.close();
        return contracts;
    }

    public ContractDto getContractById(int code) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call return_contract( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        TouristDto tourist = ServicesLocator.getTouristServices().getTouristById(result.getInt(2));
        CarDto car = ServicesLocator.getCarsServices().getCarById(result.getInt(3));
        BillDto bill = ServicesLocator.getBillServices().returnBill(result.getInt(4));
        PaymentsDto payment = ServicesLocator.getPaymentsServices().getPaymentById(result.getInt(5));
        DriverDto driver = null;
        if (result.getInt(6) != 0) {
            driver = ServicesLocator.getDriverServices().getDriverById(result.getInt(6));
        }
        LocalDate starting_date = result.getDate(7).toLocalDate();
        LocalDate final_date = result.getDate(8).toLocalDate();
        int extension = result.getInt(9);
        ContractDto contract = new ContractDto(code, tourist, car, bill, payment, driver, starting_date, final_date, extension);
        call.close();
        connection.close();
        return contract;
    }

    public void insertContract(int cod_tourist, int cod_car, int cod_bill, int cod_payment, int cod_driver, LocalDate starting_date, LocalDate final_date, int extension) throws SQLException, ClassNotFoundException {
        BillDto bill = ServicesLocator.getBillServices().returnBill(cod_bill);
        ContractDto contract = new ContractDto(bill, starting_date, final_date, extension);
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_contract( ?,?,?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, cod_tourist);
        call.setInt(2, cod_car);
        call.setInt(3, cod_bill);
        call.setInt(4, cod_payment);
        if (cod_driver != 0) {
            call.setInt(5, cod_driver);
        } else {
            call.setObject(5, null);
        }
        call.setDate(6, java.sql.Date.valueOf(starting_date));
        call.setDate(7, java.sql.Date.valueOf(final_date));
        call.setInt(8, extension);
        call.setDouble(9, contract.getTotalAmount());
        call.execute();
        call.close();
        connection.close();
        if(final_date.isAfter(LocalDate.now())) {
            ServicesLocator.getCarsServices().setCarStatusToRented(cod_car);
        }
    }

    public void updateContract(int code, int cod_tourist, int cod_car, int cod_bill, int cod_payment, int cod_driver, LocalDate starting_date, LocalDate final_date, int extension) throws SQLException, ClassNotFoundException {
        BillDto bill = ServicesLocator.getBillServices().returnBill(cod_bill);
        ContractDto contract = new ContractDto(bill, starting_date, final_date, extension);
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_contract( ?,?,?,?,?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setInt(2, cod_tourist);
        call.setInt(3, cod_car);
        call.setInt(4, cod_bill);
        call.setInt(5, cod_payment);
        if (cod_driver != 0) {
            call.setInt(6, cod_driver);
        } else {
            call.setObject(6, null);
        }
        call.setDate(7, java.sql.Date.valueOf(starting_date));
        call.setDate(8, java.sql.Date.valueOf(final_date));
        call.setInt(9, extension);
        call.setDouble(10, contract.getTotalAmount());
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteContract(int code) throws SQLException, ClassNotFoundException {
        BillDto bill = getContractById(code).getBill();
        CarDto car = getContractById(code).getCar();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_contract(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
        ServicesLocator.getBillServices().deleteBill(bill.getCode());
        ServicesLocator.getCarsServices().setCarStatusToAvailable(car.getCode());
    }

    public ArrayList<ContractDto> getContractsByTouristId(int id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for (ContractDto c : contracts) {
            if (c.getTourist().getCode() == id) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByBillId(int bill_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for (ContractDto c : contracts) {
            if (c.getBill().getCode() == bill_id) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByCarId(int car_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for (ContractDto c : contracts) {
            if (c.getCar().getCode() == car_id) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByDriverId(int driver_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for (ContractDto c : contracts) {
            if (c.getDriver() != null) {
                if (c.getDriver().getCode() == driver_id) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public ArrayList<ContractDto> getContractsByPaymentId(int payment_id) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> result = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        for (ContractDto c : contracts) {
            if (c.getPayment().getCode() == payment_id) {
                result.add(c);
            }
        }
        return result;
    }

    public ArrayList<ContractReport> contractReport() throws SQLException {
        ArrayList<ContractReport> report = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call contract_report()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            String haveDriver = "";
            if (result.getInt(10) == 0) {
                haveDriver = "No";
            } else {
                haveDriver = "Yes";
            }
            report.add(new ContractReport(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            result.getDate(7).toLocalDate(),
                            result.getDate(8).toLocalDate(),
                            result.getInt(9),
                            haveDriver,
                            result.getFloat(11)
                    )
            );
        }
        call.close();
        connection.close();
        return report;
    }

    public ArrayList<ContractDto> getAvailableContract() throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contracts = new ArrayList<>();
        ArrayList<ContractDto> contractDtos = listContract();
        for (ContractDto c : contractDtos) {
            if (c.getFinalDate().isAfter(LocalDate.now())) {
                contracts.add(c);
            }
        }
        return contracts;
    }

    public ArrayList<ContractForBrandAndModelReport> contractForBrandAndModelReport() throws SQLException, ClassNotFoundException {
        ArrayList<ContractForBrandAndModelReport> report = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        List<Integer> carsId = new LinkedList<>();
        for (ContractDto c : contracts) {
            int code = c.getCode();
            String brand = c.getCar().getBrand().getBrandText();
            String model = c.getCar().getBrand().getModel().getModelText();
            int contractAmount = 0;
            int amountRentedDays = 0;
            float incomeCreditCard = 0;
            float incomeCash = 0;
            float incomeCheck = 0;
            if (!carsId.contains(c.getCar().getCode())) {
                carsId.add(c.getCode());
                for (ContractDto d : contracts) {
                    if (d.getCode() == code) {
                        contractAmount++;
                        amountRentedDays += ChronoUnit.DAYS.between(d.getStartingDate(), d.getFinalDate());
                        if (d.getPayment().getPaymentText().equals("credit card")) {
                            incomeCreditCard += d.getTotalAmount();
                        } else {
                            if (d.getPayment().getPaymentText().equals("cash")) {
                                incomeCash += d.getTotalAmount();
                            } else {
                                if (d.getPayment().getPaymentText().equals("check")) {
                                    incomeCheck += d.getTotalAmount();
                                }
                            }
                        }
                    }
                }
                report.add(new ContractForBrandAndModelReport(brand, model, contractAmount, amountRentedDays, incomeCreditCard, incomeCheck, incomeCash));
            }
        }
        return report;
    }

    public ArrayList<ContractForCountryReport> contractForCountryReport() throws SQLException, ClassNotFoundException {
        ArrayList<ContractForCountryReport> report = new ArrayList<>();
        ArrayList<ContractDto> contracts = listContract();
        List<String> countries = new LinkedList<>();
        for (ContractDto con : contracts) {
            String country = con.getTourist().getCountry();
            if (!countries.contains(country)) {
                countries.add(country);
                List<Integer> carsId = new LinkedList<>();
                for (ContractDto c : contracts) {
                    int code = c.getCode();
                    String country1 = c.getTourist().getCountry();
                    String brand = c.getCar().getBrand().getBrandText();
                    String model = c.getCar().getBrand().getModel().getModelText();
                    int amountExtension = 0;
                    int amountRentedDays = 0;
                    float incomeCash = 0;
                    float totalAmount = 0;
                    if (!carsId.contains(c.getCar().getCode()) && country1.equals(country)) {
                        carsId.add(c.getCode());
                        for (ContractDto d : contracts) {
                            if (d.getCode() == code && country1.equals(country)) {
                                amountExtension += d.getExtension();
                                amountRentedDays += ChronoUnit.DAYS.between(d.getStartingDate(), d.getFinalDate());
                                totalAmount += d.getTotalAmount();
                                if (d.getPayment().getPaymentText().equals("cash")) {
                                    incomeCash += d.getTotalAmount();
                                }
                            }
                        }
                        report.add(new ContractForCountryReport(country, brand, model, amountExtension, incomeCash, totalAmount));
                    }
                }
            }
        }
        return report;
    }

    public ArrayList<IncomeAnnualReport> incomeAnnualReport() throws SQLException, ClassNotFoundException {
        ArrayList<IncomeAnnualReport> report = IncomeAnnualReport.generatedIncomeAnnualReport();
        ArrayList<ContractDto> contracts = listContract();
        float totalAmount = 0;
        for (ContractDto c : contracts) {
            if (c.getStartingDate().getYear() == LocalDate.now().getYear()) {
                int month = c.getStartingDate().getMonthValue();
                totalAmount += c.getTotalAmount();
                float amount = c.getTotalAmount() + report.get(month-1).getIncomeMonthly();
                report.get(month-1).setIncomeMonthly(amount);
            }
        }
        report.get(12).setIncomeMonthly(totalAmount);
        return report;
    }

}
