package dao;

import bean.Firstdirectory;
import bean.Goodinfo;
import bean.Seconddirectory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GoodsDao {

    @Autowired
    private SessionFactory sessionFactory;


    /*
    * 查询所有一级分类，键为一级分类的id，值为一级分类的对象
    * */
    public Map<Long,Firstdirectory> getFirstDirectories(){
        Session session = sessionFactory.openSession();
        String hql = "select firstdirectory from Firstdirectory firstdirectory";
        // 获取第一类目录的所有 List
        List<Firstdirectory> firstdirectories = session.createQuery(hql).list();
        Map<Long,Firstdirectory> firstdirectoryMap = new LinkedHashMap<>();
        for(Firstdirectory firstdirectory : firstdirectories){
            long id = firstdirectory.getId();
            firstdirectoryMap.put(id,firstdirectory);
        }
        return firstdirectoryMap;
    }

    /*
    * 查询所有二级分类
    * */
    public List<Seconddirectory> getSecondDirectories(){
        Session session = sessionFactory.openSession();
        String hql = "select secondDirectory from Seconddirectory secondDirectory";
        List<Seconddirectory> seconddirectories = session.createQuery(hql).list();
        return seconddirectories;
    }

    /*
    * 获取所有商品列表
    * */
    public List<Goodinfo> getAllGoods(){
        Session session = sessionFactory.openSession();
        String hql = "select goodinfo from Goodinfo goodinfo";
        List<Goodinfo> goodinfos = session.createQuery(hql).list();
        return goodinfos;
    }

    /*
    * 获取某一个二级分类下的所有商品
    * 传入的参数是二级分类的id
    * */
    public List<Goodinfo> getGoodsInSecondDirectory(int secondDirectoryId){
        Session session = sessionFactory.openSession();
        String hql = "select goodinfo from Goodinfo goodinfo where secondDirectoryId = ?";
        List<Goodinfo> goodinfos = session.createQuery(hql).setParameter(0,secondDirectoryId).list();
        return goodinfos;
    }

    /*
    * 根据搜索返回商品，商品名模糊查询
    * 输入的参数是商品名
    * */
    public List<Goodinfo> getGoodsByName(String name){
        Session session = sessionFactory.openSession();
        String hql = "select goodinfo from Goodinfo goodinfo where goodName like ?";
        List<Goodinfo> goodinfos = session.createQuery(hql).setParameter(0,"%" + name + "%").list();
        return goodinfos;
    }

    /*
    * 通过一级目录的id查询所有商品
    * */
    public List<Goodinfo> getGoodsInFirstDirectory(int id){
        Session session = sessionFactory.openSession();
        //String hql = "from Goodinfo goodinfo LEFT JOIN goodinfo.secondDirectoryId LEFT JOIN Seconddirectory sd where goodinfo.secondDirectoryId = sd.id AND sd.firstDirectoryId = ?";
        String sql = "SELECT * FROM goodinfo AS gi,secondDirectory AS sd WHERE gi.secondDirectoryId = sd.id AND sd.firstDirectoryId =  "+ id;
        SQLQuery q = session.createSQLQuery(sql).addEntity(Goodinfo.class);
        List<Goodinfo> goodinfos = q.list();
        return goodinfos;
    }

    /*
    * 通过二级分类分类编号查找一级分类id
    * */
    public int getFirstDirectoryIdBySecondDirectoryId(int secondId){
        Session session = sessionFactory.openSession();
        String hql = "SELECT firstDirectoryId FROM Seconddirectory sd where id = ?";
        int id = (int) session.createQuery(hql).setParameter(0,secondId).uniqueResult();
        return id;
    }

    /*
    *  通过二级分类id返回二级分类对象
    * */
    public Seconddirectory getSecondDirectoryById(int id){
        Session session  = sessionFactory.openSession();
        String hql = "FROM Seconddirectory sd WHERE sd.id = ?";
        Seconddirectory seconddirectory = (Seconddirectory) session.createQuery(hql).setParameter(0,id).uniqueResult();
        return seconddirectory;
    }

    /*
    * 通过一级分类id返回一级分类对象
    * */
    public Firstdirectory getFirstDirectoryById(int id){
        Session session  = sessionFactory.openSession();
        String hql = "FROM Firstdirectory fd WHERE fd.id = ?";
        Firstdirectory firstdirectory = (Firstdirectory) session.createQuery(hql).setParameter(0,id).uniqueResult();
        return firstdirectory;
    }

    /*
    * 通过商品id获取商品信息
    * */
    public Goodinfo getGoodInfoByGoodId(int id){
        Session session = sessionFactory.openSession();
        String hql = "FROM Goodinfo goodinfo WHERE goodinfo.id = ?";
        Goodinfo goodinfo = (Goodinfo) session.createQuery(hql).setParameter(0,id).uniqueResult();
        return goodinfo;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
