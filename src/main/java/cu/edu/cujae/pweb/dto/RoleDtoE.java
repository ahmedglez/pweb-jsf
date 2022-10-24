package cu.edu.cujae.pweb.dto;

public class RoleDtoE {

    private int code;
    private String roleText;

    public RoleDtoE(int code, String roleText) {
        this.code = code;
        this.roleText = roleText;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getRoleText() { return roleText; }

    public void setRoleText(String roleText) { this.roleText = roleText; }
}
