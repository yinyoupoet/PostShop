package test;

import bean.Userinfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.Address;

public class Test {
    public static void main(String[] args){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        Address ail = (Address) ac.getBean("address");

        Userinfo userinfo = new Userinfo();
        userinfo.setUserName("zk");
        userinfo.setUserPwd("135");
        ail.add(userinfo);
    }
}