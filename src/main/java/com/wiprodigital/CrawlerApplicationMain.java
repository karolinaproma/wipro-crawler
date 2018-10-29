package com.wiprodigital;

public class CrawlerApplicationMain {

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        crawler.runSearchingForDefaultUrl();
    }
}