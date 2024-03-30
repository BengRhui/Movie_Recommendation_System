import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Admin {
    int adminID;
    String adminEmail, adminPassword;
    static ArrayList<Admin> overallAdmin = new ArrayList<>();
    public Admin(int adminID, String adminEmail, String adminPassword) {
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public static void readAdminFromFile() {
        overallAdmin.clear();
        try {
            BufferedReader read = new BufferedReader(new FileReader("textfile/adminAccount.txt"));
            String line;
            while ((line = read.readLine()) != null) {
                String[] arrayLine = line.split(";");
                Admin admin = new Admin(Integer.parseInt(arrayLine[0]), arrayLine[1], arrayLine[2]);
                overallAdmin.add(admin);
            }
            read.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in reading admin account file. Please inspect code.");
        }
    }

    public static void writeToAdminFile() {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("textfile/adminAccount.txt"));
            for (Admin admin: overallAdmin) {
                write.write(admin.adminID + ";" + admin.adminEmail + ";" + admin.adminPassword);
                write.newLine();
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to admin account file. Please inspect code.");
        }
    }

}
