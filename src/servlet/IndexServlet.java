package servlet;

import entity.DinnerTable;
import entity.Food;
import entity.FoodType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lcl on 2017/4/17.
 * 客户端的首页的服务
 */
@WebServlet(name = "IndexServlet",urlPatterns = "/indexServlet")
public class IndexServlet extends BaseServlet {
    public Object listTable(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        Object uri=null;
        List<DinnerTable> list = dinnerTableService.findByNoUse();
        request.setAttribute("listDinnerTable",list);

        uri=request.getRequestDispatcher("/app/index.jsp");

        return uri;

    }
    public Object getFoodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object uri =null;
        String id = request.getParameter("food");//获取食物id
        Food food = foodService.findById(Integer.parseInt(id));
        List<FoodType> foodtypes = foodTypeService.getAll();
        request.setAttribute("food", food);
        request.setAttribute("foodtypes", foodtypes);
        uri = request.getRequestDispatcher("/app/caixiangxi.jsp");

        return uri;


    }

}
