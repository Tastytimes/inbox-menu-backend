package com.rs.listing.Restuarant.listing.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor

//@NamedQuery(name="AdminUsers.updatePasswordByEmail", query="update AdminUsers u set u.password=:password where u.email=:email")
public class AdminUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String password;
    private Boolean isFirstTimeLogin;
    private Boolean status;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private AdminRoles adminRoles;




//    public AdminUsers(int id, String fullName, String email, String password, Boolean isFirstTimeLogin, Boolean status, AdminRoles adminRoles) {
//        this.id = id;
//        this.fullName = fullName;
//        this.email = email;
//        this.password = password;
//        this.isFirstTimeLogin = isFirstTimeLogin;
//        this.status = status;
//        this.adminRoles = adminRoles;
//    }

//    @Override
//    public String toString() {
//        return "AdminUsers{" +
//                "id=" + id +
//                ", fullName='" + fullName + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", isFirstTimeLogin=" + isFirstTimeLogin +
//                ", status=" + status +
//                ", adminRoles=" + adminRoles +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AdminRoles getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(AdminRoles adminRoles) {
        this.adminRoles = adminRoles;
    }
    @Override
    public String toString() {
        return "AdminRoles{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
