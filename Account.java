import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
/**
 * Account class controls the system's log in, sign up, and account managements.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Account {
    ArrayList<String> sellers;
    ArrayList<String> customers;

    public Account() {
        this.customers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        try {
            File f = new File("CustomerAccount");
            FileReader fr = new FileReader(f);
            BufferedReader brf = new BufferedReader(fr);
            String customer = brf.readLine();
            while (customer != null) {
                customers.add(customer);
                customer = brf.readLine();
            }
            brf.close();
            File g = new File("SellerAccount");
            FileReader fr1 = new FileReader(g);
            BufferedReader brf1 = new BufferedReader(fr1);
            String seller = brf1.readLine();
            while (seller != null) {
                sellers.add(seller);
                seller = brf1.readLine();
            }
            brf1.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Please create two .txt files with names of " +
                    "'CustomerAccount' and 'SellerAccount' then start over", "Account storage file not found",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String createSellerAccount(String username, String password, String fileNameLocation,
                                    String approveListLocation) {
        boolean repeat = false;
        String output = "";
        for (int i = 0; i < sellers.size(); i++) {
            String[] parts = sellers.get(i).split(",");
            if (parts[0].equals(username)) {
                repeat = true;
                output = "You cannot use that username";
            }
        }
        if (!repeat) {
            try {
                File f = new File("SellerAccount");
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.printf("%s,%s,%s,%s\n", username, password, fileNameLocation, approveListLocation);
                pw.close();
                File g = new File(fileNameLocation);
                FileOutputStream fos1 = new FileOutputStream(g);
                PrintWriter pw1 = new PrintWriter(fos1);
                pw1.close();
                File h = new File(approveListLocation);
                FileOutputStream fos2 = new FileOutputStream(h);
                PrintWriter pw2 = new PrintWriter(fos2);
                pw2.close();
                output = "Your account is created";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public String createCustomerAccount(String username, String password, String fileNameLocation) throws IOException {
        boolean repeat = false;
        String output = "";
        for (int i = 0; i < customers.size(); i++) {
            String[] parts = customers.get(i).split(",");
            if (parts[0].equals(username)) {
                repeat = true;
                output = "You cannot use that username";
            }
        }

        if (!repeat) {
            try {
                File f = new File("CustomerAccount");
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.printf("%s,%s,%s\n", username, password, username);
                pw.close();
                File g = new File(fileNameLocation);
                FileOutputStream fos1 = new FileOutputStream(g);
                PrintWriter pw1 = new PrintWriter(fos1);
                pw1.close();
                output = "Your account is created";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public boolean checkSeller(String username, String password) {
        boolean correct = false;
        for (int i = 0; i < sellers.size(); i++) {
            String[] parts = sellers.get(i).split(",");
            String user = parts[0];
            String userPassword = parts[1];
            if (user.equals(username) && userPassword.equals(password)) {
                correct = true;
            }
        }
        return correct;
    }

    public boolean checkCustomer(String username, String password) {
        boolean correct = false;
        for (int i = 0; i < customers.size(); i++) {
            String[] parts = customers.get(i).split(",");
            String user = parts[0];
            String userPassword = parts[1];
            if (user.equals(username) && userPassword.equals(password)) {
                correct = true;
            }
        }
        return correct;
    }

    public void deleteSellerAccount(String username) {
        boolean found = false;
        int location = 0;
        for (int i = 0; i < sellers.size(); i++) {
            String[] parts = sellers.get(i).split(",");
            String user = parts[0];
            if (user.equals(username)) {
                found = true;
                location = i;
                String sellerFile = parts[2];
                String sellerAppointments = parts[3];
                File f = new File(sellerFile);
                f.delete();
                File f1 = new File(sellerAppointments);
                f1.delete();
            }
        }
        if (found) {
            this.sellers.remove(location);
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find your credentials", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteCustomerAccount(String username) {
        boolean found = false;
        for (int i = 0; i < customers.size(); i++) {
            String[] parts = customers.get(i).split(",");
            String user = parts[0];
            if (user.equals(username)) {
                found = true;
                this.customers.remove(i);
                String customerFile = parts[2];
                File f = new File(customerFile);
                f.delete();
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find your credentials", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String returnCustomerFilename(String username) {
        boolean found = false;
        String customerFilename = "";
        for (int i = 0; i < customers.size(); i++) {
            String[] parts = customers.get(i).split(",");
            String user = parts[0];
            if (user.equals(username)) {
                found = true;
                customerFilename = parts[2];
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find your file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return customerFilename;
    }

    public String returnSellerFilename(String username) {
        boolean found = false;
        String sellerFilename = "";
        for (int i = 0; i < sellers.size(); i++) {
            String[] parts = sellers.get(i).split(",");
            String user = parts[0];
            if (user.equals(username)) {
                found = true;
                sellerFilename = parts[2];
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find your file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return sellerFilename;
    }

    public String returnSellerAppointmentListName(String username) {
        boolean found = false;
        String sellerListName = "";
        for (int i = 0; i < sellers.size(); i++) {
            String[] parts = sellers.get(i).split(",");
            String user = parts[0];
            if (user.equals(username)) {
                found = true;
                sellerListName = parts[3];
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Cannot find your file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return sellerListName;
    }
}