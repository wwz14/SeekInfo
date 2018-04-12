package model;

/**
 * Created by alice on 18/4/12.
 */
public class Evidence {
    /**
     * 证据具体描述
     */
    private String description;
    /**
     * 证据类型
     */
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Evidence() {
    }

    public Evidence(String description, String type) {
        this.description = description;
        this.type = type;
    }
}
