package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.ModelDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class ModelServices {

    public ModelDto getModelById(int code) throws SQLException {
        ModelDto model;
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_model(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        model = new ModelDto(result.getInt(1),result.getString(2));
        call.close();
        con.close();
        return model;
    }

    public ModelDto insertModel(String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_model( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, model);
        call.execute();
        call.close();
        function = "{?= call get_last_model()}";
        CallableStatement call2 = connection.prepareCall(function);
        connection.setAutoCommit(false);
        call2.registerOutParameter(1, Types.OTHER);
        call2.execute();
        ResultSet result = (ResultSet) call2.getObject(1);
        result.next();
        ModelDto model1 = new ModelDto(result.getInt(1),result.getString(2));
        call2.close();
        connection.close();
        return model1;
    }

    public void deleteModel(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call delete_model( ? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateModel(int code, String model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String funcion = "{call update_model( ?,? )}";
        CallableStatement call = connection.prepareCall(funcion);
        call.setInt(1, code);
        call.setString(2, model);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<ModelDto> listModel() throws SQLException {
        ArrayList<ModelDto> models = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_model()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            models.add(new ModelDto(result.getInt(1),result.getString(2)));
        }
        call.close();
        connection.close();
        return models;
    }

    public ArrayList<ModelDto> getModelByBrand(String brand) throws SQLException {
        ArrayList<ModelDto> result = new ArrayList<>();
        ArrayList<BrandDto> brands = ServicesLocator.getBrandServices().listBrand();
        for(BrandDto b : brands){
            if(b.getBrandText().equals(brand)){
                result.add(b.getModel());
            }
        }
        return result;
    }

    public ModelDto getModelByText(String model) throws SQLException {
        ArrayList<ModelDto> models = listModel();
        ModelDto result = null;
        boolean found = false;
        for(int i = 0; i<models.size() && !found;i++){
            if(models.get(i).getModelText().equals(model)){
                found = true;
                result = models.get(i);
            }
        }
        return result;
    }

    public boolean canDelete(ModelDto model) throws SQLException {
        ArrayList<BrandDto> brands = ServicesLocator.getBrandServices().listBrand();
        boolean found = true;
        for(int i = 0; i<brands.size() && found;i++){
            if(brands.get(i).getModel().getCode()==model.getCode()){
                found = false;
            }
        }
        return found;
    }

}
