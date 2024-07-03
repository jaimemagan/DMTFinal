package com.example.dmt;

import com.example.dmt.Mediator.*;
import com.example.dmt.Player.*;
import com.example.dmt.Tablero.*;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private HBox player1Hand, player2Hand, player3Hand, player4Hand;

    @FXML
    private VBox characterSelection;

    @FXML
    private Button drawCardButton, playCardButton;

    private CardLoader cardLoader;
    private String selectedCharacter;
    private List<String> cpuCharacters;
    private int currentPlayerTurn;

    public HelloController() {
        cardLoader = new CardLoader();
        cpuCharacters = new ArrayList<>();
        currentPlayerTurn = 0; // Start with the human player
    }

    @FXML
    public void initialize() {
        setupCPUCharacters();
        characterSelection.setVisible(true);
        playCardButton.setDisable(true); // Disable play card button until character is selected
    }

    @FXML
    public void selectCharacter(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        selectedCharacter = button.getText();
        characterSelection.setVisible(false);
        updateHand(player1Hand, selectedCharacter);
        playCardButton.setDisable(false); // Enable play card button
    }

    private void setupCPUCharacters() {
        String[] characters = {"Azzan", "Blorp", "Delilah", "Lia", "Minsc and Boo", "Sutha"};
        Random rand = new Random();
        while (cpuCharacters.size() < 3) {
            String character = characters[rand.nextInt(characters.length)];
            if (!cpuCharacters.contains(character) && !character.equals(selectedCharacter)) {
                cpuCharacters.add(character);
            }
        }

        updateHand(player2Hand, cpuCharacters.get(0));
        updateHand(player3Hand, cpuCharacters.get(1));
        updateHand(player4Hand, cpuCharacters.get(2));
    }

    private void updateHand(HBox handContainer, String character) {
        List<String> folderPaths = new ArrayList<>();
        folderPaths.add("src/main/resources/ImagenesProyecto/" + character);
        List<Image> cardImages = cardLoader.loadCardImages(folderPaths);
        handContainer.getChildren().clear();

        for (int i = 0; i < 3 && i < cardImages.size(); i++) {
            ImageView imageView = new ImageView(cardImages.get(i));
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            handContainer.getChildren().add(imageView);
        }
    }

    @FXML
    public void drawCard() {
        if (currentPlayerTurn == 0) {
            List<String> folderPaths = new ArrayList<>();
            folderPaths.add("src/main/resources/ImagenesProyecto/" + selectedCharacter);
            List<Image> cardImages = cardLoader.loadCardImages(folderPaths);

            if (player1Hand.getChildren().size() < 5 && cardImages.size() > player1Hand.getChildren().size()) {
                ImageView imageView = new ImageView(cardImages.get(player1Hand.getChildren().size()));
                imageView.setFitHeight(150);
                imageView.setFitWidth(100);
                player1Hand.getChildren().add(imageView);
            }
        }
    }

    @FXML
    public void playCard() {
        if (currentPlayerTurn == 0 && !player1Hand.getChildren().isEmpty()) {
            player1Hand.getChildren().remove(0);
            endTurn();
        }
    }

    private void endTurn() {
        currentPlayerTurn = (currentPlayerTurn + 1) % 4;
        if (currentPlayerTurn != 0) {
            playCPUTurn();
        } else {
            drawCardButton.setDisable(false);
            playCardButton.setDisable(false);
        }
    }

    private void playCPUTurn() {
        drawCardButton.setDisable(true);
        playCardButton.setDisable(true);

        Platform.runLater(() -> {
            HBox currentCPUHand = getCurrentCPUHand();
            if (!currentCPUHand.getChildren().isEmpty()) {
                currentCPUHand.getChildren().remove(0);
            }

            // Check if the CPU player needs more cards
            if (currentCPUHand.getChildren().isEmpty()) {
                drawCardsForCPU(currentCPUHand, cpuCharacters.get(currentPlayerTurn - 1));
            }

            if (currentPlayerTurn != 0) {
                endTurn();
            }
        });
    }

    private void drawCardsForCPU(HBox handContainer, String character) {
        List<String> folderPaths = new ArrayList<>();
        folderPaths.add("src/main/resources/ImagenesProyecto/" + character);
        List<Image> cardImages = cardLoader.loadCardImages(folderPaths);

        for (int i = 0; i < 2 && i < cardImages.size(); i++) {
            ImageView imageView = new ImageView(cardImages.get(i));
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            handContainer.getChildren().add(imageView);
        }
    }

    private HBox getCurrentCPUHand() {
        switch (currentPlayerTurn) {
            case 1:
                return player2Hand;
            case 2:
                return player3Hand;
            case 3:
                return player4Hand;
            default:
                return player1Hand;
        }
    }
}
