package model;

import java.util.*;

public class Adventure {

    private Integer id;
    private Integer user_id;
    private String type;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;

    public Adventure() {
    }

    //constructor for Dao class
    public Adventure(Integer id, Integer user_id, String type, String title, String content, Date startDate, Date endDate) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
