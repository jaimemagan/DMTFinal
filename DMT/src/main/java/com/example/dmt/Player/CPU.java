package com.example.dmt.Player;

import com.example.dmt.Cartas.Carta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CPU implements Players {
    private String name;
    private Personaje personaje;
    private int health;
    private int escudos;
    private int turnos;
    private List<Carta> mazo;
    private List<Carta> mano;
    private List<Carta> cartasActivas;
    private List<Carta> descartadas;
    private List<Players> oponentes;
    private boolean ignorarEscudos;
    private boolean ataquesDobles;

    public CPU(String name, Personaje personaje) {
        this.name = name;
        this.personaje = personaje;
        this.health = 10;
        this.mazo = personaje.getMazo();
        this.mano = new ArrayList<>();
        this.cartasActivas = new ArrayList<>();
        this.descartadas = new ArrayList<>();
        this.oponentes = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public Personaje getPersonaje() {
        return personaje;
    }

    @Override
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override
    public List<Carta> getMazo() {
        return mazo;
    }

    @Override
    public void setMazo(List<Carta> mazo) {
        this.mazo = mazo;
    }

    @Override
    public List<Carta> getMano() {
        return mano;
    }

    @Override
    public void setMano(List<Carta> mano) {
        this.mano = mano;
    }

    @Override
    public List<Carta> getCartasActivas() {
        return cartasActivas;
    }

    @Override
    public void setCartasActivas(List<Carta> cartasActivas) {
        this.cartasActivas = cartasActivas;
    }

    @Override
    public List<Carta> getDescartadas() {
        return descartadas;
    }

    @Override
    public void setDescartadas(List<Carta> descartadas) {
        this.descartadas = descartadas;
    }

    @Override
    public int getEscudos() {
        return escudos;
    }

    @Override
    public void setEscudos(int escudos) {
        this.escudos = escudos;
    }

    @Override
    public int getTurnos() {
        return turnos;
    }

    @Override
    public void agregarTurnos(int turnos) {
        this.turnos += turnos;
    }

    @Override
    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public void tomarCarta() {
        if (!mazo.isEmpty()) {
            mano.add(mazo.remove(0));
        }
    }

    @Override
    public void tomarCarta(int cartasExtra) {
        for (int i = 0; i < cartasExtra; i++) {
            tomarCarta();
        }
    }

    @Override
    public boolean hasShield() {
        return escudos > 0;
    }

    @Override
    public void recibirAtaque(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    @Override
    public void curarse(int corazones) {
        this.health += corazones;
    }

    @Override
    public void setOponentes(List<Players> players) {
        for (Players p : players) {
            if (!p.getName().equals(this.name)) {
                this.oponentes.add(p);
            }
        }
    }

    @Override
    public List<Players> getOponentes() {
        return oponentes;
    }

    @Override
    public void mezclarMazo() {
        Collections.shuffle(mazo);
    }

    @Override
    public void jugarCarta(Carta carta) {
        if (carta.getEspadas() > 0) {
            carta.ataqueCPU(oponentes);
        }
        if (carta.getEscudos() > 0) {
            escudos += carta.getEscudos();
        }
        if (carta.getCartasExtra() > 0) {
            tomarCarta(carta.getCartasExtra());
        }
        if (carta.getRayos() > 0) {
            turnos += carta.getRayos();
        }
        if (carta.getCorazones() > 0) {
            curarse(carta.getCorazones());
        }
        turnos--;
    }

    @Override
    public void actualizar() {
        if (mano.isEmpty()) {
            tomarCarta(2);
        }
    }

    @Override
    public void takeTurn() {
        int index = (int) (Math.random() * mano.size());
        Carta randomElement = mano.get(index);
        mano.remove(randomElement);
        descartadas.add(randomElement);
        jugarCarta(randomElement);
        actualizar();
        setTurnos(turnos--);
    }

    @Override
    public void setIgnorarEscudos(boolean ignorarEscudos) {
        this.ignorarEscudos = ignorarEscudos;
    }

    @Override
    public void setAtaquesDobles(boolean ataquesDobles) {
        this.ataquesDobles = ataquesDobles;
    }
}
