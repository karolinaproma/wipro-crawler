package com.wiprodigital;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PageContentsDto {
    private final String pageUrl;
    private Set<String> childUrls = new HashSet<>();
    private Set<String> externalUrls = new HashSet<>();
    private Set<String> imagesOnPage = new HashSet<>();

    public PageContentsDto(String url){
        this.pageUrl = url;
    }
}
