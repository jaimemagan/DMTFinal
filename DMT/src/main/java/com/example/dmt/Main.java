package com.example.dmt;

import com.example.dmt.Cartas.Carta;
import com.example.dmt.Player.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.example.dmt.Mediator.GameMediator;

import static com.example.dmt.Player.Personaje.*;

public class Main {
    public static List<Personaje> personajes = new ArrayList<Personaje>();

    public static void main(String[] args) {
        personajes = generarPersonajes();
        Tablero.crearJugador(personajes);
        Tablero.imprimirJugadores();

        Tablero.empezarPartida();
    }
}