import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DatasetDownloadAndUpload {

    final static String filePath = "textfile/";
    final static String downloadPath = System.getProperty("user.home") + "/Downloads/";

    public static void downloadFiles(String fileName) {
        Path sourcePath = Paths.get(filePath + fileName);
        Path destinationPath = Paths.get(downloadPath + fileName);
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(AdminFrame.frame, fileName + " is not found. Please inspect code.", "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void uploadFiles(String fileName) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select your file to be uploaded");
            int choice = chooser.showOpenDialog(AdminFrame.frame);

            String sourcePath = null;
            if (choice == JFileChooser.APPROVE_OPTION) {
                sourcePath = chooser.getSelectedFile().getAbsolutePath();
            }

            if (sourcePath == null) {
                throw new IOException();
            }

            Path source = Path.of(sourcePath);
            Path destination = Path.of(filePath + fileName);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(AdminFrame.frame, fileName + " has been uploaded successfully.", "Upload Success", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("asset/Success.png"));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(AdminFrame.frame, "Error in uploading files. Please try again.", "Upload Error", JOptionPane.ERROR_MESSAGE);
        }


    }

}
