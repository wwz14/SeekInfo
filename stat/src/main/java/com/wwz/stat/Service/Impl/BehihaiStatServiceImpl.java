package com.wwz.stat.Service.Impl;

import com.wwz.stat.Model.StatPair;
import com.wwz.stat.Model.StatResult;
import com.wwz.stat.Service.BeihaiStatService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by alice on 18/4/24.
 */
@Service
public class BehihaiStatServiceImpl implements BeihaiStatService {



    public static void main(String[] args) {
        BeihaiStatService s = new BehihaiStatServiceImpl();
        s.beihaiStat();
    }

    @Override
    public StatResult beihaiStat() {
        //读excel获取数据
        int isRes = 0;
        int notRes = 0;
        //File file1 = new File("/Users/alice/Desktop/result/2012.xls");
        ArrayList<StatPair> pairs = new ArrayList<>();
        StatResult result = new StatResult();
        try {
            //创建输入流读取excel
            InputStream is = new FileInputStream("/Users/alice/Desktop/result/2012.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            int numOfSheet = xssfWorkbook.getNumberOfSheets();
            for(int i = 0;i<numOfSheet;i++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                int numOfRow = xssfSheet.getLastRowNum();
                for(int j = 1; j<=numOfRow;j++) {
                    Row currentRow = xssfSheet.getRow(j);
                    Cell res = currentRow.getCell(7);
                  //  System.out.println(String.valueOf(res.getStringCellValue()));
                    String resContent = String.valueOf(res.getStringCellValue());
                    if(resContent.equals("完全刑事责任能力")) {
                        isRes++;
                    }else{
                        notRes++;
                    }
                }

            }

            System.out.println(isRes);
            System.out.println(notRes);
            StatPair resPair = new StatPair("具有完全刑事责任能力",isRes+"");
            StatPair notResPair = new StatPair("不具有完全刑事责任能力",notRes+"");

            pairs.add(resPair);
            pairs.add(notResPair);

            result.setData(pairs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

        return result;
    }
}
