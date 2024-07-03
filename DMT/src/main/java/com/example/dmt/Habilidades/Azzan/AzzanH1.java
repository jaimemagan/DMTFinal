package com.example.dmt.Habilidades.Azzan;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class AzzanH1 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> jugadores, Players currentPlayer) {
        int damage = 3;

        for (Players p : jugadores) {
            if (!p.equals(currentPlayer)) {
                p.recibirAtaque(damage);
            }
        }
        System.out.println("Todos los jugadores recibieron 3 puntos de daño.");
    }
}
