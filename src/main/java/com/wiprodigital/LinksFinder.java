package com.wiprodigital;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Set;

public class LinksFinder {

    public Set<String> getLinksOnPage(Document document){
        Set<String> allLinksOnPage = new HashSet<>();
        for(Element link: document.select("a[href]")){
            allLinksOnPage.add(link.attr("abs:href"));
        }
        for(Element link: document.select("input.linkURL")){
            allLinksOnPage.add(link.attr("abs:value"));
        }
        return allLinksOnPage;
    }
}
