package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

import java.util.Map;

public class LogOutAction extends ActionSupport implements SessionAware{
    Map session;

    public String doLogOut(){
        try {
            logout();
        } catch (Exception e) {
            return "input";
        }
        return SUCCESS;
    }

    // 注销session
    public void logout() throws Exception {
        if(this.session != null){
            ((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session).invalidate();
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
