package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.utils.Connection;

import java.sql.SQLException;

public class ServicesLocator {

    private static CarsServices carsServices = null;
    private static BrandServices brandServices = null;
    private static ModelServices modelServices = null;
    private static UserServices userServices = null;
    private static StatusServices statusServices = null;
    private static TouristServices touristServices = null;
    private static ContractServices contractServices = null;
    private static DriverServices driverServices = null;
    private static BillServices billServices = null;
    private static DriverCategoryServices driverCategoryServices = null;
    private static PaymentsServices paymentsServices = null;
    private static RoleServices roleServices = null;

    public static UserServices getUserServices(){
        if(userServices == null){
            userServices = new UserServices();
        }
        return userServices;
    }

    public static RoleServices getRoleServices(){
        if(roleServices == null){
            roleServices = new RoleServices();
        }
        return roleServices;
    }

    public static TouristServices getTouristServices(){
        if(touristServices == null){
            touristServices = new TouristServices();
        }
        return touristServices;
    }


    public static StatusServices getStatusServices(){
        if(statusServices == null){
            statusServices = new StatusServices();
        }
        return statusServices;
    }


    public static CarsServices getCarsServices(){
        if(carsServices == null){
            carsServices = new CarsServices();
        }
        return carsServices;
    }

    public static BrandServices getBrandServices(){
        if(brandServices == null){
            brandServices = new BrandServices();
        }
        return brandServices;
    }

    public static ContractServices getContractServices(){
        if(contractServices == null){
            contractServices = new ContractServices();
        }
        return contractServices;
    }
    public static DriverServices getDriverServices(){
        if(driverServices == null){
            driverServices = new DriverServices();
        }
        return driverServices;
    }
    public static BillServices getBillServices(){
        if(billServices == null){
            billServices = new BillServices();
        }
        return billServices;
    }

    public static DriverCategoryServices getDriverCategoryServices(){
        if(driverCategoryServices == null){
            driverCategoryServices = new DriverCategoryServices();
        }
        return driverCategoryServices;
    }

    public static PaymentsServices getPaymentsServices(){
        if(paymentsServices == null){
            paymentsServices = new PaymentsServices();
        }
        return paymentsServices;
    }

    public static ModelServices getModelServices(){
        if(modelServices == null){
            modelServices = new ModelServices();
        }
        return modelServices;
    }

    public static java.sql.Connection getConnection(){
        Connection connection = null;
        try {
            connection = new Connection("localhost", "Rent_Car", "postgres", "1234");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection.getConnection();
    }

}
