package model;

/**
 * Created by alice on 18/4/12.
 * 描述被害人受伤情况
 */
public class TraumaticInfo
{
    /**
     * 描述被害人具体伤情
     */
   // private String description;
    /**
     * 鉴定受伤级别
     */
    private String evaluation;

    /**
     * 是否死亡
     */
    private String idDead;
    /**
     * 死亡是否与被告人打伤有关
     */
   // private String idDeadRelative;

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getIdDead() {
        return idDead;
    }

    public void setIdDead(String idDead) {
        this.idDead = idDead;
    }

//    public String getIdDeadRelative() {
//        return idDeadRelative;
//    }
//
//    public void setIdDeadRelative(String idDeadRelative) {
//        this.idDeadRelative = idDeadRelative;
//    }

}
