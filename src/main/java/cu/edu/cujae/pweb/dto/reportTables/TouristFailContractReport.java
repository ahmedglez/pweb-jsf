package cu.edu.cujae.pweb.dto.reportTables;

import java.time.LocalDate;

public class TouristFailContractReport {

    private String name;
    private String lastName;
    private LocalDate finalDate;
    private LocalDate deliveryDate;

    public TouristFailContractReport(String name, String lastName, LocalDate finalDate, LocalDate deliveryDate) {
        this.name = name;
        this.lastName = lastName;
        this.finalDate = finalDate;
        this.deliveryDate = deliveryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
