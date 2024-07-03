package com.example.dmt.Habilidades.Blorp;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class BlorpH2 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        for (Players opponent : players) {
            if (!opponent.equals(currentPlayer) && opponent.getEscudos() > 0) {
                int escudos = opponent.getEscudos();
                opponent.setEscudos(0);
                currentPlayer.curarse(escudos);
                System.out.println("Blorp destruyó un escudo y se curó " + escudos + " puntos.");
                break;
            }
        }
    }
}
