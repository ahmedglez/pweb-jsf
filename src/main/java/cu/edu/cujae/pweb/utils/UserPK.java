package cu.edu.cujae.pwebjsf.data.utils;

import java.io.Serializable;
import java.util.Objects;

public class UserPK implements Serializable {
    private Integer codeUser;
    private Integer codeRole;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codeUser);
        hash = 59 * hash + Objects.hashCode(this.codeRole);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPK other = (UserPK) obj;
        if (!Objects.equals(this.codeUser, other.codeUser)) {
            return false;
        }
        if (!Objects.equals(this.codeRole, other.codeRole)) {
            return false;
        }
        return true;
    }

    public Integer getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(Integer codeUser) {
        this.codeUser = codeUser;
    }

    public Integer getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(Integer codeRole) {
        this.codeRole = codeRole;
    }
}
