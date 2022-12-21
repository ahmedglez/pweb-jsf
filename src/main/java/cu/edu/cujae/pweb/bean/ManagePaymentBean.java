package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.PaymentsDto;
import cu.edu.cujae.pweb.service.PaymentService;
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
public class ManagePaymentBean {

    private PaymentsDto selectedPayment;
    private List<PaymentsDto> payments;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserBean userBean;

    public void newPayment(){
        this.selectedPayment = new PaymentsDto();
    }

    public void loadData(){
        try{
            payments = paymentService.getAll();
        }catch (Exception e){
            PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
        }
    }

    public void savePayment() {
        if (this.selectedPayment.getCode() == 0) {
            paymentService.create(selectedPayment);
            payments = paymentService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_payment_added"
            );
        } else {
            paymentService.update(selectedPayment);
            payments = paymentService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_payment_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('managePaymentDialog').hide()");
        PrimeFaces.current().ajax().update("form1:dt-payment");
    }

    public void deletePayment() {
        try {
            paymentService.delete(selectedPayment.getCode());
            this.selectedPayment = null;

            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_payment_deleted"
            );
            PrimeFaces.current().ajax().update("form1:dt-payment"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    public PaymentsDto getSelectedPayment() {
        return selectedPayment;
    }

    public void setSelectedPayment(PaymentsDto selectedPayment) {
        this.selectedPayment = selectedPayment;
    }

    public List<PaymentsDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsDto> payments) {
        this.payments = payments;
    }
}
