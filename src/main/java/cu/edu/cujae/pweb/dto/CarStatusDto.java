package cu.edu.cujae.pweb.dto;

public class CarStatusDto {
	
	private int cod_status;
	private String status;
	public CarStatusDto(int cod_status, String status) {
		super();
		this.cod_status = cod_status;
		this.status = status;
	}
	
	public CarStatusDto (String status) {
		if(status.equals("disponible") ) {
    		this.status = status;
    		this.cod_status = 1;
    	}else if(status.equals("rentado")) {
    		this.status = status;
    		this.cod_status = 2;
    	}
    	else if(status.equals("taller")) {
    		this.status = status;
    		this.cod_status = 3;
    	}
	}
	
	public int getCod_status() {
		return cod_status;
	}
	public void setCod_status(int cod_status) {
		this.cod_status = cod_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
