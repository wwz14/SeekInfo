/**
 * Created by alice on 18/4/15.
 */
import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdom2.JDOMException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import  java.io.File;
public class MainController {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        try {
            List<InfoSet> infoSets = mainController.start("/Users/alice/Desktop/2012");
            mainController.output("/Users/alice/Desktop/result/2012.xls",infoSets);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<InfoSet> infoSets = mainController.start("/Users/alice/Desktop/2013");
            mainController.output("/Users/alice/Desktop/result/2013.xls",infoSets);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量解析
     * @param dirPath
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public List<InfoSet> start(String dirPath) throws JDOMException, IOException {
        //获取文件路径集合
        List<InfoSet> infoSets = new ArrayList<InfoSet>();
        Reader reader = new Reader();
        List<String> filePathList = new ArrayList<String>();
        try {
             filePathList = reader.reader(dirPath);
        } catch (IOException e) {
            System.out.println("获取文件路径集合异常");
            e.printStackTrace();
        }

        //2. 循环list，对每个文件路径生成提取信息集。一个model对象

        FactSeek factSeek = new FactSeek();

        for(int i = 0;i<filePathList.size();i++) {
            String filenameSet = filePathList.get(i);
            String[] arr = filenameSet.split("/");

            String orginNanme = arr[arr.length-1];
            System.out.println("orginNanme:"+orginNanme);
         //   String[] validname = orginNanme.split(".");
           if(orginNanme.equals(".DS_Store")){
               continue;
           }

            InfoSet infoSet = new InfoSet();
            infoSet.setOrginalName(orginNanme);
            FactModel factModel = factSeek.factInfor(filePathList.get(i));
            infoSet.setFactModel(factModel);
            System.out.println("seek data"+factModel.getDate());
            CriminalJudgmenSeek cjs = new CriminalJudgmenSeek(filePathList.get(i));
            CriminalInfo criminalInfo =  cjs.criminalInfoSeek();
            ArrayList<Evidence> evidences =  cjs.evidenceSeek();
            TraumaticInfo traumaticInfo =  cjs.traumaticInfoSeek();
            infoSet.setOffSet(cjs.isOffset());
            infoSet.setOffSetByLaw(cjs.offSetByLaw());
            infoSet.setEvidences(evidences);
            infoSet.setCriminalInfo(criminalInfo);
            infoSet.setTraumaticInfo(traumaticInfo);

            CivilActionSeek cas = new CivilActionSeek(filePathList.get(i));
            infoSet.setHasCivilAction(cas.hasCivilAction());
            if(cas.hasCivilAction().equals("是"))
                infoSet.setCivilActionContent(cas.getCivilActionContent());
            else
                infoSet.setCivilActionContent("否");

            JudgedResultSeeker judgedResultSeeker = new JudgedResultSeeker(filePathList.get(i));
            JudgeResult judgeResult = judgedResultSeeker.seekJudgedResult();
            infoSet.setJudgeResult(judgeResult);

            infoSets.add(infoSet);
        }
      return infoSets;
    }

    /**
     * 批量输出excel
     * @param targetfilePath
     * @param infoSets
     */
    public void output(String targetfilePath, List<InfoSet> infoSets) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("故意伤害罪信息要素表");
        // 创建表头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("事件发生时间");
        row1.createCell(1).setCellValue("事件发生原因");
        row1.createCell(2).setCellValue("事件具体过程");
        row1.createCell(3).setCellValue("被告信息综述");
        row1.createCell(4).setCellValue("被告国籍");
        row1.createCell(5).setCellValue("被告类型");
        row1.createCell(6).setCellValue("是否是少年犯罪");
        row1.createCell(7).setCellValue("是否具有刑事承担能力");
        row1.createCell(8).setCellValue("证据");
        row1.createCell(9).setCellValue("被害人受伤级别");
        row1.createCell(10).setCellValue("被害人是否死亡");
        row1.createCell(11).setCellValue("酌定量刑情节");
        row1.createCell(12).setCellValue("法定量刑情节");
        row1.createCell(13).setCellValue("是否附带民事诉讼");
        row1.createCell(14).setCellValue("民事诉讼");
        row1.createCell(15).setCellValue("判决结果");
        row1.createCell(16).setCellValue("原裁判文书");

        //向表中写入数据
         for(int i = 0;i<infoSets.size();i++){
             InfoSet infoSet = infoSets.get(i);
             FactModel factModel = infoSet.getFactModel();
             CriminalInfo criminalInfo = infoSet.getCriminalInfo();
             String evidence = praseEvidence(infoSet.getEvidences());
             TraumaticInfo traumaticInfo = infoSet.getTraumaticInfo();
             String offset = infoSet.getOffSet();
             String offsetByLaw = infoSet.getOffSetByLaw();
             String hasCivilAction = infoSet.getHasCivilAction();
             String civilAction = infoSet.getCivilActionContent();
             JudgeResult judgeResult = infoSet.getJudgeResult();
             HSSFRow row = sheet.createRow(i+1);
             row.createCell(0).setCellValue(factModel.getDate());
             System.out.println("填数据："+factModel.getDate());
             try {
                 row.createCell(1).setCellValue(factModel.getReason());
                 row.createCell(2).setCellValue(factModel.getDetails());
                 row.createCell(3).setCellValue(criminalInfo.getBackground());
                 row.createCell(4).setCellValue(criminalInfo.getNation());
                 row.createCell(5).setCellValue(criminalInfo.getType());
                 row.createCell(6).setCellValue(criminalInfo.getIsjuvenile());
                 row.createCell(7).setCellValue(criminalInfo.getIsRes());
                 row.createCell(8).setCellValue(evidence);
                 row.createCell(9).setCellValue(traumaticInfo.getEvaluation());
                 row.createCell(10).setCellValue(traumaticInfo.getIdDead());
                 row.createCell(11).setCellValue(offset);
                 row.createCell(12).setCellValue(offsetByLaw);
                 row.createCell(13).setCellValue(hasCivilAction);
                 row.createCell(14).setCellValue(civilAction);
                 row.createCell(15).setCellValue(judgeResult.getResult());
                 row.createCell(16).setCellValue(infoSet.getOrginalName());
             }catch (IllegalArgumentException e) {

             }
         }


        File file=new File(targetfilePath);
        try {
            System.out.println("xinwenjian1");
            file.createNewFile();
            System.out.println("xinwenjian2");
            //将excel写入
            FileOutputStream stream= FileUtils.openOutputStream(file);
            System.out.println("xinwenjian3");
            wb.write(stream);
            System.out.println("xinwenjian4");
            stream.close();
            System.out.println("xinwenjian5");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String praseEvidence(ArrayList<Evidence> evidences) {
        String evidence = "";
        for(Evidence e:evidences) {
            String des = e.getDescription();
            String type = e.getType();
            evidence += des + "(类型："+type+")\n";
        }
        System.out.println("evidence1");
        return evidence;
    }
}
