package com.sunmoon.helper.model;
import android.os.Parcel;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by SunMoon on 2016/12/31.
 */
@Entity
public class NoteInfo  {
    @Id(autoincrement = true)
    private long id;
    private String label;
    private String content;
    private String createTime;
    @Keep()
    public NoteInfo(long id, String label, String content, String createTime) {
        this.id = id;
        this.label = label;
        this.content = content;
        this.createTime = createTime;
    }
    public NoteInfo() {
    }

    protected NoteInfo(Parcel in) {
        id = in.readLong();
        label = in.readString();
        content = in.readString();
        createTime = in.readString();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
