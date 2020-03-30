package app.brttimetable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDtls {
    @SerializedName("logindtlsId")
    @Expose
    private Integer logindtlsId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pwd")
    @Expose
    private String pwd;

    public LoginDtls(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public Integer getLogindtlsId() {
        return logindtlsId;
    }

    public void setLogindtlsId(Integer logindtlsId) {
        this.logindtlsId = logindtlsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
