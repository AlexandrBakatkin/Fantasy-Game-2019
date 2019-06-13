package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private int teamLeftCount = 0;
    private int teamRightCount = 0;
    private ArrayList<Hero> teamLeft = new ArrayList<>();
    private ArrayList<String> teamLeftName = new ArrayList<>();
    private ArrayList<Hero> teamRight = new ArrayList<>();
    private ArrayList<String> teamRightName = new ArrayList<>();
    private ArrayList<Hero> personsArray = new ArrayList<>();

    @FXML
    ComboBox<String> comboBoxLeftTeam;

    @FXML
    ComboBox<String> comboBoxRightTeam;

    @FXML
    TextArea leftTeam;

    @FXML
    TextArea rightTeam;

    @FXML
    TextArea battleArea;

    public void choosePersonLeft(ActionEvent actionEvent) {
        if(teamLeftCount <= 2){

            if(comboBoxLeftTeam.getValue() != null){
                teamLeftName.add(comboBoxLeftTeam.getValue());
                leftTeam.appendText(comboBoxLeftTeam.getValue() + "\n");
            }
            comboBoxRightTeam.getItems().remove(comboBoxLeftTeam.getValue());
            comboBoxLeftTeam.getItems().remove(comboBoxLeftTeam.getValue());
            teamLeftCount++;
            if(teamLeftCount > 2){
                leftTeam.appendText("Команда готова!");
            }
        }
    }

    public void choosePersonRight(ActionEvent actionEvent) {
        if(teamRightCount <= 2){

            if(comboBoxRightTeam.getValue() != null){
                teamRightName.add(comboBoxRightTeam.getValue());
                rightTeam.appendText(comboBoxRightTeam.getValue() + "\n");
            }
            comboBoxLeftTeam.getItems().remove(comboBoxRightTeam.getValue());
            comboBoxRightTeam.getItems().remove(comboBoxRightTeam.getValue());
            teamRightCount++;
            if(teamRightCount > 2){
                rightTeam.appendText("Команда готова!");
            }
        }
    }

    public void Game(){

        leftTeam.clear();
        rightTeam.clear();

        Random randomStep = new Random();
        Random randomIndex = new Random();
        Random randomHealing = new Random();


        personsArray.add(new Warrior(250, "Тигрил", 60, 0));
        personsArray.add(new Assasin(150, "Акали", 100, 0));
        personsArray.add(new Doctor(120, "Жанна", 0, 60));
        personsArray.add(new Warrior(290, "Минотавр", 50, 0));
        personsArray.add(new Assasin(160, "Джинкс", 100, 0));
        personsArray.add(new Doctor(110, "Зои", 0, 80));

        completeTeam(personsArray, teamLeftName, teamLeft);

        completeTeam(personsArray, teamRightName, teamRight);

        int team1Count = teamLeft.size();
        int team2Count = teamRight.size();
        int roundCount = 1;
        while (team1Count != 0 && team2Count != 0){
            System.out.println("Раунд " + roundCount + " :");
            roundCount++;

            if (randomStep.nextInt(2) == 0) {
                team2Count = doHitOrHealing(randomIndex, randomHealing, (ArrayList<Hero>) teamLeft, (ArrayList<Hero>) teamRight, team2Count);
                team1Count = doHitOrHealing(randomIndex, randomHealing, (ArrayList<Hero>) teamRight, (ArrayList<Hero>) teamLeft, team1Count);
            } else {
                team1Count = doHitOrHealing(randomIndex, randomHealing, (ArrayList<Hero>) teamRight, (ArrayList<Hero>) teamLeft, team1Count);
                team2Count = doHitOrHealing(randomIndex, randomHealing, (ArrayList<Hero>) teamLeft, (ArrayList<Hero>) teamRight, team2Count);
            }
        }
        System.out.println("---------------");

        leftTeam.appendText("\n" + "Результаты боя: " + "\n");
        if(teamLeft.isEmpty()){
            battleArea.appendText("\n" + "Команда №1 проиграла");
        }
        for (Hero t1: teamLeft) {
            leftTeam.appendText(t1.info() + "\n");
        }
        System.out.println();

        rightTeam.appendText("\n" + "Результаты боя: " + "\n");
        if(teamRight.isEmpty()){
            battleArea.appendText("\n" + "Команда №2 проиграла");
        }
        for (Hero t2: teamRight) {
            rightTeam.appendText(t2.info());
        }


        teamLeft.clear();
        teamRight.clear();
        teamLeftName.clear();
        teamRightName.clear();
        teamLeftCount = 0;
        teamRightCount = 0;

        for (Hero o: personsArray
             ) {
            comboBoxLeftTeam.getItems().add(o.name);
            comboBoxRightTeam.getItems().add(o.name);
        }

        personsArray.clear();
    }

    private static void completeTeam(ArrayList<Hero> personsArray, ArrayList<String> teamName, ArrayList<Hero> team) {
        for (Hero persons: personsArray
             ) {
            if (teamName.contains(persons.name)){
                team.add(persons);
            }
        }
    }

    private static int doHitOrHealing(Random randomIndex, Random randomHealing, ArrayList<Hero> team1, ArrayList<Hero> team2, int team2Count) {
        for (int i = 0; i < team1.size(); i++) {
            // ДОКТОР
            if (team1.get(i) instanceof Doctor) {
                team1.get(i).healing(team1.get(randomHealing.nextInt(2)));
            } else {
                if (team2.size() == 0) {
                    break;
                }
                // БОЕЦ
                int index = randomIndex.nextInt(team2.size());
                team1.get(i).hit(team2.get(index));
                if (team2.get(index).health <= 0) {
                    team2.remove(index);
                    team2Count--;
                }
            }
        }
        return team2Count;
    }
}