package com.example.dmt.Habilidades.Delilah;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;
import java.util.Scanner;

public class DelilahH1 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> opponents, Players currentPlayer) {
        Scanner scanner = new Scanner(System.in);

        for (Players opponent : opponents) {
            if (!opponent.equals(currentPlayer)) {
                System.out.println(opponent.getName() + ", ¿quieres alabar la grandeza de " + currentPlayer.getName() + " y darle un escudo? (yes/no)");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("yes")) {
                    currentPlayer.setEscudos(currentPlayer.getEscudos() + 1);
                    System.out.println(opponent.getName() + " alabó a Delilah y le dio un escudo.");
                }
            }
        }
    }
}
