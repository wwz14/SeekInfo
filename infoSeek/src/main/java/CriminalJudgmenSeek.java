import model.CriminalInfo;
import model.Evidence;
import model.TraumaticInfo;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alice on 18/4/12.
 */
public class CriminalJudgmenSeek {

    private Element root;

    /**
     * 读取xml，获得root节点
     * @param filePath
     */
    public CriminalJudgmenSeek(String filePath) {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            document = saxBuilder.build(filePath);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.root = document.getRootElement();
    }

    /**
     * 提取被告人信息
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public CriminalInfo criminalInfoSeek()  {
        CriminalInfo criminalInfo = new CriminalInfo();

        try {
            Element criminal = root.getChild("QW").getChild("DSR").getChild("YSF");
            //获取被告基本情况概述
            String summary = criminal.getAttributeValue("value");
            String[] arr = summary.split("。");
            criminalInfo.setBackground(arr[0]);
            //获取被告人类型
            Element type = criminal.getChild("DSRLX");
            criminalInfo.setType(type.getAttributeValue("value"));
            //获取被告人国籍信息
            try {
                Element nation = criminal.getChild("GJ");
                criminalInfo.setNation(nation.getAttributeValue("value"));
                //是否具有刑事承担能力
            }catch (NullPointerException e){
                criminalInfo.setNation("中国");
            }
            Element isRes = criminal.getChild("XSZRNL");
            criminalInfo.setIsRes(isRes.getAttributeValue("value"));
        }catch (NullPointerException e) {
            criminalInfo.setType("自然人");
            criminalInfo.setNation("中国");
            criminalInfo.setIsRes("完全刑事责任能力");
        }
        //isjuvenile 获取被害人是否是少年犯罪
        Element recode = root.getChild("QW").getChild("SSJL").getChild("SNFT");
        if(null != recode){
            criminalInfo.setIsjuvenile(recode.getAttributeValue("value"));
        }else{
            criminalInfo.setIsjuvenile(" 否");
        }
        criminalInfo.printInfo();
        return criminalInfo;
    }

    /**
     * 获取证据清单
     * @return
     */
    public ArrayList<Evidence> evidenceSeek() {
        ArrayList<Evidence> evidenceList = new ArrayList<Evidence>();
      List<Element> evidenceGroup = new ArrayList<Element>();
      try {
          evidenceGroup =
                  root.getChild("QW").getChild("AJJBQK").getChild("BSSLD").getChild("ZJXX").getChild("ZJFZ").getChildren("ZJJL");
      }catch(NullPointerException e){
          return evidenceList;
      }

        if(!evidenceGroup.isEmpty()) {
            for (Element e : evidenceGroup) {
                String des = e.getAttributeValue("value");
                System.out.println("ecidence is:" + des);
                Element type = e.getChild("ZJMX").getChild("ZL");
                String evidenceType = type.getAttributeValue("value");
                System.out.println("ecidence  type is:" + evidenceType);
                evidenceList.add(new Evidence(des, evidenceType));
            }
        }else {
            //todo 直接获取认证事实段落
        }
        return evidenceList;
    }

    public TraumaticInfo traumaticInfoSeek() {
        TraumaticInfo traumaticInfo = new TraumaticInfo();
        //判定被害人是否死亡
        List<Element> victims = new ArrayList<Element>();
        try {
            victims = root.getChild("QW").getChild("AJJBQK").getChildren("BHR");
        }catch (NullPointerException e) {
            return traumaticInfo;
        }
        String isDeadCondition = "";
        if(!victims.isEmpty()) {
            for (Element e : victims) {
                String victimName = e.getChild("BHRXM").getAttributeValue("value");
                String isDead = e.getChild("SFSW").getAttributeValue("value");
                isDeadCondition += victimName + ":" + isDead + "\n";

            }
        }else{
            Element general = root.getChild("QW");
            String des = general.getAttributeValue("value");
            if(des.contains("死亡")){
                isDeadCondition = "是";
            }else {
                isDeadCondition="否";
            }
        }
        traumaticInfo.setIdDead(isDeadCondition);

        //被害人伤情鉴定级别
        Element article = root.getChild("QW");
        String summary = article.getAttributeValue("value");
        String hurtLevel = "";

        if(summary.contains("轻伤")){
            hurtLevel+="轻伤\n";
        }

        if(summary.contains("重伤")) {
            hurtLevel+="重伤\n";
        }

        if(summary.contains("轻微伤")){
            hurtLevel+="轻微伤\n";
        }

        if(hurtLevel.length() == 0) {
            if(isDeadCondition.equals("否")){
                hurtLevel = "无鉴定结果";
            }else {
                hurtLevel = "致死";
            }
        }
        traumaticInfo.setEvaluation(hurtLevel);
        System.out.println("hurt level:"+hurtLevel);
        System.out.println("if dead:" +isDeadCondition);
        return traumaticInfo;
    }

    /**
     * 被告是否有可以酌定量刑情节
     * @return
     */
    public String isOffset() {
        String offsetAction = "";
        Element article = root.getChild("QW");
        String QW = article.getAttributeValue("value");
        List<Element> statution = new ArrayList<Element>();
        try {
            statution = root.getChild("QW").getChild("CPFXGC").getChild("LXQJ").getChildren("ZDLXQJ");
        }catch(NullPointerException e) {
            return "无 ";
        }

        String offSet = "";
        if(!statution.isEmpty()) {
             for(Element e : statution) {
                String XGR = "";
                String QJ = "";
                String type = "";

                 if(null!= e.getChild("XGR")) {
                     XGR = e.getChild("XGR").getAttributeValue("value");
                 }

                 if(null!= e.getChild("QJ")){
                     QJ = e.getChild("QJ").getAttributeValue("value");
                 }

                 if(null!=e.getChild("LXQJLB")) {
                     type = e.getChild("LXQJLB").getAttributeValue("value");
                 }

                offSet += XGR+":"+QJ+"("+type+")\n";
             }
        }else if(QW.contains("认罪态度较好")||QW.contains("认罪态度好")){
            offsetAction += "认罪态度好";
        }else {
            offSet += "无";
        }
        System.out.println(" 酌定从轻量刑"+offSet);
        return offSet;
    }

    /**
     * 被告是否有可以法定法定量刑情节
     * @return
     */
    public String offSetByLaw() {

        String offSetByLaw = "无";
        List<Element> offsetByLawList = new ArrayList<Element>();
        try{
            offsetByLawList = root.getChild("QW").getChild("CPFXGC").getChild("LXQJ").getChildren("FDLXQJ");
        }catch(NullPointerException e) {
            return "无";
        }
        if(!offsetByLawList.isEmpty()) {
            String name = "";
            String QJ = "";
            for(Element e:offsetByLawList) {
                if(null != e.getChild("XGR")) {
                    name = e.getChild("XGR").getAttributeValue("value");
                }

                if(null != e.getChild("QJ")) {
                    QJ = e.getChild("QJ").getAttributeValue("value");
                }
            }
        }else {
            offSetByLaw = "无";
        }
        System.out.println("法定从轻量刑;："+offSetByLaw);
        return offSetByLaw;
    }



}
