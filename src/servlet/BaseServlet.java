package servlet;

import factory.BeanFactory;
import service.*;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lcl on 2017/4/12.
 * Servlet基类  用于产生服务 和 调用get post方法 和对应的服务方法
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected IFoodService foodService= BeanFactory.getInstance("foodService",IFoodService.class);
    protected IFoodTypeService foodTypeService=BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
    protected IDinnerTableService dinnerTableService=BeanFactory.getInstance("dinnerTableService",IDinnerTableService.class);
    protected IOrdersService ordersService = BeanFactory.getInstance("ordersService",
            IOrdersService.class);
    protected IOrderDetailService orderDetailService = BeanFactory.getInstance("orderDetailService",
            IOrderDetailService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri=null;
        Object returnValue=null;
        request.setCharacterEncoding("UTF-8");
        String methodName=request.getParameter("method");

        try {
             Class clazz=this.getClass();
             Method method= clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
             returnValue= method.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
            uri="/error/error.jsp";
        }
        WebUtils.goTo(request,response,returnValue);
    }
}
