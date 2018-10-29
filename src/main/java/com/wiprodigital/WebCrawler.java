package com.wiprodigital;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebCrawler {

    private static final String BASE_URL = "https://wiprodigital.com";

    private Set<PageContent> pagesToVisit = new CopyOnWriteArraySet<>();

    private LinksFinder linksFinder = new LinksFinder();
    private ImagesFinder imagesFinder = new ImagesFinder();
    private PageContentsPrinter pageContentsPrinter = new PageContentsPrinter();

    public void runSearchingForDefaultUrl(){
        PageContent basePage = new PageContent(BASE_URL);
        pagesToVisit.add(basePage);
        getPageContents(basePage);
        for(PageContent page : pagesToVisit){
            getPageContents(page);
        }
        printResults();
    }

    private void getPageContents(PageContent pageContent) {
        if (!pageContent.isVisited()) {
            try {
                pageContent.setVisited(true);
                Document document = Jsoup.connect(pageContent.getPageUrl()).get();

                Set<String> allLinksOnPage = linksFinder.getLinksOnPage(document);

                for(String link: allLinksOnPage) {
                    if(checkIfItIsNotExternalLink(link)){
                        PageContent childPage = getChildPageDto(link);
                        pagesToVisit.add(childPage);
                        pageContent.addChildPage(childPage);
                    } else {
                        pageContent.addExternalUrl(link);
                    }
                }

                pageContent.setImagesOnPage(imagesFinder.getImagesOnPage(document));

            } catch (IOException e) {
                System.err.println("For '" + pageContent.getPageUrl() + "': " + e.getMessage());
            }
        }
    }

    private boolean checkIfItIsNotExternalLink(String url){
        if(url.length() >= BASE_URL.length()) {
            return url.substring(0, BASE_URL.length()).equals(BASE_URL);
        }
        return false;
    }

    private PageContent getChildPageDto(String link){
        for (PageContent dto : pagesToVisit){
            if(dto.getPageUrl().equals(link)){
                return dto;
            }
        }
        return new PageContent(link);
    }

    private void printResults(){
        for(PageContent page : pagesToVisit) {
            pageContentsPrinter.printContents(page);
        }
    }
}