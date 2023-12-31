package me.raveenaingale.helper;

import java.io.File;

public class DirectoryMaker {
    public static void createFolder( String path, String folderName) {
        // Combine path and folder name
        String fullPath = path + File.separator + folderName;

        // Create File object
        File folder = new File(fullPath);

        // Check if the folder doesn't exist and then create it
        if (!folder.exists()) {
            boolean success = folder.mkdirs();
            if (success) {
                System.out.println("Folder created successfully: " + fullPath);
            } else {
                System.err.println("Failed to create folder: " + fullPath);
            }
        } else {
            System.out.println("Folder already exists: " + fullPath);
        }
    }
}
