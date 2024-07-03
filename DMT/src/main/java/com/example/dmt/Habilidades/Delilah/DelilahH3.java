package com.example.dmt.Habilidades.Delilah;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class DelilahH3 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> opponents, Players currentPlayer) {
        for (Players opponent : opponents) {
            if (!opponent.equals(currentPlayer) && opponent.getMano().isEmpty()) {
                opponent.setEscudos(opponent.getEscudos() + 1);
                System.out.println(opponent.getName() + " recibió un escudo porque no tiene cartas en juego.");
            }
        }

        for (Players player : opponents) {
            player.getCartasActivas().clear();
        }
        currentPlayer.getCartasActivas().clear();
        System.out.println("Delilah destruyó todas las cartas activas, incluyendo las suyas.");
    }
}
