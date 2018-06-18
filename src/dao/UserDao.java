package dao;

import bean.Userinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserDao {



    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    /*
    * 检查能否登录
    * @param userinfo 传入一个用户信息，判断能否登录
    * 能登陆返回true，否则返回false
    * */
    public boolean checkLogin(Userinfo userinfo){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) from Userinfo userinfo where userName = ? AND userPwd = ?";
        Long num = (Long) session.createQuery(hql).setParameter(0,userinfo.getUserName()).setParameter(1,userinfo.getUserPwd()).uniqueResult();
        if(num >= 1){
            return true;
        }
        return false;
    }

    /*
    *  用于在注册时，检查重复的用户名
    * */
    public boolean checkDuplicateName(String userName){
        Session session = sessionFactory.openSession();
        String hql = "select count(id) from Userinfo userinfo where userName = ?";
        Long num = (Long) session.createQuery(hql).setParameter(0,userName).uniqueResult();
        System.out.println("register: " + num + " " + userName);
        if(num >= 1){
            return false;
        }
        return true;
    }

    /*
    *  通过用户名获取用户id并返回
    * */
    public int getUserIdByName(String userName){
        Session session = sessionFactory.openSession();
        String hql = "select userId from Userinfo userinfo where userName = ?";
        int id = (int) session.createQuery(hql).setParameter(0,userName).uniqueResult();
        return id;
    }

    /*
    * 用户注册，用于将用户信息保存到数据库
    * */
    public void addUser(Userinfo userinfo){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(userinfo);
        tx.commit();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
