import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Created by alice on 18/4/12.
 */
public class TestMain
{
    public static void main(String[] args)  {
//        FactSeek fs = new FactSeek();
//        try {
//            fs.factInfor("/Users/alice/Desktop/2013 2/101.xml");
//        } catch (JDOMException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        CriminalJudgmenSeek cjs  = new CriminalJudgmenSeek("/Users/alice/Desktop/2013 2/101.xml");

            cjs.criminalInfoSeek();

    }
}
