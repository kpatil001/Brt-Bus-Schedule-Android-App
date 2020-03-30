package app.brttimetable.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDtls {

    @SerializedName("userdtlsId")
    @Expose
    private Integer userdtlsId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("loginDtls")
    @Expose
    private LoginDtls loginDtls;

    public Integer getUserdtlsId() {
        return userdtlsId;
    }

    public void setUserdtlsId(Integer userdtlsId) {
        this.userdtlsId = userdtlsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LoginDtls getLoginDtls() {
        return loginDtls;
    }

    public void setLoginDtls(LoginDtls loginDtls) {
        this.loginDtls = loginDtls;
    }
}
