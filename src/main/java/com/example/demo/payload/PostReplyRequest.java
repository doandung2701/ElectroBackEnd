package com.example.demo.payload;

import java.io.Serializable;
import java.util.Date;

public class PostReplyRequest implements Serializable {

    private Long parentId;

    private String productId;

    private String cmtCnt;

    private Date repDate;

    public PostReplyRequest(Long parentId, String productId, String cmtCnt, Date repDate) {
        this.parentId = parentId;
        this.productId = productId;
        this.cmtCnt = cmtCnt;
        this.repDate = repDate;
    }

    public PostReplyRequest() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCmtCnt() {
        return cmtCnt;
    }

    public void setCmtCnt(String cmtCnt) {
        this.cmtCnt = cmtCnt;
    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }
}
