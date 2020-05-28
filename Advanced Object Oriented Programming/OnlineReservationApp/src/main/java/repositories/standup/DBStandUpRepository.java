package repositories.standup;

import managers.DBConnectionManager;
import model.Client;
import model.Opera;
import model.StandUp;
import model.Teatru;
import repositories.SpectacolRepository;

import java.sql.*;
import java.util.ArrayList;

public class DBStandUpRepository implements SpectacolRepository<StandUp> {

    public void addSpectacol(StandUp standUp) {
        String sql = "INSERT INTO standup VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, standUp.getNume());
            statement.setString(2, standUp.getOras());
            statement.setInt (3, standUp.getOra());
            statement.setInt(4, standUp.getDurata());
            statement.setInt(5, standUp.getPret());
            statement.setString(6,standUp.getData());
            statement.setString(7, standUp.getComedianti());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Spectacol adaugat cu succes !");
    }

    public ArrayList<StandUp> findSpectacolByName(String nume) {
        String sql = "SELECT * FROM standup WHERE nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, nume);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela
            ArrayList<StandUp> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numec = set.getString("comedianti");

                StandUp n = new StandUp(num, oras, ora, durata, pret, data1, numec);
                result.add(n);
            }
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<StandUp> findSpectacolByData(String data) {
        String sql = "SELECT * FROM standup WHERE data = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, data);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<StandUp> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numec = set.getString("comedianti");

                StandUp n = new StandUp(num, oras, ora, durata, pret, data1, numec);
                result.add(n);
            }
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<StandUp> findSpectacolByOra(int ora) {
        String sql = "SELECT * FROM standup WHERE ora = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, ora);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela
            ArrayList<StandUp> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numec = set.getString("comedianti");

                StandUp n = new StandUp(num, oras, ora1, durata, pret, data1, numec);
                result.add(n);
            }
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<StandUp> afisSpectacole() {
        String sql = "SELECT * FROM standup";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela
            ArrayList<StandUp> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numec = set.getString("comedianti");

                StandUp n = new StandUp(num, oras, ora1, durata, pret, data1, numec);
                result.add(n);
            }
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StandUp findSpectacol(int ora, String data, String nume) {
        String sql = "SELECT * FROM standup WHERE ora = ? and data = ? and nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, ora);
            statement.setString(2, data);
            statement.setString(3, nume);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela


            if (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String comedianti = set.getString("comedianti");

                return  new StandUp(num, oras, ora1, durata, pret, data1, comedianti);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSpectacolByNume(String nume) {
        String sql = "DELETE FROM standup WHERE nume = '"+nume+"' ";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Spectacolul a fost sters cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOraSpectacol(int ora_veche, int ora) {
        String sql = "UPDATE standup SET ora = '"+ora+"' WHERE ora = '"+ora_veche+"' ";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Ora spectacolului a fost modificata !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
