package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class prenotazione {
    public void prenota(int idOspite, int numeroCamera, String data) {
        String sql = "INSERT INTO prenotazioni (id_utente, numero_camera, data_prenotazione) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idOspite);
            pstmt.setInt(2, numeroCamera);
            pstmt.setString(3, data); // Il database lo convertirà in DATE
            pstmt.executeUpdate();
            System.out.println("Prenotazione effettuata!");

        } catch (SQLException e) {
            boolean prenotazioneSalvata = InMemoryHotelStore.aggiungiPrenotazione(idOspite, numeroCamera, data);
            if (prenotazioneSalvata) {
                System.out.println("DB non disponibile, prenotazione salvata in memoria.");
            } else {
                System.out.println("Impossibile prenotare in memoria: verifica ID ospite e numero camera (1-20).");
            }
        }
    }

}
