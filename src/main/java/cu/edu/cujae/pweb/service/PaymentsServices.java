package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.ContractDto;
import cu.edu.cujae.pweb.dto.ModelDto;
import cu.edu.cujae.pweb.dto.PaymentsDto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class PaymentsServices {

    public PaymentsDto getPaymentById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_payment( ? )}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        PaymentsDto payment = new PaymentsDto(result.getInt(1), result.getString(2));
        call.close();
        con.close();
        return payment;

    }

    public void insertPayment(String payment) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call insert_payment( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setString(1, payment);
        call.execute();
        call.close();
        connection.close();
    }

    public void deletePayment(int code) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call delete_payment( ? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.execute();
        call.close();
        connection.close();
    }

    public void updatePayment(int code, String payment) throws SQLException {
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{call update_payment( ?,? )}";
        CallableStatement call = connection.prepareCall(function);
        call.setInt(1, code);
        call.setString(2, payment);
        call.execute();
        call.close();
        connection.close();
    }

    public ArrayList<PaymentsDto> listPaymaent() throws SQLException {
        ArrayList<PaymentsDto> payments = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String function = "{?= call list_payment()}";
        connection.setAutoCommit(false);
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            payments.add(new PaymentsDto(result.getInt(1),result.getString(2)));
        }
        call.close();
        connection.close();
        return payments;
    }
    public boolean canDelete(PaymentsDto payment) throws SQLException, ClassNotFoundException {
        ArrayList<ContractDto> contacts = ServicesLocator.getContractServices().listContract();
        boolean found = true;
        for(int i = 0; i<contacts.size() && found;i++){
            if(contacts.get(i).getPayment().getCode()==payment.getCode()){
                found = false;
            }
        }
        return found;
    }
}
