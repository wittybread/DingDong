package wangxuewei.bwie.com.demo03;

/**
 * Created by jim on 2017/12/27.
 */

public class UserInfo {
    private int img;
    private String name;
    private String job;

    public UserInfo(int img, String name, String job) {
        super();
        this.img = img;
        this.name = name;
        this.job = job;
    }

    public UserInfo() {
        super();
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "UserInfo [img=" + img + ", name=" + name + ", job=" + job + "]";
    }
}
