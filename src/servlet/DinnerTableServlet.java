package servlet;

import entity.DinnerTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lcl on 2017/4/14.
 * ²Í×Àservlet
 */
@WebServlet(name = "DinnerTableServlet",urlPatterns = "/dinnerTable")
public class DinnerTableServlet extends BaseServlet {
    public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        List<DinnerTable> dinnerTableList = dinnerTableService.getAll();
        request.setAttribute("dinnerTableList",dinnerTableList);
        uri=request.getRequestDispatcher(request.getContextPath()+"/sys/boardList.jsp");
        return uri;
    }
    public Object addDinnerTable(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        DinnerTable dinnerTable=new DinnerTable();
        dinnerTable.setTableName(request.getParameter("tableName"));
        dinnerTableService.save(dinnerTable);
        uri="/dinnerTable?method=list";
        return uri;
    }
    public Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        DinnerTable dinnerTable=dinnerTableService.findById(Integer.parseInt(request.getParameter("id")));
        dinnerTableService.update(dinnerTable);
        uri="/dinnerTable?method=list";
        return uri;

    }
    public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        dinnerTableService.delete(Integer.parseInt(request.getParameter("id")));
        uri="/dinnerTable?method=list";
        return uri;
    }
    public Object findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Object uri=null;
        List<DinnerTable> dinnerTableList = dinnerTableService.getAll(request.getParameter("tableName"));
        request.setAttribute("dinnerTableList",dinnerTableList);
        uri=request.getRequestDispatcher(request.getContextPath()+"/sys/boardList.jsp");
        return uri;
    }
}
