package action;

import bean.Firstdirectory;
import bean.Goodinfo;
import bean.Seconddirectory;
import com.opensymphony.xwork2.ActionSupport;
import dao.GoodsDao;
import dao.UserDao;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ToIndexAction extends ActionSupport implements ServletRequestAware{
    @Autowired
    private GoodsDao goodsDao;

    private HttpServletRequest request;
    /*
    * 设置到request的，有两个Map，
    * FirstMap<Long,Firstdirectory>  firstdirectoryMap : 一级分类Id，一级分类对象
    * SecondMap<Long,List<Seconddirectory>> seconddirectoryMap: 一级分类Id，该一级分类下二级分类的对象列表
    * 有四个字段：
    * firstDirectory：一级目录名称
    * secondDirectory：二级目录名称
    * searchTitle：搜索内容
    * goodsList：所有要显示的商品的列表
    * */
    @Override
    public String execute(){

        // 一级分类Map
        Map<Long, Firstdirectory> firstdirectoryMap = goodsDao.getFirstDirectories();
        request.setAttribute("firstdirectoryMap",firstdirectoryMap);
        // 二级分类List
        List<Seconddirectory> seconddirectories = goodsDao.getSecondDirectories();
        Map<Long, List<Seconddirectory>> seconddirectoryMap = new LinkedHashMap<>();
        // 将二级分类加入到这个map中，key是一级分类id，value是这个一级分类下二级分类的对象列表
        for(Seconddirectory seconddirectory : seconddirectories){
            long id = seconddirectory.getFirstDirectoryId();
            if(seconddirectoryMap.containsKey(id)){
                seconddirectoryMap.get(id).add(seconddirectory);
            }else{
                List<Seconddirectory> list = new LinkedList<>();
                list.add(seconddirectory);
                seconddirectoryMap.put(id,list);
            }
        }
        request.setAttribute("seconddirectoryMap",seconddirectoryMap);
        // 传给jsp的商品列表
        List<Goodinfo> goodsList;
        // 判断url中是否有参数，如果有就根据情况设置显示的商品列表，否则就显示所有商品
        // 优先级：搜索 > 二级目录 > 一级目录 > 默认（所有）

        // 如果有搜索
        String searchTitle = request.getParameter("searchTitle");
        if(searchTitle != null){
            goodsList = goodsDao.getGoodsByName(searchTitle);
            request.setAttribute("searchTitle",searchTitle);
        }else{
            // 如果有二级目录，则查询一级目录
            String secondId = request.getParameter("secondId");
            if(secondId != null){
                try {
                    goodsList = goodsDao.getGoodsInSecondDirectory(Integer.parseInt(secondId));
                    int firstId = goodsDao.getFirstDirectoryIdBySecondDirectoryId(Integer.parseInt(secondId));
                    Firstdirectory firstdirectory = goodsDao.getFirstDirectoryById(firstId);
                    Seconddirectory seconddirectory = goodsDao.getSecondDirectoryById(Integer.parseInt(secondId));
                    request.setAttribute("firstDirectory",firstdirectory);
                    request.setAttribute("secondDirectory",seconddirectory);

                }catch (Exception e){
                    return "input";
                }
            }else {
                // 如果有一级目录，则查询一级目录
                String firstId = request.getParameter("firstId");
                if(firstId != null){
                    try {
                        goodsList = goodsDao.getGoodsInFirstDirectory(Integer.parseInt(firstId));
                        Firstdirectory firstdirectory = goodsDao.getFirstDirectoryById(Integer.parseInt(firstId));
                        request.setAttribute("firstDirectory",firstdirectory);
                    }catch (Exception e){
                        return "input";
                    }
                }else{
                    // 否则默认返回所有商品
                    goodsList = goodsDao.getAllGoods();
                }
            }
        }
        request.setAttribute("goodsList",goodsList);


        return SUCCESS;
    }



    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }


    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
