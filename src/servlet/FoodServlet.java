package servlet;

import entity.DinnerTable;
import entity.Food;
import entity.FoodType;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.Condition;
import utils.PageBean;
import utils.WebUtils;

import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by lcl on 2017/4/12.
 * 菜品的servlet
 */
@WebServlet(name = "FoodServlet", urlPatterns = "/food",loadOnStartup = 1)
public class FoodServlet extends BaseServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        PageBean<Food> pageBean = new PageBean<Food>();
        pageBean.setPageCount(6);
        foodService.getAll(pageBean);
        List<Food> list = foodService.findAll();
        config.getServletContext().setAttribute("food", list);
        config.getServletContext().setAttribute("pb", pageBean);
    }



    public Object foodSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
/*
        String foodName= request.getParameter("foodName");
        Food food= WebUtils.copyTOBean(request,Food.class);
        foodService.save(food);
*/
        Food food = getFood(request);
        foodService.save(food);
        uri = request.getRequestDispatcher("/food?method=list");
        return uri;
    }

    /**
     * 处理上传文件封装成food
     *
     * @param request
     * @return
     */
    private Food getFood(HttpServletRequest request) {
        Food food = new Food();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(30 * 1024 * 1024);
        upload.setSizeMax(50 * 1024 * 1024);
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                String oldImg="";
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items
                        ) {
                    if (item.isFormField()) {
                        //获取元素名称
                        String fieldName = item.getFieldName();
                        //获取值
                        String value = item.getString("UTF-8");
                        if (!fieldName .equals("oldImg") ) {
                            BeanUtils.copyProperty(food, fieldName, value);
                        }else {
                            oldImg=value;
                        }

                    } else {
                        //处理文件上传


                        //获取文件名
                        String name = item.getName();


                        String id = UUID.randomUUID().toString();

                        String newName = id + name;

                        //获取上传的路径
                        String basePath = getServletContext().getRealPath("/upload");

                        //创建文件对象
                        File file = new File(basePath, newName);
                        //String imgURL= file.getAbsolutePath();
                        String imgURL = request.getContextPath() + "/upload/" + newName;

                        if ("".equals(name) ) {


                            food.setImg(oldImg);
                            item.delete();
                        } else {
                            BeanUtils.copyProperty(food, item.getFieldName(), imgURL);
                            item.write(file);
                            item.delete();
                        }


                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("不是上传表单");

        }
        return food;
    }

    public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        List<Food> foodList = foodService.findAll();
        request.setAttribute("foodList", foodList);
        uri = request.getRequestDispatcher(request.getContextPath() + "/sys/foodList.jsp");

        return uri;
    }

    /**
     * 动态加载菜品种类
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object beforeFoodSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        List<FoodType> foodTypeList = foodTypeService.getAll();
        request.setAttribute("foodTypeList", foodTypeList);
        uri = request.getRequestDispatcher("/sys/saveFood.jsp");
        return uri;
    }

    public Object updateFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Food food = foodService.findById(Integer.parseInt(id));
        request.setAttribute("food", food);
        //设置菜品种类
        List<FoodType> foodTypeList = foodTypeService.getAll();
        request.setAttribute("foodTypeList", foodTypeList);

        Object uri = request.getRequestDispatcher(request.getContextPath() + "/sys/updateFood.jsp");
        return uri;
    }

    public Object updatedFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Food food = getFood(request);
        foodService.update(food);
        Object uri = request.getRequestDispatcher(request.getContextPath() + "/food?method=list");
        return uri;
    }

    public Object deleteFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        foodService.delete(Integer.parseInt(id));
        //Object uri=request.getRequestDispatcher(request.getContextPath()+"/food?method=list");
        Object uri = "/food?method=list";
        return uri;
    }

    public Object findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String foodName = request.getParameter("foodName");
        List<Food> foodList = foodService.findAll(foodName);
        request.setAttribute("foodList", foodList);
        Object uri = request.getRequestDispatcher(request.getContextPath() + "/sys/foodList.jsp");
        return uri;
    }
    public Object foodDetail(HttpServletRequest request,HttpServletResponse response)throws
            ServletException,IOException{
        Object uri=null;
        String tableId=request.getParameter("tableId");

        if(tableId!=null&&!"".equals(tableId)){
            DinnerTable dinnerTable= dinnerTableService.findById(Integer.parseInt(tableId));
            //保存餐桌信息到session
            //int id=dinnerTable.getId();
            request.getSession().setAttribute("table_id",tableId);
            //request.getSession().setAttribute("table_id",dinnerTable.getId());



        }



        PageBean<Food> pageBean=new PageBean<Food>();
        String curPage=request.getParameter("currentPage");
        if(curPage==null||"".equals(curPage.trim())){
            pageBean.setCurrentPage(1);
        }else {
            pageBean.setCurrentPage(Integer.parseInt(curPage));
        }



        Condition condition=new Condition();

        String foodTypeId=request.getParameter("foodTypeId");
        if (foodTypeId!=null&&!"".equals(foodTypeId)){
            condition.setFoodTypeId(Integer.parseInt(foodTypeId));
            request.setAttribute("oldFoodTypeId",foodTypeId);
        }else {
            String oldFoodTypeId=request.getParameter("oldFoodTypeId");
            if(oldFoodTypeId!=null&&!"".equals(oldFoodTypeId)){
                condition.setFoodTypeId(Integer.parseInt(oldFoodTypeId));
            }

        }

        String foodName=request.getParameter("foodName");
        //设置菜品参数
        condition.setFoodName(foodName);

        //设置条件到
        pageBean.setCondition(condition);

        foodService.getAll(pageBean);
        request.setAttribute("pb",pageBean);

        List<FoodType> foodTypeList=foodTypeService.getAll();
        request.setAttribute("listFoodType",foodTypeList);

        uri=request.getRequestDispatcher("/app/caidan.jsp");


        return uri;
    }


}
