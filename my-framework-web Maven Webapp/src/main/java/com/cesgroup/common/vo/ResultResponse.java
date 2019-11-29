package com.cesgroup.common.vo;

import java.util.List;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居集团股份有限公司
 * </p>
 *
 * @author 管俊(guan.jun@chinaredstar.com)
 * @version 1.0
 * @date 2019/11/29 18:54
 */
public class ResultResponse<T> {
    
    private T data;

    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageNumber() {
        if(pageNumber == null ){
            pageNumber = 1;
        }
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        if(pageSize == null){
            pageSize = 20;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static <T> ResultResponse<T> success(T data) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setData(data);
        if(data instanceof List){
            List list = (List) data;
            resultResponse.setTotal(list.size());
        }
        return resultResponse;
    }
}
