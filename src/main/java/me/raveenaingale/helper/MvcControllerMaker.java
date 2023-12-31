package me.raveenaingale.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class MvcControllerMaker {

    public static void createMVCController(String controllerName, String projectName, String basePackage) {
        // Assuming ~/code exists, create controllers directory in ~/code/src/main/java/{basePackage}/
        String controllersPath = System.getenv("HOME") + "/code/" + projectName+"/src/main/java/" + basePackage.replace(".", "/") + "/controllers";
        String controllerFilePath = controllersPath + "/" + controllerName + "Controller.java";

        try {
            // Create the controllers directory if it doesn't exist
            Files.createDirectories(Path.of(controllersPath));

            // Create the controller file
            String controllerContent = generateControllerContent(controllerName, basePackage);
            Files.writeString(Path.of(controllerFilePath), controllerContent, StandardOpenOption.CREATE);

            System.out.println("MVC Controller created successfully at: " + controllerFilePath);
        } catch (IOException e) {
            System.err.println("Failed to create the MVC Controller: " + e.getMessage());
        }
    }
    private static String generateControllerContent(String controllerName, String basePackage) {
        String packageDeclaration = "package " + basePackage + ".controllers;";
        String imports = "\n\nimport org.springframework.stereotype.Controller;\nimport org.springframework.web.bind.annotation.GetMapping;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.ResponseBody;\n\n";
        String classDeclaration = "@Controller\n@RequestMapping(\"/" + controllerName.toLowerCase() + "\")\npublic class " + controllerName + "Controller {\n\n";
        String methodDeclaration = "\t@GetMapping\n\t@ResponseBody\n" +
                "\tpublic String " + controllerName.toLowerCase() + "() {\n" +
                "\t\treturn \"" + controllerName.toLowerCase() + "\";\n\t}\n";
        String closingBrace = "}\n";

        return packageDeclaration + imports + classDeclaration + methodDeclaration + closingBrace;
    }
}
