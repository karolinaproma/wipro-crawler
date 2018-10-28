package com.wiprodigital;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebCrawler {

    private String baseUrl = "https://wiprodigital.com";

    private Set<String> visitedLinks = new HashSet<>();
    private Set<String> pagesToVisit = new CopyOnWriteArraySet<>();

    private LinksFinder linksFinder = new LinksFinder();
    private ImagesFinder imagesFinder = new ImagesFinder();
    private PageContentsPrinter pageContentsPrinter = new PageContentsPrinter();

    private void getPageContents(String url) {
        PageContentsDto pageContentsDto = new PageContentsDto(url);

        if (!visitedLinks.contains(url)) {
            try {
                visitedLinks.add(url);
                Document document = Jsoup.connect(url).get();

                Set<String> allLinksOnPage = linksFinder.getLinksOnPage(document);

                for(String link: allLinksOnPage) {
                    if(checkIfItIsNotExternalLink(link)){
                        pagesToVisit.add(link);
                        pageContentsDto.getChildUrls().add(link);
                    } else {
                        pageContentsDto.getExternalUrls().add(link);
                    }
                }

                pageContentsDto.setImagesOnPage(imagesFinder.getImagesOnPage(document));

            } catch (IOException e) {
                System.err.println("For '" + url + "': " + e.getMessage());
            }
            pageContentsPrinter.printContents(pageContentsDto);
        }
    }

    private boolean checkIfItIsNotExternalLink(String url){
        if(url.length() >= baseUrl.length()) {
            return url.substring(0, baseUrl.length()).equals(baseUrl);
        }
        return false;
    }

    public void runSearchingForDefaultUrl(){
        getPageContents(baseUrl);
        for(String page : pagesToVisit){
            getPageContents(page);
        }
    }
}
