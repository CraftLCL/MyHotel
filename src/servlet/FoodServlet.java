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
 * ��Ʒ��servlet
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
     * �����ϴ��ļ���װ��food
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
                        //��ȡԪ������
                        String fieldName = item.getFieldName();
                        //��ȡֵ
                        String value = item.getString("UTF-8");
                        if (!fieldName .equals("oldImg") ) {
                            BeanUtils.copyProperty(food, fieldName, value);
                        }else {
                            oldImg=value;
                        }

                    } else {
                        //�����ļ��ϴ�


                        //��ȡ�ļ���
                        String name = item.getName();


                        String id = UUID.randomUUID().toString();

                        String newName = id + name;

                        //��ȡ�ϴ���·��
                        String basePath = getServletContext().getRealPath("/upload");

                        //�����ļ�����
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
            System.out.println("�����ϴ���");

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
     * ��̬���ز�Ʒ����
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
        //���ò�Ʒ����
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
            //���������Ϣ��session
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
        //���ò�Ʒ����
        condition.setFoodName(foodName);

        //����������
        pageBean.setCondition(condition);

        foodService.getAll(pageBean);
        request.setAttribute("pb",pageBean);

        List<FoodType> foodTypeList=foodTypeService.getAll();
        request.setAttribute("listFoodType",foodTypeList);

        uri=request.getRequestDispatcher("/app/caidan.jsp");


        return uri;
    }


}
