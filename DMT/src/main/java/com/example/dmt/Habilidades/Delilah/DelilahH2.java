package com.example.dmt.Habilidades.Delilah;

import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Player.Player;
import com.example.dmt.Player.Players;
import java.util.List;

public class DelilahH2 implements Habilidad {

    @Override
    public void usarHabilidad(List<Players> opponents, Players currentPlayer) {
        if (currentPlayer instanceof Player) {
            ((Player) currentPlayer).getMediator().setShieldsControlledByPlayer((Player) currentPlayer, true);
            System.out.println("Delilah controlará los escudos hasta su próximo turno.");
        } else {
            System.out.println("La habilidad solo puede ser usada por un jugador.");
        }
    }
}
