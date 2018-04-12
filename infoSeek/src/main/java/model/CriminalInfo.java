package model;

/**
 * Created by alice on 18/4/12.
 */
public class CriminalInfo {
    /**
     * 被告信息综述
     */
    private String background;
    /**
     * 被告人类型，自然人，法人
     */
    private String type;
    /**
     * 被告国籍
     */
    private String nation;
    /**
     * 是否属于少年犯罪
     */
    private String isjuvenile;
    /**
     * 是否具有刑事承担能力
     */
    private String isRes;

    public CriminalInfo() {
    }

    public CriminalInfo(String background, String nation, String isjuvenile, String isRes) {
        this.background = background;
        this.nation = nation;
        this.isjuvenile = isjuvenile;
        this.isRes = isRes;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIsjuvenile() {
        return isjuvenile;
    }

    public void setIsjuvenile(String isjuvenile) {
        this.isjuvenile = isjuvenile;
    }

    public String getIsRes() {
        return isRes;
    }

    public void setIsRes(String isRes) {
        this.isRes = isRes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printInfo() {
        System.out.println("background is:"+background);
        System.out.println("type is:"+type);
        System.out.println("nation is:"+nation);
        System.out.println("is teenage:"+isjuvenile);
        System.out.println("is res:" +isRes);
    }
}
