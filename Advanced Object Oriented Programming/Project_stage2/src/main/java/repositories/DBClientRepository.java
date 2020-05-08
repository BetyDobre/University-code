package repositories;

import managers.DBConnectionManager;
import model.Client;

import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;

public class DBClientRepository implements ClientRepository{

    @Override
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

    @Override
    public Client findClientByUsername(String nume) {
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

    @Override
    public Client findClientById(int id) {
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

    @Override
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

    public void deleteClientByUsername(String nume) {
        String sql = "DELETE FROM clienti WHERE nume = '"+nume+"' ";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Clientul a fost sters cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
