package cu.edu.cujae.pweb.dto;

public class ModelDto {
    private int code;
    private String model;

    public ModelDto(int code, String model) {
        this.code = code;
        this.model = model;
    }

    public String getModelText() {
        return model;
    }

    public void setModelText(String model) {
        this.model = model;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
