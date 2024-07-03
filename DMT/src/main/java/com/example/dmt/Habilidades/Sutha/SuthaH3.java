package com.example.dmt.Habilidades.Sutha;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class SuthaH3 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        int totalCuracion = 0;
        for (Players opponent : players) {
            if (!opponent.equals(currentPlayer)) {
                currentPlayer.curarse(1);
                opponent.recibirAtaque(1);
                totalCuracion++;
            }
        }
        System.out.println("Sutha se curó " + totalCuracion + " veces y atacó a cada oponente.");
    }
}
