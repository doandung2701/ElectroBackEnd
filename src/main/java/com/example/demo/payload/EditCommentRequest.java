package com.example.demo.payload;

import java.io.Serializable;

public class EditCommentRequest implements Serializable {

    private Long cmtId;

    private String cmtCnt;

    public Long getCmtId() {
        return cmtId;
    }

    public void setCmtId(Long cmtId) {
        this.cmtId = cmtId;
    }

    public String getCmtCnt() {
        return cmtCnt;
    }

    public void setCmtCnt(String cmtCnt) {
        this.cmtCnt = cmtCnt;
    }
}
