package test;

import bean.Firstdirectory;
import bean.Goodinfo;
import bean.Seconddirectory;
import dao.GoodsDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.LoginCheckService;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args){
        ApplicationContext ac = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
        /*Address ail = (Address) ac.getBean("address");

        Userinfo userinfo = new Userinfo();
        userinfo.setUserName("zk");
        userinfo.setUserPwd("135");
        ail.add(userinfo);*/
       /* LoginCheckService loginCheckService = new LoginCheckService();*/

        GoodsDao goodsDao = (GoodsDao) ac.getBean("goodsDao");

        System.out.println(goodsDao.getFirstDirectoryIdBySecondDirectoryId(3));
        /*List<Goodinfo> goodinfos = goodsDao.getGoodsInFirstDirectory(1);
        for(Goodinfo goodinfo : goodinfos){
            System.out.println(goodinfo.getId() + ":" + goodinfo.getGoodName());
        }*/

        /*Map<Integer, Firstdirectory> map = goodsDao.getFirstDirectories();
        for(Integer id : map.keySet()){
            System.out.println(id + " " + map.get(id).getCategoryName());
        }*/
        /*List<Seconddirectory> seconddirectories = goodsDao.getSecondDirectories();
        List<Goodinfo> goodinfos = goodsDao.getGoodsInSecondDirectory(3);
        List<Goodinfo> searchGoodinfos = goodsDao.getGoodsByName("酒");

        System.out.println("二级目录");
        for(Seconddirectory seconddirectory : seconddirectories){
            System.out.println(seconddirectory.getId() + ":" + seconddirectory.getDirectoryName());
        }
        System.out.println("二级目录id为3的商品");
        for(Goodinfo goodinfo : goodinfos){
            System.out.println(goodinfo.getId() + ":" + goodinfo.getGoodName());
        }
        System.out.println("含酒的商品");
        for(Goodinfo goodinfo : searchGoodinfos){
            System.out.println(goodinfo.getId() + ":" + goodinfo.getGoodName());
        }*/



    }
}
