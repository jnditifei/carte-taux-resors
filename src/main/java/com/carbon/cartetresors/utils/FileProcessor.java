package com.carbon.cartetresors.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessor {
    private List<String> lines;
    public FileWriter fileWriter;

    public FileProcessor() {
        lines = new ArrayList<>();
    }

    public List<String> read(String fileName){
        try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
            lines = stream
                    .filter(line -> !line.startsWith("#"))
                    .collect(Collectors.toList());
        }catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    public void write(List<String> lines , String path) throws IOException {
        try{
            File file = new File(path);
            fileWriter = new FileWriter(file);
            for(String line: lines){
                fileWriter.write(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            fileWriter.close();
        }
    }

}
