package model;

import java.util.ArrayList;

/**
 * Created by alice on 18/4/15.
 */
public class InfoSet {
    /**
     *  事实信息模型
     */
    private FactModel factModel;
    /**
     * 证据信息
     */
    private ArrayList<Evidence> evidences;
    /**
     * 被害人受伤信息
     */
    private TraumaticInfo traumaticInfo;
    /**
     * 被告信息
     */
    private CriminalInfo criminalInfo;
    /**
     * 是否附带民事诉讼
     */
    private String hasCivilAction;
    /**
     * 民事诉讼内容
     */
    private String CivilActionContent;
    /**
     * 酌定量刑
     */
    private String offSet;
    /**
     * 法定量刑
     */
    private String offSetByLaw;

    private String OrginalName;

    private JudgeResult judgeResult;

    public FactModel getFactModel() {
        return factModel;
    }

    public void setFactModel(FactModel factModel) {
        this.factModel = factModel;
    }

    public ArrayList<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(ArrayList<Evidence> evidences) {
        this.evidences = evidences;
    }

    public TraumaticInfo getTraumaticInfo() {
        return traumaticInfo;
    }

    public void setTraumaticInfo(TraumaticInfo traumaticInfo) {
        this.traumaticInfo = traumaticInfo;
    }

    public CriminalInfo getCriminalInfo() {
        return criminalInfo;
    }

    public void setCriminalInfo(CriminalInfo criminalInfo) {
        this.criminalInfo = criminalInfo;
    }

    public String getHasCivilAction() {
        return hasCivilAction;
    }

    public void setHasCivilAction(String hasCivilAction) {
        this.hasCivilAction = hasCivilAction;
    }

    public String getCivilActionContent() {
        return CivilActionContent;
    }

    public void setCivilActionContent(String civilActionContent) {
        CivilActionContent = civilActionContent;
    }

    public String getOffSet() {
        return offSet;
    }

    public void setOffSet(String offSet) {
        this.offSet = offSet;
    }

    public String getOffSetByLaw() {
        return offSetByLaw;
    }

    public void setOffSetByLaw(String offSetByLaw) {
        this.offSetByLaw = offSetByLaw;
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(JudgeResult judgeResult) {
        this.judgeResult = judgeResult;
    }

    public String getOrginalName() {
        return OrginalName;
    }

    public void setOrginalName(String orginalName) {
        OrginalName = orginalName;
    }
}
