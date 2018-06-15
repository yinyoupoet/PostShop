package action;

import bean.Userinfo;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import service.AddressImpl;

public class AddressAction extends ActionSupport {

    private String name;
    private String pwd;

    @Autowired
    private AddressImpl address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String add(){
        System.out.println("进来了");
        Userinfo ui = new Userinfo();
        ui.setUserName(getName());
        ui.setUserPwd(getPwd());
        address.add(ui);
        System.out.println(getName());
        return SUCCESS;
    }

    public void setAddress(AddressImpl address){
        this.address = address;
    }
}
