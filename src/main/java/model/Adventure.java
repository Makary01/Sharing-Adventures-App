package model;

import java.time.LocalDate;

public class Adventure {

    private Integer id;
    private Integer userId;
    private String type;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    public Adventure() {
    }

    //constructor for Dao class
    public Adventure(Integer id, Integer user_id, String type, String title, String content, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = user_id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
