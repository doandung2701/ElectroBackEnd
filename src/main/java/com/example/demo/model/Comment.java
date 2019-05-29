package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Table(name = "comments")
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cmt_id")
    private Long cmtId;

    @Column(name="cmt_cnt")
    private String cmtCnt;

    @Column(name = "cmt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmtDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "parent",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Comment> replies;

    @Column(name ="edit_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editAt;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Comment parent;

    public Comment(String cmtCnt, Date cmtDate, User user, Product product, Set<Comment> replies, Comment parent) {
        this.cmtCnt = cmtCnt;
        this.cmtDate = cmtDate;
        this.user = user;
        this.product = product;
        this.replies = replies;
        this.parent = parent;
    }

    public Comment() {
    }

    public Date getEditAt() {
        return editAt;
    }

    public void setEditAt(Date editAt) {
        this.editAt = editAt;
    }

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

    public Date getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(Date cmtDate) {
        this.cmtDate = cmtDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Comment> getReplies() {
        return replies;
    }

    public void setReplies(Set<Comment> replies) {
        this.replies = replies;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
