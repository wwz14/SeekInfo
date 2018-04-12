import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Created by alice on 18/4/12.
 */
public class TestMain
{
    public static void main(String[] args)  {
        FactSeek fs = new FactSeek();
        try {
            fs.factInfor("file:///Users/alice/Desktop/2012/13402.xml");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CriminalJudgmenSeek cjs  = new CriminalJudgmenSeek("file:///Users/alice/Desktop/2012/13402.xml");

        cjs.criminalInfoSeek();

    }
}
