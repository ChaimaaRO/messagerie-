package com.example.whatsappchatappclone.model;

public class ChatMessage {

    private long id ;
    private String date;
    private String content;
    private boolean fromMe;
    private boolean showTime = true;

    public ChatMessage(long id, String date, String content, boolean fromMe, boolean showTime) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.fromMe = fromMe;
        this.showTime = showTime;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public boolean isFromMe() {
        return fromMe;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFromMe(boolean fromMe) {
        this.fromMe = fromMe;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }
}
