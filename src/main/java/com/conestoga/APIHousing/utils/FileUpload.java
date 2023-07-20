package com.conestoga.APIHousing.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class FileUpload {

    // Method that takes in a base64 string and converts it to a file and returns the relative file path
    public static String convertBase64ToFile(String base64String) throws IOException {
     final String publicFolder = "uploads";
        //if the base64 string is a url path like upload, reutrn the url
        if(base64String.contains("uploads/")){
            return base64String;
        }

        // Decode the base64 string to bytes
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64String);
        final String staticFolder = "static/"+ publicFolder;


        // Define the directory where you want to save the file (using the user's home directory)
            String directory = staticFolder;
            File uploadDir = new File(directory);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        // Create the file with the given file name in the upload directory
        String fileName = generateRandomFileName();
        File file = new File(uploadDir, fileName);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            // Write the bytes to the file
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to save the file: " + fileName, e);
        }

         final String finalFile= publicFolder + File.separator + fileName;
        System.out.println("File saved as :"+finalFile);
        return finalFile;
    }

    private static String generateRandomFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "") + ".png";
    }


}
