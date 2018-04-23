import model.JudgeResult;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;

import java.io.IOException;

/**
 * Created by alice on 18/4/21.
 */
public class JudgedResultSeeker {
    private Element root;

    public JudgedResultSeeker(String filePath) {
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

  public JudgeResult seekJudgedResult () {
        Element result = root.getChild("QW").getChild("PJJG");
        String result_summary = result.getAttributeValue("value");
        String[] sentences = result_summary.split("。");
        JudgeResult judgedResult = new JudgeResult(sentences[0]);
        return judgedResult;
  }
}
