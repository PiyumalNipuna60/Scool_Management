package views.TM;

import java.awt.*;

public class tableTm {
    String sId;
    String sName;
    String sEmail;
    String sContact;
    String sAddress;
    String sNic;
    Button btn;

    public tableTm() {
    }

    public tableTm(String sId, String sName, String sEmail, String sContact, String sAddress, String sNic, Button btn) {
        this.sId = sId;
        this.sName = sName;
        this.sEmail = sEmail;
        this.sContact = sContact;
        this.sAddress = sAddress;
        this.sNic = sNic;
        this.btn = btn;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsContact() {
        return sContact;
    }

    public void setsContact(String sContact) {
        this.sContact = sContact;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsNic() {
        return sNic;
    }

    public void setsNic(String sNic) {
        this.sNic = sNic;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "tableTm{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sEmail='" + sEmail + '\'' +
                ", sContact='" + sContact + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", sNic='" + sNic + '\'' +
                ", btn=" + btn +
                '}';
    }
}
