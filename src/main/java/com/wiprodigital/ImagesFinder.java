package com.wiprodigital;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagesFinder {

    public Set<String> getImagesOnPage(Document document){
        Set<String> imagesOnPage = new HashSet<>();
        Elements elementsWithImages = document.select("a[style]");
        Elements divElementsWithImages = document.select("div[style]");
        elementsWithImages.addAll(divElementsWithImages);

        for(Element element : elementsWithImages){
            String styleAttribute = element.attr("style");
            String imageJpg = findImageInString(styleAttribute, "(http.*?jpg)");
            if(!imageJpg.isEmpty()) imagesOnPage.add(imageJpg);
            String imagePng = findImageInString(styleAttribute, "(http.*?png)");
            if(!imagePng.isEmpty()) imagesOnPage.add(imagePng);
            String imageGif = findImageInString(styleAttribute, "(http.*?gif)");
            if(!imageGif.isEmpty()) imagesOnPage.add(imageGif);
        }

        Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        for(Element element : images){
            String srcAttribute = element.attr("src");
            if(!srcAttribute.isEmpty()) imagesOnPage.add(srcAttribute);
        }
        return imagesOnPage;
    }

    private String findImageInString(String stringToCheck, String regex){
        String findedImage = "";
        Matcher urlMatcher = Pattern.compile(regex).matcher(stringToCheck);
        while (urlMatcher.find()) {
            int matchStart = urlMatcher.start(1);
            int matchEnd = urlMatcher.end();
            findedImage = stringToCheck.substring(matchStart,matchEnd);
        }
        return findedImage;
    }
}
