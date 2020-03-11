package com.dream.common.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUiDataGridResult implements Serializable {
    private Long total;
    private List<?> rows;

    public EasyUiDataGridResult() {
    }

    public EasyUiDataGridResult(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
