package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestioneHotel gestioneHotel = new GestioneHotel();
        prenotazione gestionePrenotazione = new prenotazione();
        gestioneTuttePrenotazioni reportPrenotazioni = new gestioneTuttePrenotazioni();
        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- MENU HOTEL ---");
            System.out.println("1. Inserisci nuovo ospite");
            System.out.println("2. Effettua una prenotazione");
            System.out.println("3. Visualizza tutte le prenotazioni");
            System.out.println("4. Esci");
            System.out.print("Scegli un'operazione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma l'invio a capo

            switch (scelta) {
                case 1:
                    System.out.print("Nome ospite: ");
                    String nome = scanner.nextLine();
                    System.out.print("Cognome ospite: ");
                    String cognome = scanner.nextLine();
                    gestioneHotel.inserisciOspite(nome, cognome);
                    break;
                case 2:
                    System.out.print("ID ospite: ");
                    int idOspite = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Numero camera: ");
                    int numeroCamera = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Data prenotazione (YYYY-MM-DD): ");
                    String dataPrenotazione = scanner.nextLine();

                    gestionePrenotazione.prenota(idOspite, numeroCamera, dataPrenotazione);
                    break;
                case 3:
                    reportPrenotazioni.mostraPrenotazioni();
                    break;
                case 4:
                    esci = true;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        scanner.close();
    }

}
