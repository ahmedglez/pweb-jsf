package cu.edu.cujae.pweb.dto;

public class ModelDto {
	
	private int cod_model;
	private String model;
	public ModelDto(int cod_model, String model) {
		super();
		this.cod_model = cod_model;
		this.model = model;
	}
	public int getCod_model() {
		return cod_model;
	}
	public void setCod_model(int cod_model) {
		this.cod_model = cod_model;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	

}
