import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
/**
 * The store class will read store files listing calendar files names.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Store {
    ArrayList<Calender> calenders;
    ArrayList<String> calenderNames;
    String fileName;



    public Store(String fileName) throws IOException {
        this.calenders = new ArrayList<>();
        this.fileName = fileName;
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            String calender = brf.readLine();
            this.calenderNames = new ArrayList<>();
            while (calender != null) {
                calenderNames.add(calender);
                calender = brf.readLine();
            }
            brf.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter the correct store filename and start over", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        for (int i = 0; i < calenderNames.size(); i++) {
            calenders.add(new Calender(calenderNames.get(i)));
        }
    }

    public String addCalender(String file, String description) throws IOException {
        File f = new File(file);
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(file);
        pw.println(description);
        pw.close();
        calenders.add(new Calender(file));
        calenderNames.add(file);
        updateStore();
        return "The calendar is successfully added";
    }

    public String deleteCalender(String file) {
        File f = new File(file);
        if (f.delete()) {
            return "The calendar is successfully deleted";
        } else {
            return "The calender was not found";
        }
    }

    public void updateStore() {
        try {
            File f = new File(getFileName());
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < calenders.size(); i++) {
                pw.println(calenders.get(i).getFileName());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getFileName() {
        return fileName;
    }

    public ArrayList<String> getCalenderNames() {
        return calenderNames;
    }
}
