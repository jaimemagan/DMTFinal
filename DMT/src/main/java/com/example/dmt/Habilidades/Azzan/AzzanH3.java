package com.example.dmt.Habilidades.Azzan;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;
import java.util.Scanner;

public class AzzanH3 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el oponente con el que desea intercambiar puntos de vida:");
        int index = 1;
        for (Players opponent : players) {
            if (!opponent.equals(currentPlayer)) {
                System.out.println(index + ". " + opponent.getName() + " - Puntos de vida: " + opponent.getHealth());
                index++;
            }
        }

        int selectedIndex = scanner.nextInt() - 1;

        Players selectedOpponent = null;
        index = 0;
        for (Players opponent : players) {
            if (!opponent.equals(currentPlayer)) {
                if (index == selectedIndex) {
                    selectedOpponent = opponent;
                    break;
                }
                index++;
            }
        }

        if (selectedOpponent != null) {
            int tempHealth = currentPlayer.getHealth();
            currentPlayer.setHealth(selectedOpponent.getHealth());
            selectedOpponent.setHealth(tempHealth);

        } else {
            System.out.println("Oponente no válido seleccionado.");
        }
    }
}
