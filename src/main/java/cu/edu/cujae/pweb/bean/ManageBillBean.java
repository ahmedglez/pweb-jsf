package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BillDto;
import cu.edu.cujae.pweb.service.BillService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageBillBean {

    private BillDto selectedBill;
    private List<BillDto> bills;

    @Autowired
    private BillService billService;

    @Autowired
    private UserBean userBean;

    public void newBill(){
        this.selectedBill = new BillDto();
    }

    public void loadData(){
        try{
            bills = billService.getAll();
        }catch (Exception e){
            PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
        }

    }

    public void saveBill() {
        if (this.selectedBill.getCode() == 0) {
            billService.create(selectedBill);
            bills = billService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_bill_added"
            );
        } else {
            billService.update(selectedBill);
            bills = billService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_bill_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageBillDialog').hide()");
        PrimeFaces.current().ajax().update("form1:dt-bill");
    }

    public void deleteBill() {
        try {
            billService.delete(selectedBill.getCode());
            this.selectedBill = null;

            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_bill_deleted"
            );
            PrimeFaces.current().ajax().update("form1:dt-bill"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    public BillDto getSelectedBill() {
        return selectedBill;
    }

    public void setSelectedBill(BillDto selectedBill) {
        this.selectedBill = selectedBill;
    }

    public List<BillDto> getBills() {
        return bills;
    }

    public void setBills(List<BillDto> bills) {
        this.bills = bills;
    }
}
