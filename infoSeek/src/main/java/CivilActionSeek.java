import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;
import java.io.IOException;
import java.util.List;

/**
 * 提取刑事案件附带民事信息
 * Created by alice on 18/4/12.
 */
public class CivilActionSeek {

        private Element root;

        public CivilActionSeek(String filePath) {
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

        public String hasCivilAction() {
            String general = root.getChild("QW").getAttributeValue("value");
            if(general.contains("民事诉讼")){
                System.out.println("是否附带民事诉讼:" + "是");
                return "是";
            }else{
                System.out.println("是否附带民事诉讼:" + "否");
                return "否";
            }

        }

        public String getCivilActionContent() {
            String content="";
            List<Element> contentNode = root.getChild("QW").getChild("PJJG").getChildren("FDMSPJJGFZ");
            if(null!=contentNode) {
                for(Element e: contentNode) {
                    String contentDes = e.getAttributeValue("value");
                    content+=contentDes+"\n";
                }
            }
            System.out.println("民事诉讼内容："+content);
           return content;
        }

    }

