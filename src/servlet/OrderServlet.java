package servlet;

import entity.DinnerTable;
import entity.Food;
import entity.OrderDetail;
import entity.Orders;
import utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by lcl on 2017/4/19.
 */
@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    @Override
    public void init() throws ServletException {
        List<Orders> orders = ordersService.query();
        List<OrderDetail> orderDetail = orderDetailService.query();
        this.getServletContext().setAttribute("orders", orders);
        this.getServletContext().setAttribute("orderDetail", orderDetail);
    }
    public Object putInCar(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object uri = null;
        Map<Food, Integer> map = new LinkedHashMap<Food, Integer>();

        // 获取食物id
        String id = request.getParameter("food_id");
        Food food = foodService.findById(Integer.parseInt(id));

        //用于存储订单数据
        Map<Food, Integer> m = (Map<Food, Integer>) session
                .getAttribute("foods");

        if (m != null) {
            if (m.containsKey(food)) {
                Integer count = m.get(food);
                count++;
                m.put(food, count);
            } else {
                m.put(food, 1);
            }
        } else {
            map.put(food, 1);
        }

        if (m != null) {
            session.setAttribute("foods", m);
        } else {
            session.setAttribute("foods", map);
        }
        uri = "/app/clientCart.jsp";

        return uri;
    }

    //删除订单
    public Object removeOrder(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        String id = request.getParameter("gid");
        Food food = foodService.findById(Integer.parseInt(id));
        HttpSession session = request.getSession();

        //获取加入餐车时的食物数据
        Map<Food, Integer> m = (Map<Food, Integer>) session
                .getAttribute("foods");

        m.remove(food);

        session.setAttribute("foods", m);
        uri = "/app/clientCart.jsp";
        return uri;
    }

    public Object alterSorder(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        String id = request.getParameter("gid");
        Food food = foodService.findById(Integer.parseInt(id));

        // 获取修改商品数量的数量
        String num = request.getParameter("snumber");
        HttpSession session = request.getSession();
        Map<Food, Integer> m = (Map<Food, Integer>) session
                .getAttribute("foods");
        m.put(food, Integer.parseInt(num));
        session.setAttribute("foods", m);

        uri = "/app/clientCart.jsp";
        return uri;
    }

    //下单的方法
    public Object takeOrder(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<Food, Integer> m = (Map<Food, Integer>) session
                .getAttribute("foods");
        String table_id = (String) session.getAttribute("table_id");

        // 新建订单对象
        Orders order = new Orders();
        order.setTable_id(Integer.parseInt(table_id));

        Set<Map.Entry<Food, Integer>> entrySet = m.entrySet();
        // 创建订单详细对象
        OrderDetail detail = new OrderDetail();

        // 定义总价钱
        int sum = 0;
        int orderId = ordersService.getCount() + 1;

        //获取总价格
        for (Map.Entry<Food, Integer> entry : entrySet) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            order.setId(food.getId());
            sum += food.getPrice() * count;
            order.setOrderDate(new Date());
        }

        order.setTotalPrice(sum);
        ordersService.add(order);

        for (Map.Entry<Food, Integer> entry : entrySet) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            detail.setFood_id(food.getId());
            detail.setOrderId(orderId);
            detail.setFoodCount(count);
            orderDetailService.add(detail);
        }

        findOrder(request, response);

        uri = "/app/clientOrderList.jsp";
        return uri;
    }

    public void findOrder(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        List<Orders> orders = ordersService.query();
        List<OrderDetail> orderDetail = orderDetailService.query();
        this.getServletContext().setAttribute("orders", orders);
        this.getServletContext().setAttribute("orderDetail", orderDetail);
    }

    public Object getOrderDetail(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        String id = request.getParameter("orderId");
        List<OrderDetail> list = null;
        if (id != null && !id.isEmpty()) {
            list = orderDetailService.findByOrderId(Integer.parseInt(id));
        }

        request.setAttribute("orderDetail", list);

        uri = request.getRequestDispatcher("/sys/orderDetail.jsp");
        return uri;
    }

    // 后台结账的方法
    public Object pay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;
        String oid = request.getParameter("orderId");
        Orders o = new Orders();
        o.setOrderStatus(1);
        o.setId(Integer.parseInt(oid));

        ordersService.update(o);

        String tid = request.getParameter("tableId");
        if (tid != null) {
            dinnerTableService.quitTable(Integer.parseInt(tid));
        }
        findOrder(request, response);

        DinnerTable table = dinnerTableService.findById(Integer.parseInt(tid));// 用于后台点击结账时消除通知
        @SuppressWarnings("unchecked")
        List<String> list = (List<String>) this.getServletContext()
                .getAttribute("tn");
        if (list != null) {
            list.remove(table.getTableName());
        }
        getOrderList(request, response);
        // 5. 跳转
        uri = request.getRequestDispatcher("sys/orderList.jsp");

        return uri;
    }

    // 前台呼叫结账的方法
    public Object call(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("table_id");
        DinnerTable table = dinnerTableService.findById(Integer.parseInt(id));

        String tableName = table.getTableName();

        @SuppressWarnings("unchecked")
        List<String> tab = (List<String>) this.getServletContext()
                .getAttribute("tn");
        if (tab == null) {
            tab = new ArrayList<String>();
        }
        tab.add(tableName);

        this.getServletContext().setAttribute("tn", tab);

        List<DinnerTable> tables = dinnerTableService.getAll();// 更新前台首页的桌子
        this.getServletContext().setAttribute("table", tables);

        uri = "/app/index.jsp";

        return uri;
    }

    public Object getOrderList(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        // 1. 获取“当前页”参数； (第一次访问当前页为null)
        String currPage = request.getParameter("currentPage");
        // 判断
        if (currPage == null || "".equals(currPage.trim())) {
            currPage = "1"; // 第一次访问，设置当前页为1;
        }
        // 转换
        int currentPage = Integer.parseInt(currPage);

        // 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
        PageBean<Orders> pageBean = new PageBean<Orders>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageCount(6);

        // 3. 调用service
        ordersService.getAll(pageBean); // 【pageBean已经被dao填充了数据】
        // 4. 保存pageBean对象，到request域中
        request.setAttribute("pageBean", pageBean);

        // 5. 跳转
        uri = request.getRequestDispatcher("sys/orderList.jsp");

        return uri;

    }


}
