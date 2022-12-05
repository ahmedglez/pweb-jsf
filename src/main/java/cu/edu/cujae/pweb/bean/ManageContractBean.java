package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.service.BillService;
import cu.edu.cujae.pweb.service.ContractService;
import cu.edu.cujae.pweb.service.PaymentService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@Component
@ManagedBean
public class ManageContractBean {

    private ContractDto contractDto;
    private ContractDto selectedContract;
    private List<ContractDto> contracts;

    @Autowired
    private ContractService contractService;
    @Autowired
    private BillService billService;
    @Autowired
    private PaymentService paymentService;

    private List<CarDto> cars;
    private List<TouristDto> tourists;
    private List<DriverDto> drivers;
    private List<BillDto> bills;
    private List<PaymentsDto> payments;

    @PostConstruct
    public void init(){
        contracts = contractService.getAll();
        bills = billService.getAll();
        payments = paymentService.getAll();
    }

    public void newContract() {
        this.selectedContract = new ContractDto();
    }

    public void updateContract(ContractDto contract) {
        this.selectedContract = contract;
    }


    public void deleteContract() {
        /*
        try {
            service.delete(this.selectedTourist.getCode());
            this.selectedTourist = null;
            tourists = service.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_tourist_deleted"
            );
            PrimeFaces.current().ajax().update("form:dt-tourist"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }

         */
    }

    public void saveContract() {
        /*
        if (this.selectedTourist.getCode() == 0) {
            boolean repeatedId = service.existID(this.selectedTourist.getCode());
            if (!repeatedId) {
                service.create(this.selectedTourist);
                tourists = service.getAll();
                JsfUtils.addMessageFromBundle(
                        null,
                        FacesMessage.SEVERITY_INFO,
                        "message_tourist_added"
                );
            } else {
                JsfUtils.addMessageFromBundle(
                        null,
                        FacesMessage.SEVERITY_ERROR,
                        "message_error_id_already_exists"
                );
            }
        } else {
            service.update(this.selectedTourist);
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_tourist_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageTouristDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-tourist"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

         */

    }

    public ContractDto getContractDto() {
        return contractDto;
    }

    public void setContractDto(ContractDto contractDto) {
        this.contractDto = contractDto;
    }

    public ContractDto getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(ContractDto selectedContract) {
        this.selectedContract = selectedContract;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public List<TouristDto> getTourists() {
        return tourists;
    }

    public void setTourists(List<TouristDto> tourists) {
        this.tourists = tourists;
    }

    public List<DriverDto> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDto> drivers) {
        this.drivers = drivers;
    }

    public List<BillDto> getBills() {
        return bills;
    }

    public void setBills(List<BillDto> bills) {
        this.bills = bills;
    }

    public List<PaymentsDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsDto> payments) {
        this.payments = payments;
    }
}
