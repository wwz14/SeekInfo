import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

/**
 * Created by alice on 18/4/14.
 */
public class Reader {
    public static void main(String[] args) {
        Reader reader = new Reader();

        try {
            reader.reader("/Users/alice/Desktop/2012");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> reader(String dirPath) throws IOException {
        File file = new File(dirPath);
        List<String> r = DirErgodic.ergodic(file,new ArrayList<String>());
        return r;
    }
}
