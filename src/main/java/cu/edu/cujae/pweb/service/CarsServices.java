package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.utils.reportTables.CarStatusReport;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CarsServices {

    public void insertCar(String carId, int codStatus, int codBrand, String color, double mileage) throws SQLException, ClassNotFoundException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_car( ?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, carId);
        call.setInt(2, codStatus);
        call.setInt(3, codBrand);
        call.setString(4, color);
        call.setDouble(5, mileage);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<CarDto> listCars() throws SQLException, ClassNotFoundException {
        ArrayList<CarDto> cars = new ArrayList<CarDto>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_cars()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(4));
            CarStatusDto status = ServicesLocator.getStatusServices().getStatusById(result.getInt(3));
            String color = result.getString(5);
            double mileage = result.getDouble(6);
            cars.add(new CarDto(result.getInt(1), result.getString(2), status, brand, color, mileage));
        }
        call.close();
        connection.close();
        return cars;
    }

    public void deleteCar(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_car(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateCar(int code, String carId, int codStatus, int codBrand, String color, double mileage) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_car( ?,?,?,?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, carId);
        call.setInt(3, codStatus);
        call.setInt(4, codBrand);
        call.setString(5, color);
        call.setDouble(6, mileage);
        call.execute();
        call.close();
        connection.close();
    }

    public CarDto getCarById(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call return_car( ? )}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        BrandDto brand = ServicesLocator.getBrandServices().getBrandById(result.getInt(4));
        CarStatusDto status = ServicesLocator.getStatusServices().getStatusById(result.getInt(3));
        String color = result.getString(5);
        double km_driver = result.getDouble(6);
        CarDto car = new CarDto(result.getInt(1), result.getString(2), status, brand, color, km_driver);
        call.close();
        connection.close();
        return car;
    }

    public ArrayList<CarDto> getAvailableCars() throws SQLException, ClassNotFoundException {
        ArrayList<CarDto> cars = listCars();
        ArrayList<CarDto> result = new ArrayList<>();
        for(CarDto c : cars){
            if(c.getStatus().getStatusText().equals("Disponible")){
                result.add(c);
            }else{
                if(c.getStatus().getStatusText().equals("Rentado")){
                    //implementar para el otro corte
                    //pasar por parametro un LocalDate
                }
            }
        }
        return result;
    }

    public boolean canDelete(CarDto car) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contacts = ServicesLocator.getContractServices().listContract();
        boolean found = true;
        for (int i = 0; i < contacts.size() && found; i++) {
            if (contacts.get(i).getCar().getCode() == car.getCode()) {
                found = false;
            }
        }
        return found;
    }

    public void setCarStatusToRented(int code) throws SQLException {
        CarDto car = getCarById(code);
        CarStatusDto rentStatus = ServicesLocator.getStatusServices().getStatusByText("Rentado");
        updateCar(car.getCode(),car.getCarID(),rentStatus.getCode(),car.getBrand().getCode(),car.getColor(),car.getMileage());
    }

    public void setCarStatusToAvailable(int code) throws SQLException {
        CarDto car = getCarById(code);
        CarStatusDto rentStatus = ServicesLocator.getStatusServices().getStatusByText("Disponible");
        updateCar(car.getCode(),car.getCarID(),rentStatus.getCode(),car.getBrand().getCode(),car.getColor(),car.getMileage());
    }

    public ArrayList<CarStatusReport> getCarStatusReport() throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contracts = ServicesLocator.getContractServices().getAvailableContract();
        ArrayList<CarStatusReport> report = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call car_status_report()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while (result.next()) {
            LocalDate endOfContract = null;
            boolean found = false;
            for(int i=0; i<contracts.size() && !found;i++){
                if (contracts.get(i).getCar().getCarID().equals(result.getString(1))) {
                    endOfContract = contracts.get(i).getFinalDate();
                    found = true;
                }
            }
            report.add(new CarStatusReport(result.getString(1),result.getString(2),result.getString(3),endOfContract));
        }
        call.close();
        connection.close();
        return report;
    }
}
