package com.kodilla.tictactoe.fx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOX extends Application {

    private Button[][] buttons = new Button[3][3];
    private boolean isGameOver;
    private boolean vsComputer;
    private String currentPlayer = "X";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Computer", "Player vs Player");
        dialog.setTitle("Game choice");
        dialog.setContentText("Make your choice");

        dialog.showAndWait().ifPresent(choice -> {
            vsComputer = choice.equals("Computer");
        });

        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        optionsMenu.getItems().addAll(save, load);
        menuBar.getMenus().addAll(optionsMenu);

        save.setOnAction((e) -> {
            String gameState;
            gameState = currentPlayer;
            gameState = gameState + "\n" + vsComputer;

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    gameState = gameState + "\n" + buttons[i][j].getText();
                }
            }
            writeFile(gameState);
        });

        load.setOnAction((e) -> {
            List<String> strings = readFile();
            currentPlayer = strings.get(0);
            vsComputer = Boolean.getBoolean(strings.get(1));
            int a = 2;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    buttons[i][j].setText(strings.get(a++));
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: rgba(81,30,138,0.6)");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setStyle("-fx-background-color: rgba(162,228,95,0.58)");
                button.setMinSize(200, 200);
                button.setFont(Font.font(70));
                final int finalI = i;
                final int finalJ = j;
                buttons[finalI][finalJ] = button;
                button.setOnAction(e -> handleButtonClicked(e, finalI, finalJ));
                gridPane.add(button, finalI, finalJ);
            }
        }
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void handleButtonClicked(ActionEvent e, int i, int j) {
        if(buttons[i][j].getText().isEmpty()) {
            buttons[i][j].setText(currentPlayer);
            if (checkWin(currentPlayer)) {
                showWinAlert("Player " + currentPlayer + " won!", e);
                return;
            } else if (isDraw(buttons)) {
                showWinAlert("Draw!", e);
                return;
            }
            /*else if (isGameOver) {  //false at the beginning ---po co to jest???
                isGameOver = false;
                return;
            }*/
            if (vsComputer) {
                computerMove();
                if (checkWin("O")) {
                    showWinAlert("Player O won!", e);
                } else if (isDraw(buttons)) {
                    showWinAlert("Draw", e);
                }
                /*else if (isGameOver) {
                    isGameOver = false;
                    return;
                }*/
            }
            else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }
    }

    public boolean isDraw(Button[][] array) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (array[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        isGameOver = true;
        return true;
    }

    private void computerMove() {
        List<Button> availableButtons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[i][j].getText().isEmpty()) {
                    availableButtons.add(buttons[i][j]);
                }
            }
        }
        Random rand = new Random();
        int computerButtonIndex = rand.nextInt(availableButtons.size());
        availableButtons.get(computerButtonIndex).setText("O");
    }

    private boolean checkWin(String move) {
        boolean winner = isWinner(buttons, move);
        if (winner) {
            isGameOver = true;
            return true;
        }
        return false;
    }

    private boolean isWinner(Button[][] array, String c) {
        for(int i = 0; i < 3; i++) {
            if(array[i][0].getText().equals(c) && array[i][1].getText().equals(c) && array[i][2].getText().equals(c)) return true;
        }
        for(int j = 0; j < 3; j++) {
            if (array[0][j].getText().equals(c) && array[1][j].getText().equals(c) && array[2][j].getText().equals(c)) return true;
        }
        if (array[0][0].getText().equals(c) && array[1][1].getText().equals(c) && array[2][2].getText().equals(c)) return true;
        if (array[0][2].getText().equals(c) && array[1][1].getText().equals(c) && array[2][0].getText().equals(c)) return true;

        return false;
    }

    private void showWinAlert(String message, ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WIN");
        alert.setContentText(message);
        alert.getDialogPane().getButtonTypes().clear();
        alert.getDialogPane().getButtonTypes().setAll(
                ButtonType.CLOSE,
                ButtonType.OK
        );
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                resetGame();
            }
            if (buttonType == ButtonType.CLOSE) {
                Platform.exit();
            }
        });
    }

    private void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                currentPlayer = "X";
                isGameOver = false;
            }
        }
    }

    private void writeFile(String message) {
        Path path = Paths.get("src/main/resources/results");
        try (BufferedWriter writer = Files.newBufferedWriter (
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)) {

            writer.write(message);
            System.out.println("\nResult has been written to the results file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
    }

    public List<String> readFile() {
        Path path = Paths.get("src/main/resources/results");
        System.out.println("\nGame results from the file:\n ");
        try {
            return Files.lines(path).toList();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
            return List.of();
        }
    }

}