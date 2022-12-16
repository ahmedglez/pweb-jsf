package cu.edu.cujae.pweb.dto;


public class UserForRecoverCode {

    private String email;
    private String recoveryCode;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRecoveryCode() {
        return recoveryCode;
    }
    public void setRecoveryCode(String recoverCode) {
        this.recoveryCode = recoverCode;
    }

    

    
}
