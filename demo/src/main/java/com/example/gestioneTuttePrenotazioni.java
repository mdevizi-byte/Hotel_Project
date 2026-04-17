package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gestioneTuttePrenotazioni {
    public void mostraPrenotazioni() {
        // Questa query "unisce" le tre tabelle per darti un report completo
        String sql = "SELECT u.nome, u.cognome, c.numero, p.data_prenotazione " +
                "FROM prenotazioni p " +
                "JOIN utenti u ON p.id_utente = u.id " +
                "JOIN camere c ON p.numero_camera = c.numero";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) { // rs contiene la tabella dei risultati

            System.out.println("--- ELENCO PRENOTAZIONI ---");
            while (rs.next()) {
                // Estrai i dati dalle colonne del risultato
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                int camera = rs.getInt("numero");
                String data = rs.getString("data_prenotazione");

                System.out.println("Ospite: " + nome + " " + cognome + " | Camera: " + camera + " | Data: " + data);
            }

        } catch (SQLException e) {
            System.out.println("Errore durante la lettura prenotazioni: " + e.getMessage());
        }
    }
}
