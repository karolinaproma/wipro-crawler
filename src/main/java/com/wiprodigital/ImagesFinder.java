package com.wiprodigital;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toSet;

public class ImagesFinder {

    private static final String REGEX_FOR_IMAGES = "(http.*?(jpg|png|gif|bmp|img|jpeg|ico))";

    public Set<String> getImagesOnPage(Document document){
        Set<String> imagesOnPage;
        Elements elementsWithImages = document.select("a[style]");
        Elements divElementsWithImages = document.select("div[style]");
        elementsWithImages.addAll(divElementsWithImages);
        imagesOnPage = elementsWithImages.stream()
                .map(e -> findImageInString(e.attr("style")))
                .filter(i -> !i.isEmpty())
                .collect(toSet());

        Elements imagesFromSrc = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        imagesOnPage.addAll(imagesFromSrc.stream()
                .map(e -> e.attr("src"))
                .filter(a -> !a.isEmpty())
                .collect(toSet()));

        return imagesOnPage;
    }

    private static String findImageInString(String stringToCheck){
        String findedImage = "";
        Matcher imagesUrlMatcher = Pattern.compile(REGEX_FOR_IMAGES).matcher(stringToCheck);
        if (imagesUrlMatcher.find()) {
            int matchStart = imagesUrlMatcher.start(1);
            int matchEnd = imagesUrlMatcher.end();
            findedImage = stringToCheck.substring(matchStart,matchEnd);
        }
        return findedImage;
    }
}
