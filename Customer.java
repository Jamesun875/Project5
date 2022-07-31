import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
/**
 * The customer class will read its own file only containing their approved appointments and store it into the customer
 * object. It can list all approved appointments on the terminal
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Customer {
    ArrayList<Appointment> approvedAppointments;
    String fileName;

    public Customer(String fileName) {
        this.fileName = fileName;
        this.approvedAppointments = new ArrayList<>();
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            String appointment = brf.readLine();
            while (appointment != null) {
                String[] parts = appointment.split(",");
                approvedAppointments.add(new Appointment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                appointment = brf.readLine();
            }
            brf.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter the correct customer filename and start over", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String viewApprovedAppointments() {
        String output = "Title,Calendar,Store,Max Capacity,Attendance,Start Time,End Time;";
        for (int i = 0; i < this.approvedAppointments.size(); i++) {
            if (i + 1 == this.approvedAppointments.size()) {
                output += this.approvedAppointments.get(i);
            } else {
                output += this.approvedAppointments.get(i) + ";";
            }
        }
        return output;
    }

    public String cancelAppointment(String appointmentTitle) throws IOException {
        boolean found = false;
        int location = 0;
        String output = "";
        for (int i = 0; i < this.approvedAppointments.size(); i++) {
            if (this.approvedAppointments.get(i).getTitle().equals(appointmentTitle)) {
                location = i;
                found = true;
            }
        }
        if (found) {
            this.approvedAppointments.remove(location);
            output = "appointment is cancelled";
            File f = new File(this.fileName);
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < this.approvedAppointments.size(); i++) {
                pw.println(this.approvedAppointments.get(i));
            }
            pw.close();
        } else {
            output = "Cannot find the appointment in customer file";
        }
        return output;
    }
}
