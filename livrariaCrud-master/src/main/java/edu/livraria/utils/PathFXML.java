package edu.livraria.utils;

import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return Paths.get("livrariaCrud-master/src/main/java/edu/livraria/view").toAbsolutePath().toString();
    }
}