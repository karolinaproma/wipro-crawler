package com.wiprodigital;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PageContentsPrinter {

    private static final String FILENAME = "page_contents.txt";

    public void printContents(PageContent pageContent){
        String contentToPrint = pageContent.toString();
        System.out.print(contentToPrint);
        writeToFile(contentToPrint);
    }

    private void writeToFile(String content){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
