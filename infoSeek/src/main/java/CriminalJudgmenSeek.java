import model.CriminalInfo;
import model.Evidence;
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
        Element criminal = root.getChild("QW").getChild("DSR").getChild("YSF");
        if(null != criminal) {
            //获取被告基本情况概述
            String summary = criminal.getAttributeValue("value");
            String[] arr = summary.split("。");
            criminalInfo.setBackground(arr[0]);
            //获取被告人类型
            Element type = criminal.getChild("DSRLX");
            criminalInfo.setType(type.getAttributeValue("value"));
            //获取被告人国籍信息
            Element nation = criminal.getChild("GJ");
            criminalInfo.setNation(nation.getAttributeValue("value"));
            //是否具有刑事承担能力
            Element isRes = criminal.getChild("XSZRNL");
            criminalInfo.setIsRes(isRes.getAttributeValue("value"));
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
        List<Element> evidenceGroup =
                root.getChild("QW").getChild("AJJBQK").getChild("BSSLD").getChild("ZJXX").getChild("ZJFZ").getChildren("ZJJL");
        for(Element e:evidenceGroup){
            String des = e.getAttributeValue("value");
            Element type = e.getChild("ZJMX").getChild("ZL");
            String evidenceType = type.getAttributeValue("value");
            evidenceList.add(new Evidence(des,evidenceType));
        }

        return new ArrayList<Evidence>();
    }

}
