import model.FactModel;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alice on 18/4/11.
 */
public class FactSeek {


public FactModel factInfor(String filePath) throws JDOMException, IOException {
    FactModel factmodel = new FactModel();
    SAXBuilder saxBuilder = new SAXBuilder();
    Document document = saxBuilder.build(filePath);
    Element root = document.getRootElement();

     Element caseCondition = root.getChild("QW").getChild("AJJBQK");
    if(null != caseCondition) {
        //获取案件基本情况语段
        String description = caseCondition.getAttributeValue("value");
        System.out.println("description of this case is:" + description);
        //解析案件发生时间
        //todo 具体发生时间

        Pattern pattern = Pattern.compile("[0-9]{4}[年][0-9]{1,2}[月][0-9]{1,2}[日]");
        Matcher matcher = pattern.matcher(description);

        String dateInfo = "";
        if (matcher.find()) {
            dateInfo = matcher.group(0);
            System.out.println("Date of this case happened is :" + dateInfo);
            factmodel.setDate(dateInfo);
        }

        //提取案件发生原因
        Pattern reasonPattern = Pattern.compile("[因].*[口角]");
        Pattern reasonPattern2 = Pattern.compile("[因].*[争执]");
        Matcher reasonMatch = reasonPattern.matcher(description);
        Matcher reasonMatch2 = reasonPattern2.matcher(description);
        String reason = "";
        if (reasonMatch.find()) {
            reason = reasonMatch.group(0);
            System.out.println("reason of this case is" + reason);
        } else if (reasonMatch2.find()) {
            reason = reasonMatch2.group(0);
            System.out.println("reason2 of this case is" + reason);
        } else {
            reason = "no reason";
            System.out.println(reason);
        }
        factmodel.setReason(reason);

        //解析案发具体经过
        Element fact = caseCondition.getChild("ZKDL").getChild("ZKSS");
        String detail = "";
        if (null != fact) {
            String detail_description = caseCondition.getAttributeValue("value");
            String[] arr = detail_description.split("，");
            for (int i = 2; i < arr.length; i++) {
                detail += arr[i];
            }
            System.out.println(detail);
        } else {
            System.out.println("no ZKSS");
        }

        factmodel.setDetails(detail);
    }
    return factmodel;
}


}
