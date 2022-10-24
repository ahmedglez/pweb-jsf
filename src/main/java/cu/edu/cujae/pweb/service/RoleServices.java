package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.RoleDtoE;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class RoleServices {

    public ArrayList<RoleDtoE> listRoles() throws SQLException {
        ArrayList<RoleDtoE> roles = new ArrayList<>();
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        String function = "{?= call list_roles()}";
        CallableStatement call = connection.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        while(result.next()){
            int code = result.getInt(1);
            String role = result.getString(2);
            roles.add(new RoleDtoE(code,role));
        }
        call.close();
        connection.close();
        return roles;
    }

    public RoleDtoE getRoleById(int code) throws SQLException {
        java.sql.Connection con = ServicesLocator.getConnection();
        String function = "{?= call return_role(?)}";
        con.setAutoCommit(false);
        CallableStatement call = con.prepareCall(function);
        call.registerOutParameter(1, Types.OTHER);
        call.setInt(2, code);
        call.execute();
        ResultSet result = (ResultSet) call.getObject(1);
        result.next();
        RoleDtoE role = new RoleDtoE(result.getInt(1),result.getString(2));
        call.close();
        con.close();
        return role;
    }

    public RoleDtoE getRoleByText(String role) throws SQLException {
        ArrayList<RoleDtoE> roles = listRoles();
        RoleDtoE result = null;
        boolean found = false;
       for(int i = 0;i<roles.size() && !found;i++){
            if(roles.get(i).getRoleText().equals(role)){
                found = true;
                result = new RoleDtoE(roles.get(i).getCode(),roles.get(i).getRoleText());
            }
        }
       return result;
    }



}
