package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestioneHotel {
    public void inserisciOspite(String nome, String cognome) {
        String sql = "INSERT INTO utenti (nome, cognome) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, cognome);
            pstmt.executeUpdate();
            System.out.println("Ospite salvato!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}