package me.raveenaingale.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {

    public static void extract(String zipFilePath, String destinationPath) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(zipFilePath)))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryPath = destinationPath + File.separator + entry.getName();
                if (entry.isDirectory()) {
                    Files.createDirectories(Paths.get(entryPath));
                } else {
                    Files.createDirectories(Paths.get(entryPath).getParent());
                    Files.copy(zipInputStream, Paths.get(entryPath), StandardCopyOption.REPLACE_EXISTING);
                }
                zipInputStream.closeEntry();
            }
        }
    }
}
