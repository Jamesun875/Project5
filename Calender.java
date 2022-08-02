import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * The calendar class will read a calendar file with a calendar title, a description, a time when last time the calendar
 * is modified, and lines of appointments
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Calender {
    ArrayList<Appointment> appointments;
    String title;
    String description;
    String now;
    String fileName;

    public Calender(String fileName) {
        this.fileName = fileName;
        this.appointments = new ArrayList<>();
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            this.title = brf.readLine();
            this.description = brf.readLine();
            String now1 = brf.readLine();
            String line = brf.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                appointments.add(new Appointment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                line = brf.readLine();
            }
            brf.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter the correct calendar file name and start over", "Incorrect filename",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.now = new SimpleDateFormat("ddMMyyyyHHmm").format(new Date());

    }


    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNow() {
        return now;
    }

    public String getFileName() {
        return fileName;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setTitle(String title) {
        this.title = title;
        renewTimeStamp();
    }

    public void setDescription(String description) {
        this.description = description;
        renewTimeStamp();
    }

    public void renewTimeStamp() {
        this.now = new SimpleDateFormat("ddMMyyyyHHmm").format(new Date());
    }

    public void buildAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        renewTimeStamp();
    }

    public void deleteAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        renewTimeStamp();
    }

    public String makeAppointment(String title) {
        String output = "";
        boolean success = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getTitle().equals(title)) {
                if (appointments.get(i).getBooking() + 1 <= appointments.get(i).getCapacity()) {
                    output = "Appointment is made";
                    success = true;
                    appointments.get(i).setBooking(appointments.get(i).getBooking() + 1);
                } else {
                    output = "Error. Capacity exceeded\nPlease start over";
                }
            }
        }
        if (!success) {
            output = "The appointment was not found\nPlease start over";
        }
        renewTimeStamp();
        updateCalender();
        return output;
    }

    public String cancelAppointment(String title) {
        String output = "";
        boolean success = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getTitle().equals(title) && appointments.get(i).getBooking() > 0) {
                output = "Appointment is canceled";
                success = true;
                appointments.get(i).setBooking(appointments.get(i).getBooking() - 1);
            }
        }
        if (!success) {
            output = "The appointment was not found\nPlease start over";
        }
        renewTimeStamp();
        updateCalender();
        return output;
    }


    public void editAppointment(String title, Appointment appointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getTitle().equals(title)) {
                appointments.set(i, appointment);
            }
        }
        renewTimeStamp();
    }

    public String buildAppointment(String title, String calender, String store, int capacity, int booking, int startTime,
                                 int endTime) {
        Appointment appointment = new Appointment(title, calender, store, capacity, booking, startTime, endTime);
        appointments.add(appointment);
        updateCalender();
        return "New appointment was added";
    }

    public String importCSVCalender(String fileNameCSV) {
        ArrayList<Appointment> calendarAppointments = new ArrayList<>();
        String output = "";
        try {
            File f = new File(fileNameCSV);
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            String line = brf.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                calendarAppointments.add(new Appointment(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6])));
                line = brf.readLine();
            }
            brf.close();
            File f1 = new File(getFileName());
            FileOutputStream fos = new FileOutputStream(f1, true);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < calendarAppointments.size(); i++) {
                pw.println(calendarAppointments.get(i));
            }
            pw.close();
            output = "successful import";
        } catch (Exception e) {
            output = "Please enter the correct filenames, and make sure the file only contains appointments with correct format";
        }
        return output;
    }
    public String removeAppointment(String title) {
        boolean found = false;
        int location = 0;
        String output = "";
        for (int i = 0; i < getAppointments().size(); i++) {
            if (getAppointments().get(i).getTitle().equals(title)) {
                found = true;
                location = i;
                output = "Appointment is removed";
            }

        }
        if (found) {
            this.appointments.remove(location);
        } else {
            output = "Cannot find the appointment, please start over";
        }
        updateCalender();
        return output;
    }

    public void updateCalender() {
        try {
            File f = new File(title);
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(getTitle());
            pw.println(getDescription());
            pw.println(getNow());
            for (int i = 0; i < appointments.size(); i++) {
                pw.println(appointments.get(i));
            }
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File output error", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
