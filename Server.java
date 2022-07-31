import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    Socket socket;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4242);
        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            server.start();
        }
    }

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            String filename;
            String sellerAppointmentList;
            BufferedReader brf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            String option = brf.readLine();

            if (option.equals("Login")) {
                Account account = new Account();
                String role = brf.readLine();

                if (role.equals("Customer")) {
                    String username = brf.readLine();
                    String password = brf.readLine();

                    if (account.checkCustomer(username, password)) {
                        pw.println("Correct");
                        pw.flush();
                        filename = account.returnCustomerFilename(username);
                        String customerChoice = brf.readLine();

                        if (customerChoice.equals("View approved appointments")) {
                            Customer customer = new Customer(filename);
                            pw.println(customer.viewApprovedAppointments());
                            pw.flush();
                        } else if (customerChoice.equals("Make/cancel appointments")) {
                            String appointmentMove = brf.readLine();
                            String calendarName = brf.readLine();
                            Calender calender = new Calender(calendarName);
                            String title = brf.readLine();

                            if (appointmentMove.equals("Make an appointment")) {
                                pw.println(calender.makeAppointment(title));
                                pw.flush();
                                pw.println(calender.getNow());
                                pw.flush();
                            } else if (appointmentMove.equals("Cancel an appointment")) {
                                pw.println(calender.cancelAppointment(title));
                                pw.flush();
                                pw.println(calender.getNow());
                                pw.flush();
                                Customer customer = new Customer(filename);
                                pw.println(customer.cancelAppointment(title));
                                pw.flush();
                            }
                        } else if (customerChoice.equals("View Calender")) {
                            String storeName = brf.readLine();
                            Store store = new Store(storeName);
                            String calendars = "";
                            for (int i = 0; i < store.getCalenderNames().size(); i++) {
                                if (i + 1 == store.getCalenderNames().size()) {
                                    calendars += store.getCalenderNames().get(i);
                                } else {
                                    calendars += store.getCalenderNames().get(i) + ";";
                                }
                            }
                            pw.println(calendars);
                            pw.flush();
                            String calendarName = brf.readLine();
                            Calender calender = new Calender(calendarName);
                            String appointments = "Title,Calendar,Store,Max Capacity,Attendance,Start Time,End Time;";
                            for (int i = 0; i < calender.getAppointments().size(); i++) {
                                if (i + 1 == calender.getAppointments().size()) {
                                    appointments += calender.getAppointments().get(i);
                                } else {
                                    appointments += calender.getAppointments().get(i) + ";";
                                }
                            }
                            pw.println(appointments);
                            pw.flush();
                        }
                    } else {
                        pw.println("Incorrect");
                        pw.flush();
                    }
                } else if (role.equals("Seller")) {
                    String username = brf.readLine();
                    String password = brf.readLine();

                    if (account.checkSeller(username, password)) {
                        pw.println("Correct");
                        pw.flush();
                        filename = account.returnSellerFilename(username);
                        sellerAppointmentList = account.returnSellerAppointmentListName(username);
                        String sellerMove = brf.readLine();

                        if (sellerMove.equals("Modify calendar")) {
                            String storeName = brf.readLine();
                            Store store = new Store(storeName);
                            String calendarMove = brf.readLine();

                            if (calendarMove.equals("Create calendar")){
                                String createMove = brf.readLine();

                                if (createMove.equals("Build an empty calendar")) {
                                    String calendarFilename = brf.readLine();
                                    String calendarDescription = brf.readLine();
                                    pw.println(store.addCalender(calendarFilename, calendarDescription));
                                    pw.flush();
                                } else if (createMove.equals("Import a CSV file to create a calendar")) {
                                    String calendarFilename = brf.readLine();
                                    String calendarDescription = brf.readLine();
                                    pw.println(store.addCalender(calendarFilename, calendarDescription));
                                    pw.flush();
                                    Calender calender = new Calender(calendarFilename);
                                    String filenameCSV = brf.readLine();
                                    pw.println(calender.importCSVCalender(filenameCSV));
                                    pw.flush();
                                }
                            } else if (calendarMove.equals("Edit calendar")) {
                                String calendarName =brf.readLine();
                                Calender calender = new Calender(calendarName);
                                String calendarEditionMove = brf.readLine();

                                if (calendarEditionMove.equals("Add appointment")) {
                                    String title = brf.readLine();
                                    int capacity = Integer.parseInt(brf.readLine());
                                    int attendance = Integer.parseInt(brf.readLine());
                                    int startTime = Integer.parseInt(brf.readLine());
                                    int endTime = Integer.parseInt(brf.readLine());
                                    pw.println(calender.buildAppointment(title, calendarName, storeName, capacity,
                                            attendance, startTime, endTime));
                                    pw.flush();
                                    pw.println(calender.getNow());
                                    pw.flush();
                                } else if (calendarEditionMove.equals("Remove appointment")) {
                                    String title = brf.readLine();
                                    pw.println(calender.removeAppointment(title));
                                    pw.println(calender.getNow());
                                    pw.flush();
                                }
                            } else if (calendarMove.equals("Delete calendar")) {
                                String calendarName = brf.readLine();
                                pw.println(store.deleteCalender(calendarName));
                                pw.flush();
                            }
                        } else if (sellerMove.equals("Approve appointments")) {
                            String calendarName = brf.readLine();
                            String title = brf.readLine();
                            String customerFilename = brf.readLine();
                            Seller seller = new Seller(filename, sellerAppointmentList);
                            pw.println(seller.addToApproveList(calendarName, title, customerFilename,
                                    sellerAppointmentList));
                            pw.flush();
                        } else if (sellerMove.equals("View store's approved appointments")) {
                            String storeName = brf.readLine();
                            Seller seller = new Seller(filename, sellerAppointmentList);
                            pw.println(seller.viewApprovalList(storeName));
                            pw.flush();
                        } else if (sellerMove.equals("Create store")) {
                            String storeName = brf.readLine();
                            Seller seller = new Seller(filename, sellerAppointmentList);
                            pw.println(seller.createStore(storeName));
                            pw.flush();
                        }
                    } else {
                        pw.println("Incorrect");
                        pw.flush();
                    }
                }

            } else if (option.equals("Signup")) {
                Account account = new Account();
                String role = brf.readLine();

                if (role.equals("Customer")) {
                    String username = brf.readLine();
                    String password = brf.readLine();
                    filename = brf.readLine();
                    pw.println(account.createCustomerAccount(username, password, filename));
                    pw.flush();
                } else if (role.equals("Seller")) {
                    String username = brf.readLine();
                    String password = brf.readLine();
                    filename = brf.readLine();
                    sellerAppointmentList = brf.readLine();
                    pw.println(account.createSellerAccount(username, password, filename, sellerAppointmentList));
                    pw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
