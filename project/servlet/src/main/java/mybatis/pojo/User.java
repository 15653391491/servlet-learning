package mybatis.pojo;

import java.util.HashMap;

public class User {
    protected int id;
    protected String account;
    protected String password1;
    protected String password2;
    protected String platform;
    protected String createTime;
    protected String updateTime;

    public User() {
    }

    public User(int id, String account, String password1, String password2, String platform, String createTime, String updateTime) {
        this.id = id;
        this.account = account;
        this.password1 = password1;
        this.password2 = password2;
        this.platform = platform;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", platform='" + platform + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getPlatform() {
        return platform;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }
}

