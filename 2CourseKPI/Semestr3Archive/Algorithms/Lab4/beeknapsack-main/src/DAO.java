import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DAO {

    private static DAO instance;
    private static String filename = "data.txt";


    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    public static void setFileName(String filename_) {
        filename = filename_;
    }

    private DAO() {	}


    public void readParcel() throws IOException {

        BufferedReader br = null;
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filePath.concat("\\" + filename)));

            sCurrentLine = br.readLine();

            Knapsack.setMaxWeight((Double.parseDouble(sCurrentLine.split(" ")[0])));

        } finally {
            if (br != null)br.close();
        }
    }


    public ArrayList<Item> readItems() throws IOException {

        BufferedReader br = null;
        String filePath = new File("").getAbsolutePath();

        try {

            ArrayList<Item> result = new ArrayList<>();

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filePath.concat("\\" + filename)));

            ArrayList<String> lines = new ArrayList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                lines.add(sCurrentLine);
            }

            for (int i = 1; i < lines.size(); i++) {

                String[] item = lines.get(i).split(" ");

                String name = item[0];
                double weight = Double.parseDouble(item[1]);
                double price = Double.parseDouble(item[2]);

                result.add(new Item(name, weight, price));
            }

            return result;

        } finally {
            if (br != null)br.close();
        }
    }
}
