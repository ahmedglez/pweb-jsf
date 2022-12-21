package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.service.BrandService;
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
public class ManageBrandBean {

    private BrandDto selectedBrand;
    private List<BrandDto> brands;

    @Autowired
    private BrandService brandService;

    public void newBrand(){
        this.selectedBrand = new BrandDto();
    }

    public void loadData(){
        this.brands = brandService.getAll();
    }

    public void saveBrand() {
        if (this.selectedBrand.getCode() == 0) {
            brandService.create(selectedBrand);
            brands = brandService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_brand_added"
            );
        } else {
            brandService.update(selectedBrand);
            brands = brandService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_brand_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageBrandDialog').hide()");
        PrimeFaces.current().ajax().update("form1:dt-brand");
    }

    public void deleteBrand() {
        try {
            brandService.delete(selectedBrand.getCode());
            this.selectedBrand = null;

            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_brand_deleted"
            );
            PrimeFaces.current().ajax().update("form1:dt-brand"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    public BrandDto getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(BrandDto selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public List<BrandDto> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandDto> brands) {
        this.brands = brands;
    }
}
