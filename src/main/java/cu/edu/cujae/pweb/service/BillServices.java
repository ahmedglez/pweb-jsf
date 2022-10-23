package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BillDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class BillServices {

    public BillDto returnBill(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_bill( ? )}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        BillDto bill = new BillDto(result.getInt(1), result.getFloat(2),result.getFloat(3));
        call.close();
        con.close();
        return bill;

    }

    public BillDto insertBill(float amount, float special_amount) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_bill( ?, ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setFloat(1, amount);
        call.setFloat(2,special_amount);
        call.execute();
        call.close();
        function = "{?= call get_last_bill()}";
        CallableStatement call2 = connection.prepareCall(function);
        connection.setAutoCommit(false);
        call2.registerOutParameter(1, Types.OTHER);
        call2.execute();
        ResultSet result = (ResultSet) call2.getObject(1);
        result.next();
        BillDto bill = new BillDto(result.getInt(1), result.getFloat(2),result.getFloat(3));
        call2.close();
        connection.close();
        return bill;
    }

    public void deleteBill(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_bill( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void getBillById(int code, float amount, float special_amount) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_bill( ?,?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setFloat(2, amount);
        call.setFloat(3, special_amount);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<BillDto> listBill() throws SQLException {
        ArrayList<BillDto> bills = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_bill()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            bills.add(new BillDto(result.getInt(1),result.getFloat(2), result.getFloat(3)));
        }
        call.close();
        connection.close();
        return bills;
    }
}
