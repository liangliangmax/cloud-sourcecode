package com.liang.service_b.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "comment_info")
public class CommentInfo implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "comment_id")
    private String commentId;

    @Column(name = "commentInfo")
    private String commentInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }
}
