package service;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author hasee
 */
public class RegisterCheckService {
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    /**
     * 判断用户名是否已经存在，如果已经存在则返回false，否则返回true
     * */
    public Boolean isNameDuplicate(String userName){
        return userDao.checkDuplicateName(userName);
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }




}
