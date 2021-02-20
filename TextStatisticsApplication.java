package textstatistics;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class TextStatisticsApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane layout = new BorderPane();

        TextArea textArea = new TextArea();

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label letterLabel = new Label("Letters: 0");
        Label wordLabel = new Label("Words: 0");
        Label longWordLabel = new Label("The longest word is: ");

        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int totalCharacters = t1.length();
                String withoutPunct = t1.replaceAll("\\p{Punct}", "");
                String[] words = withoutPunct.split(" ");
                long numOfWords = Arrays.stream(words)
                        .filter(word -> !word.isEmpty())
                        .count();
                String longest = Arrays.stream(words)
                        .sorted((s1, s2) -> s2.length() - s1.length())
                        .findFirst()
                        .get();

                letterLabel.setText("Letters: " + totalCharacters);
                wordLabel.setText(("Words: " + numOfWords));
                longWordLabel.setText(("The longest word is: " + longest));
            }
        });

        hbox.getChildren().add(letterLabel);
        hbox.getChildren().add(wordLabel);
        hbox.getChildren().add(longWordLabel);

        layout.setCenter(textArea);
        layout.setBottom(hbox);

        Scene view = new Scene(layout);
        stage.setTitle("Text Statistics");
        stage.setScene(view);
        stage.show();
    }
}
