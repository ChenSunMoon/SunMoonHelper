package com.sunmoon.helper.model;




public class Remind {

    private long id;
    private String content;
    private String time;
    private int status;
    private String completeTime;

    public Remind(long id, String content, String time, int status,
            String completeTime) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.status = status;
        this.completeTime = completeTime;
    }


    public Remind() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setStatus(boolean status) {
        if (status) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }
    public String getCompleteTime() {
        return this.completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
}
