package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.dto.ModelDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class DriverCategoryServices {

    public DriversCategoriesDto getDriverCategoryById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_driver_category( ? )}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        DriversCategoriesDto category = new DriversCategoriesDto(result.getInt(1), result.getString(2));
        call.close();
        con.close();
        return category;

    }

    public void insertDriverCategory(String category) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_driver_category( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, category);
        call.execute();
        call.close();
        connection.close();
    }

    public void deleteDriverCategory(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_driver_category( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateDriverCategory(int code, String category) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_driver_category( ?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, category);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<DriversCategoriesDto> listDriverCategories() throws SQLException {
        ArrayList<DriversCategoriesDto> categories = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_driver_category()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            categories.add(new DriversCategoriesDto(result.getInt(1),result.getString(2)));
        }
        call.close();
        connection.close();
        return categories;
    }

    public boolean canDelete(DriversCategoriesDto category) throws SQLException, ClassNotFoundException {
        ArrayList<DriverDto> drivers = ServicesLocator.getDriverServices().listDriver();
        boolean found = true;
        for(int i = 0; i<drivers.size() && found;i++){
            if(drivers.get(i).getCategory().getCode()==category.getCode()){
                found = false;
            }
        }
        return found;
    }
}
