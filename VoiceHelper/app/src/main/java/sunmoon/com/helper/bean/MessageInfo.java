package sunmoon.com.helper.bean;

/**
 * Created by SunMoon on 2016/11/4.
 */

public class MessageInfo  {
    private String title;
    private String recentContent;
    private String time;
    public MessageInfo(String title, String recentContent) {
        this.title = title;
        this.recentContent = recentContent;
    }

    public MessageInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecentContent() {
        return recentContent;
    }

    public void setRecentContent(String recentContent) {
        this.recentContent = recentContent;
    }

    public MessageInfo(String title, String recentContent, String time) {
        this.title = title;
        this.time = time;
        this.recentContent = recentContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
