package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class ToLoginOrRegisterAction extends ActionSupport implements SessionAware {
    private Map session;
    public String showLoginOrRegister(){
        if(session.get("userinfo") != null){
            return "index";
        }
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
