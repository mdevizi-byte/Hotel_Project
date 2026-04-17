package com.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    // Chiama il metodo per inserire ospite
                    break;
                case 2:
                    // Chiama il metodo per prenotare
                    break;
                case 3:
                    // Chiama il metodo per visualizzare
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
