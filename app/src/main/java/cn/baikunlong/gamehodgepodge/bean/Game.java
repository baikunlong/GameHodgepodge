package cn.baikunlong.gamehodgepodge.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Android Studio.
 * User: baikunlong
 * Date: 2019/6/6
 * Time: 11:38
 */
@Entity
public class Game {
    @Id(autoincrement = true)
    Long id;

    @Unique
    String gameName;
    String imgUrl="https://img.tapimg.com/market/icons/be9e8df96759ab84bcd169c3fd142641_360.png?imageMogr2/auto-orient/strip";
    String grade;
    String tag;
    String category;
    @Generated(hash = 1380014877)
    public Game(Long id, String gameName, String imgUrl, String grade, String tag, String category) {
        this.id = id;
        this.gameName = gameName;
        this.imgUrl = imgUrl;
        this.grade = grade;
        this.tag = tag;
        this.category = category;
    }
    @Generated(hash = 380959371)
    public Game() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGameName() {
        return this.gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Game(String gameName, String imgUrl, String grade, String tag, String category) {
        this.gameName = gameName;
        this.imgUrl = imgUrl;
        this.grade = grade;
        this.tag = tag;
        this.category = category;
    }
}
