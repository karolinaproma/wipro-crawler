package com.wiprodigital;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PageContentsPrinter {

    private static final String FILENAME = "page_contents.txt";

    public void printContents(PageContentsDto pageContentsDto){
        String contentToPrint = createContentToPrint(pageContentsDto);
        System.out.print(contentToPrint);
        writeToFile(contentToPrint);
    }

    private String createContentToPrint(PageContentsDto pageContentsDto){
        StringBuilder content = new StringBuilder();
        content.append("On page: ")
                .append(pageContentsDto.getPageUrl())
                .append(" there are URLs to child pages:")
                .append(System.getProperty("line.separator"));
        for(String childUrl : pageContentsDto.getChildUrls()){
            content.append("    child page: ")
                    .append(childUrl)
                    .append(System.getProperty("line.separator"));
        }
        content.append("And external links to:")
                .append(System.getProperty("line.separator"));
        for(String externalUrl : pageContentsDto.getExternalUrls()){
            content.append("    external link: ")
                    .append(externalUrl)
                    .append(System.getProperty("line.separator"));
        }
        content.append("And images:")
                .append(System.getProperty("line.separator"));
        for(String imageOnPage : pageContentsDto.getImagesOnPage()){
            content.append("    image: ")
                    .append(imageOnPage)
                    .append(System.getProperty("line.separator"));
        }
        return content.toString();
    }

    private void writeToFile(String content){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
