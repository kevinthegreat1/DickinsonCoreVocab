package com.kevinthegreat.dickinsoncorevocab;

import javafx.application.Application;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DickinsonCoreVocab extends Application {
    private WebDriver driver;
    private DickinsonCoreVocabPage vocabPage;
    private Stage stage;
    private FlashCardScene[] flashCardScenes;

    @Override
    public void init() throws Exception {
        super.init();
        scrapeWords();
    }

    private void scrapeWords() {
        driver = new ChromeDriver();
        driver.get("https://dcc.dickinson.edu/latin-core-list1?field_search_lemma_value=&field_part_of_speech_value=All&field_semantic_group_value=All&order=field_frequency_rank&sort=asc");
        vocabPage = new DickinsonCoreVocabPage(driver);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        flashCardScenes = new FlashCardScene[vocabPage.vocabTable.size()];
        setFlashCardScene(0);
        stage.setWidth(512);
        stage.setHeight(512);
        stage.show();
    }

    public void setFlashCardScene(int index) {
        while (index < 0) {
            index += vocabPage.vocabTable.size();
        }
        index %= vocabPage.vocabTable.size();
        if (flashCardScenes[index] == null) {
            createFlashCardScene(index);
        }
        double width = stage.getWidth();
        double height = stage.getHeight();
        stage.setScene(flashCardScenes[index].getScene());
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    private void createFlashCardScene(int index) {
        flashCardScenes[index] = new FlashCardScene(this, index, vocabPage.vocabTable.get(index));
    }

    @Override
    public void stop() throws Exception {
        driver.quit();
        super.stop();
    }
}
