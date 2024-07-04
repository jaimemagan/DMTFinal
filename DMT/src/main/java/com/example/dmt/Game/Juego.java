package com.example.dmt.Game;

import com.example.dmt.Cartas.Carta;
import com.example.dmt.Habilidades.Azzan.*;
import com.example.dmt.Habilidades.Blorp.*;
import com.example.dmt.Habilidades.Delilah.*;
import com.example.dmt.Habilidades.Habilidad;
import com.example.dmt.Habilidades.Lia.*;
import com.example.dmt.Habilidades.MinscAndBoo.*;
import com.example.dmt.Habilidades.Sutha.*;
import com.example.dmt.Player.Players;

import java.util.List;
import java.util.Scanner;

public class Juego {
    private List<Players> jugadores;
    private Players currentPlayer;
    private Scanner scanner;

    public Juego(List<Players> jugadores) {
        this.jugadores = jugadores;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarJuego() {
        while (!juegoTerminado()) {
            for (Players jugador : jugadores) {
                currentPlayer = jugador;
                if (jugador.getHealth() > 0) {
                    jugarTurno();
                }
            }
        }
        anunciarGanador();
    }

    private void jugarTurno() {
        System.out.println(currentPlayer.getName() + " es tu turno.");
        mostrarOpciones();
    }

    private void mostrarOpciones() {
        System.out.println("Selecciona una acción:");
        System.out.println("1. Jugar carta");
        System.out.println("2. Usar habilidad");
        System.out.println("3. Pasar turno");

        int seleccion = scanner.nextInt();

        switch (seleccion) {
            case 1:
                jugarCarta();
                break;
            case 2:
                usarHabilidad();
                break;
            case 3:
                System.out.println("Turno pasado.");
                break;
            default:
                System.out.println("Selección inválida.");
                mostrarOpciones();
        }
    }

    private void jugarCarta() {
        System.out.println("Selecciona una carta para jugar:");
        List<Carta> mano = currentPlayer.getMano();
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i + 1) + ". " + mano.get(i).getNombre());
        }

        int seleccion = scanner.nextInt();

        if (seleccion < 1 || seleccion > mano.size()) {
            System.out.println("Selección inválida.");
            jugarCarta();
        } else {
            Carta cartaSeleccionada = mano.get(seleccion - 1);
            currentPlayer.jugarCarta(cartaSeleccionada);
            currentPlayer.getMano().remove(cartaSeleccionada);
        }
    }

    private void usarHabilidad() {
        System.out.println("Selecciona una habilidad para usar:");
        System.out.println("1. AzzanH1");
        System.out.println("2. AzzanH2");
        System.out.println("3. AzzanH3");
        System.out.println("4. BlorpH1");
        System.out.println("5. BlorpH2");
        System.out.println("6. BlorpH3");
        System.out.println("7. DelilahH1");
        System.out.println("8. DelilahH2");
        System.out.println("9. DelilahH3");
        System.out.println("10. LiaH1");
        System.out.println("11. LiaH2");
        System.out.println("12. MinscAndBooH1");
        System.out.println("13. MinscAndBooH2");
        System.out.println("14. MinscAndBooH3");
        System.out.println("15. SuthaH1");
        System.out.println("16. SuthaH2");
        System.out.println("17. SuthaH3");

        int seleccion = scanner.nextInt();
        Habilidad habilidad;

        switch (seleccion) {
            case 1:
                habilidad = new AzzanH1();
                break;
            case 2:
                habilidad = new AzzanH2();
                break;
            case 3:
                habilidad = new AzzanH3();
                break;
            case 4:
                habilidad = new BlorpH1();
                break;
            case 5:
                habilidad = new BlorpH2();
                break;
            case 6:
                habilidad = new BlorpH3();
                break;
            case 7:
                habilidad = new DelilahH1();
                break;
            case 8:
                habilidad = new DelilahH2();
                break;
            case 9:
                habilidad = new DelilahH3();
                break;
            case 10:
                habilidad = new LiaH1();
                break;
            case 11:
                habilidad = new LiaH2();
                break;
            case 12:
                habilidad = new MinscAndBooH1();
                break;
            case 13:
                habilidad = new MinscAndBooH2();
                break;
            case 14:
                habilidad = new MinscAndBooH3();
                break;
            case 15:
                habilidad = new SuthaH1();
                break;
            case 16:
                habilidad = new SuthaH2();
                break;
            case 17:
                habilidad = new SuthaH3();
                break;
            default:
                System.out.println("Selección inválida.");
                return;
        }

        habilidad.usarHabilidad(jugadores, currentPlayer);
    }

    private boolean juegoTerminado() {
        int jugadoresVivos = 0;
        for (Players jugador : jugadores) {
            if (jugador.getHealth() > 0) {
                jugadoresVivos++;
            }
        }
        return jugadoresVivos <= 1;
    }

    private void anunciarGanador() {
        for (Players jugador : jugadores) {
            if (jugador.getHealth() > 0) {
                System.out.println("El ganador es " + jugador.getName());
                break;
            }
        }
    }
}
