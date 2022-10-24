package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.dto.ModelDto;
import cu.edu.cujae.pweb.utils.Error;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class BrandServices {

    public BrandDto getBrandById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_brand(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        ModelDto model = ServicesLocator.getModelServices().getModelById(result.getInt(3));
        BrandDto brand = new BrandDto(result.getInt(1), result.getString(2),model);
        call.close();
        con.close();
        return brand;

    }

    public void insertBrand(String brand,int cod_model) throws SQLException {
        ArrayList<BrandDto> brands = listBrand();
        boolean found = false;
        for(int i = 0;i<brands.size()&&!found;i++){
            if(brands.get(i).getBrandText().equals(brand)){
                if(brands.get(i).getModel().getCode()==cod_model){
                    found = true;
                }
            }
        }
        if(!found) {
            java.sql.Connection connection = ServicesLocator.getConnection();
            String function = "{call insert_brand( ?,? )}";
            CallableStatement call = connection.prepareCall(function);
            call.setString(1, brand);
            call.setInt(2, cod_model);
            call.execute();
            call.close();
            connection.close();
        }else{
            Error error = new Error("La marca y modelo insertada ya existe");
            System.out.println(error.getErrorMsg());
        }
    }

    public void deleteBrand(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_brand(?)}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updateBrand(int code, String brand,int cod_model) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_brand( ?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, brand);
        call.setInt(3,cod_model);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<BrandDto> listBrand() throws SQLException {
        ArrayList<BrandDto> brands = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_brand()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            ModelDto model = ServicesLocator.getModelServices().getModelById(result.getInt(3));
            brands.add(new BrandDto(result.getInt(1), result.getString(2),model));
        }
        call.close();
        connection.close();
        return brands;
    }

     public BrandDto getBrandByText(String brand, String model) throws SQLException {
        ArrayList<BrandDto> brands = listBrand();
        BrandDto result = null;
        boolean found = false;
        for(int i = 0; i<brands.size() && !found;i++){
            if(brands.get(i).getBrandText().equals(brand) && brands.get(i).getModel().getModelText().equals(model)){
                found = true;
                result = brands.get(i);
            }
        }
        return result;
     }

     public void insertBrandWithModel(String brand, String model) throws SQLException {
        ModelDto model_insert= ServicesLocator.getModelServices().getModelByText(model);
        if(model_insert!=null){
            insertBrand(brand,model_insert.getCode());
        }else{
            model_insert = ServicesLocator.getModelServices().insertModel(model);
            insertBrand(brand,model_insert.getCode());
        }
     }

    public void updateBrandWithModel(int code, String brand, String model) throws SQLException {
        ModelDto model_insert= ServicesLocator.getModelServices().getModelByText(model);
        if(model_insert!=null){
            updateBrand(code, brand,model_insert.getCode());
        }else{
            model_insert = ServicesLocator.getModelServices().insertModel(model);
            updateBrand(code, brand,model_insert.getCode());
        }
    }

    public boolean canDelete(BrandDto brand) throws SQLException, ClassNotFoundException {
        ArrayList<CarDto> cars = ServicesLocator.getCarsServices().listCars();
        boolean found = true;
        for(int i = 0; i<cars.size() && found;i++){
            if(cars.get(i).getBrand().getCode()==brand.getCode()){
                found = false;
            }
        }
        return found;
    }


}
