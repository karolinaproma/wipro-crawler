package com.wiprodigital;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PageContentsPrinterTest {

    private PageContentsPrinter contentsPrinter = new PageContentsPrinter();

    @Before
    public void executeBeforeTest() throws Exception{
        String basePath = getBasePath();
        String endPath = "/page_contents.txt";
        Files.deleteIfExists(Paths.get(basePath, endPath));
    }

    @Test
    public void fileWithContentShouldHaveSameText() throws Exception {

        PageContent pageContent = new PageContent("test.url");
        String expectedString = pageContent.toString();

        contentsPrinter.printContents(pageContent);

        String basePath = getBasePath();
        String endPath = "/page_contents.txt";

        Assert.assertTrue(Files.exists(Paths.get(basePath, endPath)));


        String actualString = new String(Files.readAllBytes(Paths.get(basePath, endPath)));

        Assert.assertEquals(expectedString, actualString);
    }

    private String getBasePath() throws Exception{
        Path basePath = Paths.get(this.getClass().getClassLoader().getResource(".").toURI()).getParent().getParent();
        return basePath.toString();
    }
}
