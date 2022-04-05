package com.carbon.cartetresors.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessor {
    private String fileName;
    private List<String> lines;

    public FileProcessor(String input) {
        this.fileName=input;
        lines = new ArrayList<>();
    }

    public List<String> read(){

        try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lines = stream
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toList());
        }catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    public void print(){

    }

}
