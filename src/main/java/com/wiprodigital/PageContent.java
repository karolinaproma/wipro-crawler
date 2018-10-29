package com.wiprodigital;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PageContent {
    private final String pageUrl;
    private boolean visited = false;
    private Set<PageContent> childUrls = new HashSet<>();
    private Set<String> externalUrls = new HashSet<>();
    private Set<String> imagesOnPage = new HashSet<>();

    public PageContent(String url){
        this.pageUrl = url;
    }

    public void addChildPage(PageContent childPage){
        childUrls.add(childPage);
    }

    public void addExternalUrl(String externalUrl){
        externalUrls.add(externalUrl);
    }

    public String toString(){
        StringBuilder content = new StringBuilder();
        content.append("On page: ")
                .append(pageUrl)
                .append(" there are URLs to child pages:")
                .append(System.getProperty("line.separator"));
        for(PageContent childUrl : childUrls){
            content.append("    child page: ")
                    .append(childUrl.getPageUrl())
                    .append(System.getProperty("line.separator"));
        }
        content.append("And external links to:")
                .append(System.getProperty("line.separator"));
        for(String externalUrl : externalUrls){
            content.append("    external link: ")
                    .append(externalUrl)
                    .append(System.getProperty("line.separator"));
        }
        content.append("And images:")
                .append(System.getProperty("line.separator"));
        for(String imageOnPage : imagesOnPage){
            content.append("    image: ")
                    .append(imageOnPage)
                    .append(System.getProperty("line.separator"));
        }
        return content.toString();
    }
}