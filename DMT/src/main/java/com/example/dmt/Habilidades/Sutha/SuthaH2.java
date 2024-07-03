package com.example.dmt.Habilidades.Sutha;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class SuthaH2 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        for (Players player : players) {
            player.getMano().clear();
            player.tomarCarta(3);
        }
        System.out.println("Sutha hizo que todos los jugadores descartaran sus manos y robaran tres cartas.");
    }
}
