package mObject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "Member")
public class Member {
//    @Ignore
//    public Member() { }
//
//    public Member(@NonNull String loginId, String pwd, String nickName, String gender, Date birthday, String email) {
//        this.loginId = loginId;
//        this.pwd = pwd;
//        this.nickName = nickName;
//        this.gender = gender;
//        this.birthday = birthday;
//        this.email = email;
//    }

    @PrimaryKey //(autoGenerate = false)主鍵是否自動增長，預設為false
    @NonNull
    @ColumnInfo(name = "loginId")
    private String loginId;
    @ColumnInfo(name = "pwd")
    private String pwd;
    @ColumnInfo(name = "nickName")
    private String nickName;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "birthday")
    private Date birthday;
    @ColumnInfo(name = "email")
    private String email;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
