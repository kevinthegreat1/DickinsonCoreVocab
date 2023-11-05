package com.kevinthegreat.dickinsoncorevocab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DickinsonCoreVocabPage {
    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> vocabTable;

    public DickinsonCoreVocabPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
