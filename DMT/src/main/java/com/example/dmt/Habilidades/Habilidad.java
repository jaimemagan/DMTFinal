package com.example.dmt.Habilidades;

import com.example.dmt.Player.Players;
import java.util.List;

public interface Habilidad {
    void usarHabilidad(List<Players> jugadores, Players currentPlayer);
}
