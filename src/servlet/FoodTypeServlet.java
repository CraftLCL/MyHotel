package servlet;

import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;
import service.impl.FoodTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 * 菜系管理servlet开发
 */
@WebServlet(name = "FoodTypeServlet",urlPatterns = "/foodType")
public class FoodTypeServlet extends HttpServlet {
    //调用菜系服务
 //private IFoodTypeService foodTypeService=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
    IFoodTypeService foodTypeService=new FoodTypeService();
    //跳转资源
    private String uri;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);

        //获取操作类型
        String method=request.getParameter("method");
        if("addFoodType".equals(method)){
            //添加
            addFoodType(request,response);
        }else if ("list".equals(method)){
            list(request,response);
        }else if("viewUpdate".equals(method)){
            //进入更新页面
            viewUpdate(request,response);

        }else if("delete".equals(method)){
            delete(request,response);
        }else if("update".equals(method)){
            update(request,response);
        }else if("findByName".equals(method)){
            findByName(request,response);
        }


    }
    public void addFoodType(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //1.封装请求的数据
        try {
            String foodTypeName=request.getParameter("foodTypeName");
            //2.调用service处理业务逻辑
            FoodType foodType=new FoodType();
            foodType.setTypeName(foodTypeName);
            foodTypeService.save(foodType);

            uri="/foodType?method=list";
        } catch (Exception e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);
    }
    //菜系列展示
    public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //调用service查询所有类别
        try {
            List<FoodType> foodTypeList=foodTypeService.getAll();
            request.setAttribute("listFoodType",foodTypeList);
            uri="/sys/type/cuisineList.jsp";
        } catch (Exception e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);

    }
    protected void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求id
        try {
            String id=request.getParameter("id");
            //2.更据id查询对象
            FoodType foodType=foodTypeService.findById(Integer.parseInt(id));

            request.setAttribute("foodType",foodType);
            uri="/sys/type/updateCuisine.jsp";
        } catch (NumberFormatException e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);

    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id=request.getParameter("id");
            foodTypeService.delete(Integer.parseInt(id));

            uri="/foodType?method=list";
        } catch (NumberFormatException e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);

    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id= request.getParameter("id");
            String name=request.getParameter("foodTypeName");
            FoodType foodType=new FoodType();
            foodType.setId(Integer.parseInt(id));
            foodType.setTypeName(name);
            foodTypeService.update(foodType);
            uri="/foodType?method=list";
        } catch (NumberFormatException e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);


    }
    protected void findByName (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            String typeName= request.getParameter("typeName");
            if ("".equals(typeName)){
                uri="/foodType?method=list";
            }
            else {
                List<FoodType> foodTypeList= foodTypeService.getAll(typeName);
                request.setAttribute("listFoodType",foodTypeList);
                uri="/sys/type/cuisineList.jsp";
            }

        } catch (Exception e) {
            uri="/error/error.jsp";
        }
        request.getRequestDispatcher(uri).forward(request,response);

    }


}
