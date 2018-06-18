package action;

import bean.Goodinfo;
import com.opensymphony.xwork2.ActionSupport;
import dao.GoodsDao;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GoodInfoAction extends ActionSupport implements ServletRequestAware {
    @Autowired
    private GoodsDao goodsDao;

    private HttpServletRequest request;


    public String showGoodInfo(){
        // 获取要显示的商品的id
        String goodId = request.getParameter("goodId");
        if(goodId == null){
            return "input";
        }
        // 商品信息
        Goodinfo goodinfo = null;

        // 之后以异常判断来作为判断条件
        try{
            goodinfo = goodsDao.getGoodInfoByGoodId(Integer.parseInt(goodId));
        }catch (Exception e){
            // e.printStackTrace();
            return "input";
        }
        request.setAttribute("goodInfo",goodinfo);

        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }


    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }
}
