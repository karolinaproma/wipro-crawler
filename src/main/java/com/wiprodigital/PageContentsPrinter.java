package com.wiprodigital;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PageContentsPrinter {

    private static final String FILE_NAME = "page_contents.txt";

    public void printContents(PageContent pageContent){
        String contentToPrint = pageContent.toString();
        System.out.print(contentToPrint);
        writeToFile(contentToPrint);
    }

    private void writeToFile(String content){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME,true))){
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
