import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
/**
 * The seller classes read two files one is the store list and one contains all approved appointments.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Seller {
    ArrayList<Store> stores;
    ArrayList<Appointment> appointments;
    ArrayList<String> storeFileNames;
    String fileName;
    String appointmentListLocation;

    public Seller(String fileName, String appointmentListLocation) {
        this.fileName = fileName;
        this.stores = new ArrayList<>();
        this.appointmentListLocation = appointmentListLocation;
        this.storeFileNames = new ArrayList<>();
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            String store = brf.readLine();
            while (store != null) {
                storeFileNames.add(store);
                store = brf.readLine();
            }
            brf.close();
            for (int i = 0; i < storeFileNames.size(); i++) {
                stores.add(new Store(storeFileNames.get(i)));
            }
            this.appointments = new ArrayList<>();
            File g = new File(appointmentListLocation);
            FileReader fr1 = new FileReader(g);
            BufferedReader brf1 = new BufferedReader(fr1);
            String appointment = brf1.readLine();
            while (appointment != null) {
                String[] parts = appointment.split(",");
                appointments.add(new Appointment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                appointment = brf1.readLine();
            }
            brf1.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter the correct customer and appointment list filename and start over",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String addToApproveList(String calenderFileLocation, String title, String customerFileLocation,
                                 String sellerFileLocation) {
        String output = "";
        boolean found = false;
        try {
            File f = new File(calenderFileLocation);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            ArrayList<Appointment> appointmentList = new ArrayList<>();
            brf.readLine();
            brf.readLine();
            brf.readLine();
            String appointment = brf.readLine();
            while (appointment != null) {
                String[] parts = appointment.split(",");
                appointments.add(new Appointment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                appointment = brf.readLine();
            }
            brf.close();
            Appointment approvedAppointment = null;
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getTitle().equals(title)) {
                    approvedAppointment = appointments.get(i);
                    found = true;
                }
            }
            if (found && approvedAppointment != null) {
                File g = new File(customerFileLocation);
                FileOutputStream fos1 = new FileOutputStream(g, true);
                PrintWriter pw1 = new PrintWriter(fos1);
                File h = new File(sellerFileLocation);
                FileOutputStream fos2 = new FileOutputStream(h, true);
                PrintWriter pw2 = new PrintWriter(fos2);
                pw1.println(approvedAppointment);
                pw2.println(approvedAppointment);
                pw1.close();
                pw2.close();
                output = "The appointment was added to the list";
            } else {
                output = "Please enter the correct info and start over";
            }
        } catch (IOException e) {
            output = "Please enter the correct info and start over";
        }
        return output;
    }

    public String createStore(String file) throws IOException {
        File f = new File(file);
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        pw.close();
        this.stores.add(new Store(file));
        this.storeFileNames.add(file);
        File g = new File(this.fileName);
        FileOutputStream fos1 = new FileOutputStream(g);
        PrintWriter pw1 = new PrintWriter(fos1);
        pw1.println(file);
        pw1.close();
        updateSeller();
        return "You just got a new store";
    }

    public String viewApprovalList(String store) {
        ArrayList<String> storeNames = new ArrayList<>();
        String output = "Title,Calendar,Store,Max Capacity,Attendance,Start Time,End Time;";
        boolean found = false;
        for (int i = 0; i < this.appointments.size(); i++) {
            if (this.appointments.get(i).getStore().equals(store)) {
                storeNames.add(appointments.get(i).toString());
                found = true;
            }
        }
        if (found) {
            for (int i = 0; i < storeNames.size(); i++) {
                if (i + 1 == storeNames.size()) {
                    output += storeNames.get(i);
                } else {
                    output += storeNames.get(i) + ";";
                }
            }
        }
        return output;
    }

    public String getFileName() {
        return fileName;
    }

    public void updateSeller() throws IOException {
        File f = new File(getFileName());
        FileOutputStream fos = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < stores.size(); i++) {
            pw.println(stores);
        }
    }


}
