package cu.edu.cujae.pweb.dto;

public class CarStatusDto {

    private int code;
    private String status;

    public CarStatusDto(int code, String status) {
        this.code  = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatusText() {return status;}

    public void setStatusText(String status) {this.status = status;}
}
