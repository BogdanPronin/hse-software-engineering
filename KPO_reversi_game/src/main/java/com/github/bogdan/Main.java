package com.github.bogdan;


import com.github.bogdan.models.*;

import java.util.Scanner;



public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static final String message = "Игра возможна в следующих режимах:\n" +
            "/s - легкий режим против бота\n" +
            "/vs - режим игрок против игрока\n" +
            "/exit - выход\n" +
            "В игре присутствует визуализация вохможных ходов противника:\n" +
            "◘ - место, куда можно поставить черную фишку\n" +
            "◉ - место, куда можно поставить белую фишку\n" +
            "◐ - место, куда можно поставить и белую, и черную фишку";

    public static final String bot = "/s";

    public static final String versus = "/vs";

    public static final String exit = "/exit";

    public static void main(String[] args) {
        GameLauncher gameLauncher = new GameLauncher();
        System.out.println(message);
        while (true){
            System.out.println("Выберите режим:");
            String line = scanner.nextLine();
            if(line.equals(bot)){
                gameLauncher.startGameWithBot();
            } else if(line.equals(versus)){
                gameLauncher.startGameWithPerson();
            } else if(line.equals(exit)){
                return;
            }
        }
    }
}
