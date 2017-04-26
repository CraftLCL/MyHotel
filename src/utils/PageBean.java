package utils;

import java.util.List;

/**
 * Created by lcl on 2017/4/17.
 * 封装分页参数
 */
public class PageBean<T> {
    private int currentPage;

    private int pageCount=6;

    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;

    private List<T> pageData;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        if(totalCount%pageCount==0){
            totalPage=totalCount/pageCount;
        }else {
            totalPage=totalCount/pageCount+1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Condition getCondition() {

        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    private  Condition condition;

}
