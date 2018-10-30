package com.wiprodigital;

import static org.hamcrest.io.FileMatchers.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PageContentsPrinterTest {

    private static final String FILE_PATH = "/page_contents.txt";

    private PageContentsPrinter contentsPrinter = new PageContentsPrinter();

    @Before
    public void executeBeforeTest() throws Exception{
        String basePath = getBasePath();
        Files.deleteIfExists(Paths.get(basePath, FILE_PATH));
    }

    @Test
    public void fileWithContentShouldHaveSameText() throws Exception {

        PageContent pageContent = new PageContent("test.url");
        String expectedString = pageContent.toString();

        contentsPrinter.printContents(pageContent);

        String basePath = getBasePath();
        File pageContentsFile = new File(basePath,FILE_PATH);

        Assert.assertThat(pageContentsFile, anExistingFile());

        String actualString = new String(Files.readAllBytes(Paths.get(basePath, FILE_PATH)));

        Assert.assertEquals(expectedString, actualString);
    }

    private String getBasePath() throws Exception{
        Path basePath = Paths.get(this.getClass().getClassLoader().getResource(".").toURI()).getParent().getParent();
        return basePath.toString();
    }
}
