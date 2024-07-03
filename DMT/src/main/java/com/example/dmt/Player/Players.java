package com.example.dmt.Player;

import com.example.dmt.Cartas.Carta;
import java.util.List;

public interface Players {
    String getName();
    void setName(String name);
    int getHealth();
    void setHealth(int health);
    Personaje getPersonaje();
    void setPersonaje(Personaje personaje);
    List<Carta> getMazo();
    void setMazo(List<Carta> mazo);
    List<Carta> getMano();
    void setMano(List<Carta> mano);
    List<Carta> getCartasActivas();
    void setCartasActivas(List<Carta> cartasActivas);
    List<Carta> getDescartadas();
    void setDescartadas(List<Carta> descartadas);
    int getEscudos();
    void setEscudos(int escudos);
    int getTurnos();
    void agregarTurnos(int turnos);
    void setTurnos(int turnos);
    void tomarCarta();
    void tomarCarta(int cartasExtra);
    boolean hasShield();
    void recibirAtaque(int damage);
    void curarse(int corazones);
    void jugarCarta(Carta carta);
    void actualizar();
    void mezclarMazo();
    void takeTurn();
    void setIgnorarEscudos(boolean ignorarEscudos);
    void setAtaquesDobles(boolean ataquesDobles);
    List<Players> getOponentes();
    void setOponentes(List<Players> players);
}
