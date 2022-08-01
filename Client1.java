import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4242);
        BufferedReader brf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());

        String[] options = {"Login", "Signup"};

        String option = (String) JOptionPane.showInputDialog(null, "What do you want to do",
                "Login/signup", JOptionPane.PLAIN_MESSAGE, null, options, null);
        pw.println(option);
        pw.flush();

        try {
            if (option.equals("Login")) {
                String[] roles = {"Customer", "Seller"};
                String role = (String) JOptionPane.showInputDialog(null, "Choose your role",
                        "Role selection", JOptionPane.PLAIN_MESSAGE, null, roles, null);
                pw.println(role);
                pw.flush();

                if (role.equals("Customer")) {
                    String username = JOptionPane.showInputDialog(null, "Please enter your username",
                            "Login", JOptionPane.QUESTION_MESSAGE);
                    pw.println(username);
                    pw.flush();
                    String password = JOptionPane.showInputDialog(null, "Please enter your password",
                            "Login", JOptionPane.QUESTION_MESSAGE);
                    pw.println(password);
                    pw.flush();

                    String check = brf.readLine();
                    if (check.equals("Correct")) {
                        String[] customerChoices = {"View approved appointments", "Make/cancel appointments",
                                "View Calender"};
                        String customerMove = (String) JOptionPane.showInputDialog(null,
                                "What do you want to do", "Customer menu", JOptionPane.PLAIN_MESSAGE,
                                null, customerChoices, null);
                        pw.println(customerMove);
                        pw.flush();

                        if (customerMove.equals("View approved appointments")) {
                            String approvedAppointment = brf.readLine();
                            String[] parts = approvedAppointment.split(";");
                            String approvedAppointments = "";
                            for (int i = 0; i < parts.length; i++) {
                                if (i + 1 == parts.length) {
                                    approvedAppointments += parts[i];
                                } else {
                                    approvedAppointments += parts[i] + "\n";
                                }
                            }
                            JOptionPane.showMessageDialog(null, approvedAppointments, "View approved appointments", JOptionPane.PLAIN_MESSAGE);
                        } else if (customerMove.equals("Make/cancel appointments")) {
                            String[] appointmentOptions = {"Make an appointment", "Cancel an appointment"};
                            String appointmentMove = (String) JOptionPane.showInputDialog(null,
                                    "What do you want to do", "Make/cancel appointments",
                                    JOptionPane.PLAIN_MESSAGE, null, appointmentOptions, null);

                            pw.println(appointmentMove);
                            pw.flush();
                            String calendar = JOptionPane.showInputDialog(null,
                                    "What is the calendar name", "Make/cancel appointments",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(calendar);
                            pw.flush();
                            String title = JOptionPane.showInputDialog(null,
                                    "What is the appointment title", "Make/cancel appointments",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(title);
                            pw.flush();
                            if (appointmentMove.equals("Make an appointment")) {
                                String result = brf.readLine();
                                JOptionPane.showMessageDialog(null, result, "Make an appointment",
                                        JOptionPane.PLAIN_MESSAGE);
                                String now = brf.readLine();
                                JOptionPane.showMessageDialog(null, now, "Calendar's last change",
                                        JOptionPane.PLAIN_MESSAGE);
                            } else if (appointmentMove.equals("Cancel an appointment")) {
                                String appointmentResult = brf.readLine();
                                JOptionPane.showMessageDialog(null, appointmentResult,
                                        "Cancel an Appointment", JOptionPane.PLAIN_MESSAGE);
                                String now = brf.readLine();
                                JOptionPane.showMessageDialog(null, now, "Calendar's last change",
                                        JOptionPane.PLAIN_MESSAGE);
                                String customerResult = brf.readLine();
                                JOptionPane.showMessageDialog(null, customerResult,
                                        "Cancel an Appointment", JOptionPane.PLAIN_MESSAGE);
                            }
                        } else if (customerMove.equals("View Calender")) {
                            String store = JOptionPane.showInputDialog(null,
                                    "What is the store name", "View Calender",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(store);
                            pw.flush();
                            String calendarName = brf.readLine();
                            String[] calendarParts = calendarName.split(";");
                            String calendarNames = "";
                            for (int i = 0; i < calendarParts.length; i++) {
                                if (i + 1 == calendarParts.length) {
                                    calendarNames += calendarParts[i];
                                } else {
                                    calendarNames += calendarParts[i] + "\n";
                                }
                            }
                            JOptionPane.showMessageDialog(null, calendarNames, "Store list",
                                    JOptionPane.PLAIN_MESSAGE);
                            String calendar = JOptionPane.showInputDialog(null,
                                    "What is the calendar name", "View Calender",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(calendar);
                            pw.flush();
                            String appointment = brf.readLine();
                            String[] parts = appointment.split(";");
                            String appointments = "";
                            for (int i = 0; i < parts.length; i++) {
                                if (i + 1 == parts.length) {
                                    appointments += parts[i];
                                } else {
                                    appointments += parts[i] + "\n";
                                }
                            }
                            JOptionPane.showMessageDialog(null, appointments, "Calendar list",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter the correct username and " +
                                "password and start over", "Incorrect credentials", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (role.equals("Seller")) {
                    String username = JOptionPane.showInputDialog(null, "Please enter your username",
                            "Login", JOptionPane.QUESTION_MESSAGE);
                    pw.println(username);
                    pw.flush();

                    String password = JOptionPane.showInputDialog(null, "Please enter your password",
                            "Login", JOptionPane.QUESTION_MESSAGE);
                    pw.println(password);
                    pw.flush();

                    String check = brf.readLine();
                    if (check.equals("Correct")) {
                        String[] sellerChoice = {"Modify calendar", "Approve appointments",
                                "View store's approved appointments", "Create store"};
                        String sellerMove = (String) JOptionPane.showInputDialog(null,
                                "What do you want to do", "Seller menu", JOptionPane.PLAIN_MESSAGE,
                                null, sellerChoice, null);
                        pw.println(sellerMove);
                        pw.flush();

                        if (sellerMove.equals("Modify calendar")) {
                            String storeName = JOptionPane.showInputDialog(null,
                                    "What is the store name", "Modify calendar", JOptionPane.QUESTION_MESSAGE);
                            pw.println(storeName);
                            pw.flush();

                            String[] calendarChoice = {"Create calendar", "Edit calendar", "Delete calendar"};
                            String calendarMove = (String) JOptionPane.showInputDialog(null,
                                    "What do you want to do with the calendar", "Calendar menu",
                                    JOptionPane.PLAIN_MESSAGE, null, calendarChoice, null);
                            pw.println(calendarMove);
                            pw.flush();

                            if (calendarMove.equals("Create calendar")) {
                                String[] createOptions = {"Build an empty calendar", "Import a CSV file to create a " +
                                        "calendar"};
                                String createMove = (String) JOptionPane.showInputDialog(null,
                                        "How do you want to create a calendar", "Create calendar",
                                        JOptionPane.PLAIN_MESSAGE, null, createOptions, null);
                                pw.println(createMove);
                                pw.flush();

                                if (createMove.equals("Build an empty calendar")) {
                                    String calendarFilename = JOptionPane.showInputDialog(null,
                                            "What is the name of the calendar", "Create an empty calendar",
                                            JOptionPane.QUESTION_MESSAGE);
                                    pw.println(calendarFilename);
                                    pw.flush();
                                    String calendarDescription = JOptionPane.showInputDialog(null,
                                            "What is the description of the calendar",
                                            "Create an empty calendar", JOptionPane.QUESTION_MESSAGE);
                                    pw.println(calendarDescription);
                                    pw.flush();
                                    String result = brf.readLine();
                                    JOptionPane.showMessageDialog(null, result,
                                            "Build an empty calendar", JOptionPane.PLAIN_MESSAGE);
                                } else if (createMove.equals("Import a CSV file to create a calendar")) {
                                    String calendarFilename = JOptionPane.showInputDialog(null,
                                            "What is the name of the calendar", "Create an empty calendar",
                                            JOptionPane.QUESTION_MESSAGE);
                                    pw.println(calendarFilename);
                                    pw.flush();
                                    String calendarDescription = JOptionPane.showInputDialog(null,
                                            "What is the description of the calendar",
                                            "Create an empty calendar", JOptionPane.QUESTION_MESSAGE);
                                    pw.println(calendarDescription);
                                    pw.flush();
                                    String calendarResult = brf.readLine();
                                    JOptionPane.showMessageDialog(null, calendarResult, "Import calendar",
                                            JOptionPane.PLAIN_MESSAGE);
                                    String filenameCSV = JOptionPane.showInputDialog(null,
                                            "Please enter the filename of CSV file and please include .txt",
                                            "Import calendar", JOptionPane.QUESTION_MESSAGE);
                                    pw.println(filenameCSV);
                                    pw.flush();
                                    String result = brf.readLine();
                                    JOptionPane.showMessageDialog(null, result, "Import calendar",
                                            JOptionPane.PLAIN_MESSAGE);
                                }
                            } else if (calendarMove.equals("Edit calendar")) {
                                String calendarName = JOptionPane.showInputDialog(null,
                                        "What is the name of the calendar", "Edit Calendar",
                                        JOptionPane.QUESTION_MESSAGE);
                                pw.println(calendarName);
                                pw.flush();
                                String[] calendarEdition = {"Add appointment", "Remove appointment"};
                                String calenderEditionMove =(String) JOptionPane.showInputDialog(null,
                                        "How do you want to edit the calendar", "Calendar editor",
                                        JOptionPane.PLAIN_MESSAGE, null, calendarEdition, null);
                                pw.println(calenderEditionMove);
                                pw.flush();

                                if (calenderEditionMove.equals("Add appointment")) {
                                    String title = JOptionPane.showInputDialog(null,
                                            "What is the appointment title", "Add appointment",
                                            JOptionPane.QUESTION_MESSAGE);
                                    pw.println(title);
                                    pw.flush();
                                    int capacity = Integer.parseInt(JOptionPane.showInputDialog(null,
                                            "What is the max capacity", "Add appointment",
                                            JOptionPane.QUESTION_MESSAGE));
                                    pw.println(capacity);
                                    pw.flush();
                                    int attendance = Integer.parseInt(JOptionPane.showInputDialog(null,
                                            "What is the current number of attendance", "Add appointment",
                                            JOptionPane.QUESTION_MESSAGE));
                                    pw.println(attendance);
                                    pw.flush();
                                    int startTime = Integer.parseInt(JOptionPane.showInputDialog(null,
                                            "What is the starting time (from 0 to 23)", "Add appointment",
                                            JOptionPane.QUESTION_MESSAGE));
                                    pw.println(startTime);
                                    pw.flush();
                                    int endTime = Integer.parseInt(JOptionPane.showInputDialog(null,
                                            "What is the ending time (from 0 to 23)", "Add appointment",
                                            JOptionPane.QUESTION_MESSAGE));
                                    pw.println(endTime);
                                    pw.flush();
                                    String result = brf.readLine();
                                    JOptionPane.showMessageDialog(null, result, "Add appointment",
                                            JOptionPane.PLAIN_MESSAGE);
                                    String now = brf.readLine();
                                    JOptionPane.showMessageDialog(null, now, "Calendar's last change",
                                            JOptionPane.PLAIN_MESSAGE);
                                } else if (calenderEditionMove.equals("Remove appointment")) {
                                    String calendar = JOptionPane.showInputDialog(null,
                                            "What is the name of the calendar", "Remove appointment",
                                            JOptionPane.QUESTION_MESSAGE);
                                    pw.println(calendar);
                                    pw.flush();
                                    String now = brf.readLine();
                                    JOptionPane.showMessageDialog(null, now, "Calendar's last change",
                                            JOptionPane.PLAIN_MESSAGE);
                                }
                            } else if (calendarMove.equals("Delete calendar")) {
                                String calendarFilename = JOptionPane.showInputDialog(null,
                                        "What is the name of the calendar", "Delete calendar",
                                        JOptionPane.QUESTION_MESSAGE);
                                pw.println(calendarFilename);
                                pw.flush();
                                String result = brf.readLine();
                                JOptionPane.showMessageDialog(null, result, "Delete calendar",
                                        JOptionPane.PLAIN_MESSAGE);
                            }

                        } else if (sellerMove.equals("Approve appointments")) {
                            String calenderName = JOptionPane.showInputDialog(null,
                                    "Please enter the calender name", "Approve appointment",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(calenderName);
                            pw.flush();
                            String title = JOptionPane.showInputDialog(null,
                                    "Please enter the appointment title", "Approve appointment",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(title);
                            pw.flush();
                            String customerFilename = JOptionPane.showInputDialog(null,
                                    "Please enter the customer filename", "Approve appointment",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(customerFilename);
                            pw.flush();
                            String result = brf.readLine();
                            JOptionPane.showMessageDialog(null, result, "Approve appointments",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else if (sellerMove.equals("View store's approved appointments")) {
                            String store = JOptionPane.showInputDialog(null,
                                    "Please enter the store name", "Approve appointment",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(store);
                            pw.flush();
                            String appointment = brf.readLine();
                            String[] parts = appointment.split(";");
                            String appointments = "";
                            for (int i = 0; i < parts.length; i++) {
                                if (i + 1 == parts.length) {
                                    appointments += parts[i];
                                } else {
                                    appointments += parts[i] + "\n";
                                }
                            }
                            JOptionPane.showMessageDialog(null, appointments,
                                    "View store's approved appointments", JOptionPane.PLAIN_MESSAGE);
                        } else if (sellerMove.equals("Create store")) {
                            String store = JOptionPane.showInputDialog(null,
                                    "Please enter the store name", "Approve appointment",
                                    JOptionPane.QUESTION_MESSAGE);
                            pw.println(store);
                            pw.flush();
                            String result = brf.readLine();
                            JOptionPane.showMessageDialog(null, result, "Create store",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The Program is shutting down",
                                    "Goodbye", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            } else if (option.equals("Signup")) {
                String[] roles = {"Customer", "Seller"};
                String role = (String) JOptionPane.showInputDialog(null, "Choose your role",
                        "Role selection", JOptionPane.PLAIN_MESSAGE, null, roles, null);
                pw.println(role);
                pw.flush();

                if (role.equals("Customer")) {
                    String username = JOptionPane.showInputDialog(null, "Please enter your username",
                            "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(username);
                    pw.flush();
                    String password = JOptionPane.showInputDialog(null, "Please enter your password",
                            "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(password);
                    pw.flush();
                    String filename = JOptionPane.showInputDialog(null,
                            "Please enter the name of your file", "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(filename);
                    pw.flush();
                    String result = brf.readLine();
                    JOptionPane.showMessageDialog(null, result, "Signup", JOptionPane.PLAIN_MESSAGE);
                } else if (role.equals("Seller")) {
                    String username = JOptionPane.showInputDialog(null, "Please enter your username",
                            "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(username);
                    pw.flush();

                    String password = JOptionPane.showInputDialog(null, "Please enter your password",
                            "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(password);
                    pw.flush();
                    String filename = JOptionPane.showInputDialog(null,
                            "Please enter the name of your file", "Signup", JOptionPane.QUESTION_MESSAGE);
                    pw.println(filename);
                    pw.flush();
                    String approvedList = JOptionPane.showInputDialog(null,
                            "Please enter the name of the approved appointment list", "Signup",
                            JOptionPane.QUESTION_MESSAGE);
                    pw.println(approvedList);
                    pw.flush();
                    String result = brf.readLine();
                    JOptionPane.showMessageDialog(null, result, "Signup", JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No Input",
                    "The process is interrupted", JOptionPane.PLAIN_MESSAGE);
        }
        pw.close();
        brf.close();
    }
}
