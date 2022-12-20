package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.service.DriverCategoryService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageCategoryBean {

    private DriversCategoriesDto selectedCategory;
    private List<DriversCategoriesDto> categories;

    @Autowired
    private DriverCategoryService driverCategoryService;

    public void newCategory(){
        this.selectedCategory = new DriversCategoriesDto();
        this.selectedCategory.setCode(-1);
    }

    public void saveCategory() {
        if (this.selectedCategory.getCode() == 0) {
            driverCategoryService.create(selectedCategory);
            categories = getAllWithOutSinCategory();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_category_added"
            );
        } else {
            driverCategoryService.update(selectedCategory);
            categories = getAllWithOutSinCategory();
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_category_edited"
            );
        }

        PrimeFaces.current().executeScript("PF('manageCategoryDialog').hide()");
        PrimeFaces.current().ajax().update("form1:dt-category");
    }

    public void deleteCategory() {
        try {
            driverCategoryService.delete(selectedCategory.getCode());
            this.selectedCategory = null;

            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_INFO,
                    "message_category_deleted"
            );
            PrimeFaces.current().ajax().update("form1:dt-category"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(
                    null,
                    FacesMessage.SEVERITY_ERROR,
                    "message_error"
            );
        }
    }

    private List<DriversCategoriesDto> getAllWithOutSinCategory(){
        List<DriversCategoriesDto> allWhitOutSinCategory = new ArrayList<>();
        for(DriversCategoriesDto d: driverCategoryService.getAll()){
            if(d.getCode()!=0){
                allWhitOutSinCategory.add(d);
            }
        }
        return allWhitOutSinCategory;
    }

    public DriversCategoriesDto getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(DriversCategoriesDto selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<DriversCategoriesDto> getCategories() {
        categories = getAllWithOutSinCategory();
        return categories;
    }

    public void setCategories(List<DriversCategoriesDto> categories) {
        this.categories = categories;
    }
}
