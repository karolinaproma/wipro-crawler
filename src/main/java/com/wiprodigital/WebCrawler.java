package com.wiprodigital;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebCrawler {

    private String baseUrl = "https://wiprodigital.com";

    private Set<String> visitedLinks = new HashSet<>();
    private Set<String> pagesToVisit = new CopyOnWriteArraySet<>();

    public void getPageContents(String URL) {
        Set<String> childPages = new HashSet<>();
        Set<String> externalUrls = new HashSet<>();

        if (!visitedLinks.contains(URL)) {

            try {
                visitedLinks.add(URL);
                Document document = Jsoup.connect(URL).get();

                Set<String> allLinksOnPage = getLinksOnPage(document);
                for(String link: allLinksOnPage) {
                    if(checkIfItIsNotExternalLink(link)){
                        addToPagesToVisit(link);
                        childPages.add(link);
                    } else {
                        externalUrls.add(link);
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
            System.out.println("On page: " + URL + " there are URLs to child pages:");
            for(String childUrl : childPages){
                System.out.println("    child page: " + childUrl);
            }
            System.out.println("And external links to:");
            for(String externalUrl : externalUrls){
                System.out.println("    external link: " + externalUrl);
            }
            System.out.println("end of list for this page");
        }
    }

    private boolean checkIfItIsNotExternalLink(String url){
        if(url.length() >= baseUrl.length()) {
            return url.substring(0, baseUrl.length()).equals(baseUrl);
        }
        return false;
    }

    private Set<String> getLinksOnPage(Document document){
        Set<String> allLinksOnPage = new HashSet<>();
        for(Element link: document.select("a[href]")){
            allLinksOnPage.add(link.attr("abs:href"));
        }
        for(Element link: document.select("input.linkURL")){
            allLinksOnPage.add(link.attr("abs:value"));
        }
        return allLinksOnPage;
    }

    private void addToPagesToVisit(String url){
        pagesToVisit.add(url);
    }

}
