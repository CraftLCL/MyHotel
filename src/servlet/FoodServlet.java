package servlet;

import entity.Food;
import utils.WebUtils;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * ²ËÆ·µÄservlet
 */
@WebServlet(name = "FoodServlet",urlPatterns = "/food")
public class FoodServlet extends BaseServlet {
    public Object foodSave(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        Food food= WebUtils.copyTOBean(request,Food.class);
        foodService.save(food);
        uri=request.getRequestDispatcher("/food?method=list");
        return uri;
    }
    public Object list(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Object uri=null;
        List<Food> foodList=foodService.findAll();
        request.setAttribute("foodList",foodList);
        uri=request.getRequestDispatcher(request.getContextPath()+"/sys/foodList.jsp");

        return uri;
    }
}
