package com.wiprodigital;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class ImagesFinderTest {

    private ImagesFinder imagesFinder = new ImagesFinder();

    @Test
    public void shouldFind4Images() throws Exception {
        String testPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/testPageImages.html").toURI())));
        Document document = Jsoup.parse(testPage);

        Set<String> findedImages = imagesFinder.getImagesOnPage(document);

        assertThat(findedImages,hasSize(4));
    }

    @Test
    public void shouldGetEmptySet() throws Exception {
        String testPage = new String(Files.readAllBytes(Paths.get(getClass().getResource("/testPageEmpty.html").toURI())));
        Document document = Jsoup.parse(testPage);

        Set<String> findedImages = imagesFinder.getImagesOnPage(document);

        assertThat(findedImages,hasSize(0));
    }
}
