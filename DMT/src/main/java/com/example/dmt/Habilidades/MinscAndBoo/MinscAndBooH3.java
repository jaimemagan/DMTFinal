package com.example.dmt.Habilidades.MinscAndBoo;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class MinscAndBooH3 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        for (Players opponent : players) {
            if (!opponent.equals(currentPlayer)) {
                int damage = 1;
                if (currentPlayer.getEscudos() > 0) {
                    damage += 1;
                }
                opponent.recibirAtaque(damage);
            }
        }
        System.out.println("Las cartas de escudo de Minsc and Boo causaron un daño adicional este turno.");
    }
}
