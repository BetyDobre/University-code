package repositories;

import managers.DBConnectionManager;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientRepository {
    public void addClient(Client client) {
            String sql = "INSERT INTO clienti VALUES (NULL, ?, ?)";
            try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
            ) {
            statement.setString(1, client.getNume());
            statement.setString(2, client.getParola());

            statement.executeUpdate();
            System.out.println("Clientul a fost adaugat cu succes !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client findUserByUsername(String nume) {
        String sql = "SELECT * FROM clienti WHERE nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, nume);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            if (set.next()) {
                int id = set.getInt("id");
                String u = set.getString("nume");
                String p = set.getString("parola");

                return new Client(id, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client findUserByID(int id) {
        String sql = "SELECT * FROM clienti WHERE id = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            if (set.next()) {
                int id1 = set.getInt("id");
                String u = set.getString("nume");
                String p = set.getString("parola");

                return new Client(id1, u, p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Client> afisClienti() {
        String sql = "SELECT * FROM clienti";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Client> result = new ArrayList<>();
            while (set.next()) {
                int id1 = set.getInt("id");
                String u = set.getString("nume");
                String p = set.getString("parola");

                Client n = new Client(id1, u, p);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
