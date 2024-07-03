package com.example.dmt.Mediator;

import com.example.dmt.Player.*;
import java.util.HashMap;
import java.util.Map;

public class GameMediator {

    private Map<Player, Boolean> shieldControlMap = new HashMap<>();

    public static void attack(Players defender, int damage) {
        if (defender.hasShield()) {
            if (damage == defender.getEscudos()) {
                defender.setEscudos(0);
            } else if (damage > defender.getEscudos()) {
                int remainder = damage - defender.getEscudos();
                defender.setEscudos(0);
                defender.recibirAtaque(remainder);
            }
        } else {
            defender.recibirAtaque(damage);
        }
    }

    public void setShieldsControlledByPlayer(Player player, boolean control) {
        shieldControlMap.put(player, control);
    }

    public boolean isShieldControlledByPlayer(Player player) {
        return shieldControlMap.getOrDefault(player, false);
    }

    public static void redirectAttack(Player attacker, Player defender, int damage) {
        GameMediator mediator = new GameMediator();
        if (mediator.isShieldControlledByPlayer(defender)) {
            // Redirect attack to another player with a shield
            for (Players opponent : defender.getOponentes()) {
                if (opponent.hasShield()) {
                    attack(opponent, damage);
                    return;
                }
            }
        }
        attack(defender, damage);
    }
}
