package com.example.dmt.Habilidades.Lia;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;
import com.example.dmt.Cartas.Carta;

public class LiaH2 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        if (!currentPlayer.getDescartadas().isEmpty()) {
            Carta cartaRecuperada = currentPlayer.getDescartadas().remove(0);
            currentPlayer.getMano().add(cartaRecuperada);
            System.out.println("Lia recuperó la carta " + cartaRecuperada.getNombre() + " del montón de descartes.");
        }
    }
}
