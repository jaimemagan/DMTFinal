package com.example.dmt.Habilidades.Blorp;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Players;
import java.util.List;

public class BlorpH3 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> players, Players currentPlayer) {
        currentPlayer.setAtaquesDobles(true);
        System.out.println("Blorp atacará dos veces cuando una carta sea destruida.");
    }
}
