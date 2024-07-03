package com.example.dmt.Player;

import com.example.dmt.Cartas.Carta;
import com.example.dmt.Cartas.CartaEspecial;
import com.example.dmt.Mediator.GameMediator;
import com.example.dmt.Observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Player implements Observer, Players {
    private String name;
    private Personaje personaje;
    private int health;
    private int escudos;
    private List<Carta> mazo;
    private List<Carta> mano;
    private List<Carta> cartasActivas;
    private List<Carta> descartadas;
    private List<Players> oponentes;
    private int turnos;
    private boolean ignorarEscudos;
    private boolean ataquesDobles;
    private GameMediator mediator;

    public Player(String name) {
        this.name = name;
        this.health = 10;
        this.mano = new ArrayList<>();
        this.cartasActivas = new ArrayList<>();
        this.descartadas = new ArrayList<>();
        this.oponentes = new ArrayList<>();
        this.mediator = new GameMediator();
    }

    public Player(String name, Personaje personaje) {
        this.name = name;
        this.personaje = personaje;
        this.health = 10;
        this.mazo = personaje.getMazo();
        this.mano = new ArrayList<>();
        this.cartasActivas = new ArrayList<>();
        this.descartadas = new ArrayList<>();
        this.oponentes = new ArrayList<>();
        this.mediator = new GameMediator();
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
    public void recibirAtaque(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println(name + " recibió " + damage + " puntos de daño. Vida restante: " + health);
    }

    @Override
    public void curarse(int corazones) {
        this.health += corazones;
        System.out.println(name + " se curó " + corazones + " puntos. Vida actual: " + health);
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
    public void jugarCarta(Carta carta) {
        System.out.println(name + " jugó la carta " + carta.getNombre());
        if (carta.getEspadas() > 0) {
            for (Players opponent : oponentes) {
                GameMediator.redirectAttack(this, (Player) opponent, carta.getEspadas());
            }
        }
        if (carta.getEscudos() > 0) {
            escudos += carta.getEscudos();
            System.out.println(name + " ganó " + carta.getEscudos() + " escudos.");
        }
        if (carta.getCartasExtra() > 0) {
            tomarCarta(carta.getCartasExtra());
        }
        if (carta.getRayos() > 0) {
            turnos += carta.getRayos();
            System.out.println(name + " ganó " + carta.getRayos() + " turnos extra.");
        }
        if (carta.getCorazones() > 0) {
            curarse(carta.getCorazones());
        }
        if (carta instanceof CartaEspecial) {
            ((CartaEspecial) carta).getHabilidad().usarHabilidad(oponentes, this);
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
    public void mezclarMazo() {
        Collections.shuffle(this.mazo);
    }

    @Override
    public void takeTurn() {
        Scanner sc = new Scanner(System.in);
        while (turnos > 0 && !mano.isEmpty()) {
            System.out.println("Elige una carta: ");
            for (int i = 0; i < mano.size(); i++) {
                Carta c = mano.get(i);
                System.out.println((i + 1) + ". " + c.getNombre());
                System.out.println("Espadas: " + c.getEspadas());
                System.out.println("Escudos: " + c.getEscudos());
                System.out.println("Rayos: " + c.getRayos());
                System.out.println("Corazones: " + c.getCorazones());
                System.out.println("Cartas Extra: " + c.getCartasExtra());
            }
            int seleccion = sc.nextInt() - 1;
            Carta cartaElegida = mano.remove(seleccion);
            descartadas.add(cartaElegida);
            jugarCarta(cartaElegida);
            actualizar();
            setTurnos(turnos--);
        }
    }

    @Override
    public void setIgnorarEscudos(boolean ignorarEscudos) {
        this.ignorarEscudos = ignorarEscudos;
    }

    @Override
    public void setAtaquesDobles(boolean ataquesDobles) {
        this.ataquesDobles = ataquesDobles;
    }

    public GameMediator getMediator() {
        return mediator;
    }
}


