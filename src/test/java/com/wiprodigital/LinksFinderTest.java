package com.wiprodigital;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class LinksFinderTest {

    private LinksFinder linksFinder = new LinksFinder();

    @Test
    public void shouldFind4Links() throws Exception {
        String testPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/testPageLinks.html").toURI())));
        Document document = Jsoup.parse(testPage);

        Set<String> findedLinks = linksFinder.getLinksOnPage(document);

        Assert.assertEquals(4, findedLinks.size());
    }

    @Test
    public void shouldGetEmptySet() throws Exception {
        String testPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/testPageEmpty.html").toURI())));
        Document document = Jsoup.parse(testPage);

        Set<String> findedLinks = linksFinder.getLinksOnPage(document);

        Assert.assertEquals(0, findedLinks.size());
    }
}
