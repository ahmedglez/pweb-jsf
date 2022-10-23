package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.dto.ModelDto;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class StatusServices {

    public CarStatusDto getStatusById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_status(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        CarStatusDto status = new CarStatusDto(result.getInt(1), result.getString(2));
        call.close();
        con.close();
        return status;

    }

    public CarStatusDto getStatusByText(String status) throws SQLException {
        CarStatusDto result = null;
        ArrayList<CarStatusDto> statuses = listStatus();
        boolean found = false;
        for(int i = 0;i<statuses.size() && !found; i++){
            if(statuses.get(i).getStatusText().equalsIgnoreCase(status)){
                result = statuses.get(i);
                found = true;
            }
        }
        return result;
    }

    public void insertStatus(String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_status( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, status);
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteStatus(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_staus( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateStatus(int code, String status) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_status( ?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, status);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<CarStatusDto> listStatus() throws SQLException {
        ArrayList<CarStatusDto> statuses = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_status()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            statuses.add(new CarStatusDto(result.getInt(1),result.getString(2)));
        }
        call.close();
        connection.close();
        return statuses;
    }

    public boolean canDelete(CarStatusDto status) throws SQLException, ClassNotFoundException {
        ArrayList<CarDto> cars = ServicesLocator.getCarsServices().listCars();
        boolean found = true;
        for(int i = 0; i<cars.size() && found;i++){
            if(cars.get(i).getStatus().getCode()==status.getCode()){
                found = false;
            }
        }
        return found;
    }

}
