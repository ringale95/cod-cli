package me.raveenaingale.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SpringBootProjectMaker {

    public static void createSpringBootProject(String projectName, String groupId, String description) {
        // Assuming ~/code exists, create project directory in ~/code
        String projectPath = System.getenv("HOME") + "/code/";

        String initializrJarUrl = "https://start.spring.io/starter.zip?" +
                "type=maven-project&" +
                "language=java&" +
                "bootVersion=3.2.1&" +
                "baseDir=" + projectName + "&" +
                "groupId=" + groupId +
                "&artifactId=" + projectName + "&" +
                "name=" + projectName +"&" +
                "description=" + description +
                "&packageName=" + groupId + "." + projectName + "&" +
                "packaging=jar" +
                "&javaVersion=17"+
                "&dependencies=web,lombok";

        try {
            // Create the project directory
            Files.createDirectories(Path.of(projectPath));

            // Download the Spring Initializr JAR file
            String initializrJarPath = projectPath + "/initializr.zip";
            Files.copy(new java.net.URL(initializrJarUrl).openStream(), Path.of(initializrJarPath), StandardCopyOption.REPLACE_EXISTING);

            // Unzip the downloaded file
            ZipExtractor.extract(initializrJarPath, projectPath);

            // Remove the downloaded zip file
            Files.deleteIfExists(Path.of(initializrJarPath));

            System.out.println("Spring Boot project created successfully at: " + projectPath);
        } catch (IOException e) {
            System.err.println("Failed to create the Spring Boot project: " + e.getMessage());
        }
    }

}
