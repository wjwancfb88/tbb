package tb_inspection.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页公共类
 *
 * @param <E>
 */
public class PageData<E> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 总个数
     */
    private Integer count;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 查询结果
     */
    private List<E> result;


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }
}

