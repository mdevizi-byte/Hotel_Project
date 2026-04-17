package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class InMemoryHotelStore {
    private static final Map<Integer, String> OSPITI = new HashMap<>();
    private static final List<PrenotazioneMemoria> PRENOTAZIONI = new ArrayList<>();
    private static final Set<Integer> CAMERE = new HashSet<>();

    private static int prossimoIdOspite = 1;

    static {
        for (int camera = 1; camera <= 20; camera++) {
            CAMERE.add(camera);
        }
    }

    private InMemoryHotelStore() {
    }

    public static synchronized int aggiungiOspite(String nome, String cognome) {
        int id = prossimoIdOspite++;
        OSPITI.put(id, nome + " " + cognome);
        return id;
    }

    public static synchronized boolean aggiungiPrenotazione(int idOspite, int numeroCamera, String data) {
        if (!OSPITI.containsKey(idOspite)) {
            return false;
        }

        if (!CAMERE.contains(numeroCamera)) {
            return false;
        }

        PRENOTAZIONI.add(new PrenotazioneMemoria(idOspite, numeroCamera, data));
        return true;
    }

    public static synchronized void stampaPrenotazioni() {
        if (PRENOTAZIONI.isEmpty()) {
            System.out.println("Nessuna prenotazione presente.");
            return;
        }

        System.out.println("--- ELENCO PRENOTAZIONI (MODALITA IN-MEMORY) ---");
        for (PrenotazioneMemoria prenotazione : PRENOTAZIONI) {
            String ospite = OSPITI.get(prenotazione.idOspite);
            System.out.println(
                    "Ospite: " + ospite + " | Camera: " + prenotazione.numeroCamera + " | Data: " + prenotazione.data);
        }
    }

    private static final class PrenotazioneMemoria {
        private final int idOspite;
        private final int numeroCamera;
        private final String data;

        private PrenotazioneMemoria(int idOspite, int numeroCamera, String data) {
            this.idOspite = idOspite;
            this.numeroCamera = numeroCamera;
            this.data = data;
        }
    }
}