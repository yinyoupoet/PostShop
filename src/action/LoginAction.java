package action;

import bean.Userinfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware{
    private String lUserName;
    private String lUserPwd;

    private Map session;

    @Autowired
    private UserDao userDao;

    /*
    * 登录
    * */
    public String Login(){
        System.out.println(getlUserName() + ":" + getlUserPwd());
        // 获取用户 id
        int id = userDao.getUserIdByName(getlUserName());
        // 将用户信息封装成一个对象
        Userinfo userinfo = new Userinfo();
        userinfo.setUserId(id);
        userinfo.setUserName(getlUserName());
        userinfo.setUserPwd(getlUserPwd());

        // 将这个用户信息装入session中,在此之前先把session失效
        try {
            logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.put("userinfo",userinfo);
        return SUCCESS;
    }

    public String getlUserName() {
        return lUserName;
    }

    public void setlUserName(String lUserName) {
        this.lUserName = lUserName;
    }

    public String getlUserPwd() {
        return lUserPwd;
    }

    public void setlUserPwd(String lUserPwd) {
        this.lUserPwd = lUserPwd;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    // 注销session
    public String logout() throws Exception {
        if(this.session != null){
            ((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session).invalidate();
        }
        return SUCCESS;
    }
}
