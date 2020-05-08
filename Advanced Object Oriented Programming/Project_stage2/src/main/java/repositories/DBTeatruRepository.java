package repositories;

import managers.DBConnectionManager;
import model.Client;
import model.Opera;
import model.Teatru;

import java.sql.*;
import java.util.ArrayList;

public class DBTeatruRepository implements SpectacolRepository<Teatru>{
    public void addSpectacol(Teatru teatru) {
        String sql = "INSERT INTO teatre VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, teatru.getNume());
            statement.setString(2, teatru.getOras());
            statement.setInt(3, teatru.getOra());
            statement.setInt(4, teatru.getDurata());
            statement.setInt(5, teatru.getPret());
            statement.setString(6,teatru.getData());
            statement.setString(7, teatru.getNumeTeatru());
            statement.setString(8, teatru.getActori());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Teatru> findSpectacolByName(String nume) {
        String sql = "SELECT * FROM teatre WHERE nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, nume);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Teatru> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                Teatru n =  new Teatru(num, oras, ora, durata, pret, data, numet, actori);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Teatru> findSpectacolByData(String data) {
        String sql = "SELECT * FROM teatre WHERE data = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, data);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Teatru> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                Teatru n =  new Teatru(num, oras, ora, durata, pret, data1, numet, actori);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Teatru> findSpectacolByNumeTeatru(String numeTeatru) {
        String sql = "SELECT * FROM teatre WHERE numeTeatru = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, numeTeatru);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Teatru> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                Teatru n =  new Teatru(num, oras, ora, durata, pret, data, numet, actori);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Teatru> findSpectacolByOra(int ora) {
        String sql = "SELECT * FROM teatre WHERE ora = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, ora);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Teatru> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                Teatru n =  new Teatru(num, oras, ora1, durata, pret, data, numet, actori);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Teatru> afisSpectacole(){
        String sql = "SELECT * FROM teatre ";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Teatru> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                Teatru n =  new Teatru(num, oras, ora1, durata, pret, data, numet, actori);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Teatru findSpectacol(int ora, String data, String nume) {
        String sql = "SELECT * FROM teatre WHERE ora = ? and data = ? and nume = ?";
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
                String numet = set.getString("numeTeatru");
                String actori = set.getString("actori");

                return  new Teatru(num, oras, ora1, durata, pret, data1, numet, actori);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSpectacolByNume(String nume) {
        String sql = "DELETE FROM teatre WHERE nume = '"+nume+"' ";
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
        String sql = "UPDATE teatre SET ora = '"+ora+"' WHERE ora = '"+ora_veche+"' ";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                Statement statement = con.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("Ora spectacolului a fost modificata !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Spectacol adaugat cu succes !");
    }
}

