package com.wiprodigital;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebCrawler {

    private String baseUrl = "https://wiprodigital.com";

    private Set<String> links = new HashSet<>();

    public void getPageContents(String URL) {
        if (!links.contains(URL)) {
            try {
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    if(checkIfItIsNotExternalLink(page)) {
                        getPageContents(page.attr("abs:href"));
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    private boolean checkIfItIsNotExternalLink(Element page){
        return false;
    }
}