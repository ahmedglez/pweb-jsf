package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.dto.CarModelDto;
import cu.edu.cujae.pweb.service.BrandService;
import cu.edu.cujae.pweb.service.ModelService;
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
public class ManageModelBean {

    private CarModelDto selectedModel;
    private List<CarModelDto> models;
    private List<BrandDto> brands;
    private int selectedBrand;

    @Autowired
    private ModelService modelService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private UserBean userBean;


    public void newModel(){
        selectedBrand = 0;
        selectedModel = new CarModelDto();
    }

    public void loadData(){
        try{
            this.brands = brandService.getAll();
            this.models = modelService.getAll();
        }catch (Exception e){
            PrimeFaces.current().executeScript("PF('manageLoggedDialog').show()");
        }

    }

    public void saveModel() {
        if (this.selectedModel.getCode() == 0) {
            this.selectedModel.setBrand(brandService.getByCode(selectedBrand));
            modelService.create(selectedModel);
            models = modelService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_model_added"
            );
        } else {
            this.selectedModel.setBrand(brandService.getByCode(selectedBrand));
            modelService.update(selectedModel);
            models = modelService.getAll();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_model_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageModelDialog').hide()");
        PrimeFaces.current().ajax().update("form1:dt-model");
    }

    public void deleteModel() {
        try {
           modelService.delete(selectedModel.getCode());
            this.selectedModel = null;

            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_model_deleted"
            );
            PrimeFaces.current().ajax().update("form1:dt-model"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    public CarModelDto getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(CarModelDto selectedModel) {
        this.selectedModel = selectedModel;
    }

    public List<CarModelDto> getModels() {
        return models;
    }

    public void setModels(List<CarModelDto> models) {
        this.models = models;
    }

    public List<BrandDto> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandDto> brands) {
        this.brands = brands;
    }

    public int getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(int selectedBrand) {
        this.selectedBrand = selectedBrand;
    }
}
