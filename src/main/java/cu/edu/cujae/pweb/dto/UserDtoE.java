package cu.edu.cujae.pweb.dto;

public class UserDtoE {
    private int code;
    private String name;
    private String password;
    private RoleDtoE role;

    public UserDtoE(int code, String name, String password, RoleDtoE role) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public RoleDtoE getRole() { return role; }

    public void setRole(RoleDtoE role) { this.role = role; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }
}
