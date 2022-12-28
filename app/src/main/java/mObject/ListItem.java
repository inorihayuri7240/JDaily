package mObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListItem  implements Serializable {
    private Date date;
    private String auctor_id;
    private String content;
    private boolean done;

    public ListItem(Date date, String auctor_id, String content/*, boolean done*/){
        this.date=date;
        this.auctor_id=auctor_id;
        this.content=content;
        this.done=false;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuctor_id() {
        return auctor_id;
    }

    public void setAuctor_id(String auctor_id) {
        this.auctor_id = auctor_id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    //差距(當天)天數
    public long getLeftDay(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //顯示的時間格式
        Calendar cal = Calendar.getInstance();
        Date today=Calendar.getInstance().getTime();
        long diff = date.getTime() - today.getTime(); //差值
        long leftDay = date.getTime()<=today.getTime() ? diff / (1000 * 60 * 60 * 24) : diff / (1000 * 60 * 60 * 24)+1; //得到差的天數
        return leftDay;
    }
}
