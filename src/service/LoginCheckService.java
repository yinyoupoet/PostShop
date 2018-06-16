package service;

import bean.Userinfo;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author hasee
 */
public class LoginCheckService {

    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    /*
    *  判断登录的用户名和密码是否正确
    * */
    public Boolean isInfoRight(String name, String pwd){
        Userinfo userinfo = new Userinfo();
        userinfo.setUserName(name);
        userinfo.setUserPwd(pwd);
        return userDao.checkLogin(userinfo);
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
