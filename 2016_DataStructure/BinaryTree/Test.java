import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * March 2016
 * @author Ping Zhang
 */
public class Test {

    public static void main(String[] args) {

        InventoryTree tree1 = new InventoryTree();

        File file = new File("Items.txt");

        try {
            FileReader r = new FileReader(file);
            BufferedReader br = new BufferedReader(r);

            String s;
            while ((s = br.readLine()) != null) {
                String[] params = s.split(" ");
                tree1.addItem(new Item(params[0], params[1],
                        Integer.parseInt(params[2]), Double.parseDouble(params[3])));

                sopln(tree1.findItem(params[0]));
            }
            r.close();
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(TestAsg4.class.getName()).log(Level.SEVERE, null, ex);
        }

        sopln(tree1);
    }

    /**
     * A simplified System.out.println(String s)
     *
     * @param obj any object to be printed
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }

}
