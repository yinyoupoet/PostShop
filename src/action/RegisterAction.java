package action;

import bean.Userinfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RegisterAction extends ActionSupport implements SessionAware{
    private String rUserName;
    private String rUserPwd;
    private Map session;

    @Autowired
    private UserDao userDao;

    /*
    * 进行注册
    * */
    public String doRegister(){
        // 先将数据封装成一个对象
        Userinfo userinfo = new Userinfo();
        userinfo.setUserName(getrUserName());
        userinfo.setUserPwd(getrUserPwd());

        // 将数据保存到数据库
        userDao.addUser(userinfo);

        // 查询用户id并保存到userinfo中
        userinfo.setUserId(userDao.getUserIdByName(getrUserName()));

        // 获取session并将userinfo保存到session
        // 在此之前先要清除原来的session
        try {
            logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.put("userinfo",userinfo);
        return SUCCESS;
    }


    public String getrUserName() {
        return rUserName;
    }

    public void setrUserName(String rUserName) {
        this.rUserName = rUserName;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getrUserPwd() {
        return rUserPwd;

    }

    public void setrUserPwd(String rUserPwd) {
        this.rUserPwd = rUserPwd;
    }


    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    // 注销session
    public String logout() throws Exception {
        if(this.session != null){
            ((org.apache.struts2.dispatcher.SessionMap<String, Object>) this.session).invalidate();
        }
        return SUCCESS;
    }

}
