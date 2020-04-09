package repositories;

import managers.DBConnectionManager;
import model.Opera;
import model.StandUp;
import model.Teatru;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperaRepository implements SpectacolRepository<Opera> {

    public void addSpectacol(Opera opera) {
        String sql = "INSERT INTO opere VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, opera.getNume());
            statement.setString(2, opera.getOras());
            statement.setInt (3, opera.getOra());
            statement.setInt(4, opera.getDurata());
            statement.setInt(5, opera.getPret());
            statement.setString(6,opera.getData());
            statement.setString(7, opera.getNumeOpera());
            statement.setString(8, opera.getLimba());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Opera> findSpectacolByName(String nume) {
        String sql = "SELECT * FROM opere WHERE nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, nume);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Opera> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numeo = set.getString("numeOpera");
                String limba= set.getString("limba");

                Opera n = new Opera(num, oras, ora, durata, pret, data, numeo, limba);
                result.add(n);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Opera> findSpectacolByData(String data) {
        String sql = "SELECT * FROM opere WHERE data = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, data);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Opera> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data1 = set.getString("data");
                String numeo = set.getString("numeOpera");
                String limba= set.getString("limba");

                Opera n = new Opera(num, oras, ora, durata, pret, data1, numeo, limba);
                result.add(n);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Opera> findSpectacolByNumeOpera(String numeOpera) {
        String sql = "SELECT * FROM opere WHERE numeOpera = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, numeOpera);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Opera> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numeo = set.getString("numeOpera");
                String limba= set.getString("limba");

                Opera n = new Opera(num, oras, ora, durata, pret, data, numeo, limba);
                result.add(n);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Opera> findSpectacolByOra(int ora) {
        String sql = "SELECT * FROM opere WHERE ora = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setInt(1, ora);

            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Opera> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numeo = set.getString("numeOpera");
                String limba= set.getString("limba");

                Opera n = new Opera(num, oras, ora1, durata, pret, data, numeo, limba);
                result.add(n);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Opera> afisSpectacole() {
        String sql = "SELECT * FROM opere";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Opera> result = new ArrayList<>();

            while (set.next()) {
                String num = set.getString("nume");
                String oras = set.getString("oras");
                int ora1 = set.getInt("ora");
                int durata = set.getInt("durata");
                int pret = set.getInt("pret");
                String data = set.getString("data");
                String numeo = set.getString("numeOpera");
                String limba= set.getString("limba");

                Opera n = new Opera(num, oras, ora1, durata, pret, data, numeo, limba);
                result.add(n);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Opera findSpectacol(int ora, String data, String nume) {
        String sql = "SELECT * FROM opere WHERE ora = ? and data = ? and nume = ?";
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
                String numet = set.getString("numeOpera");
                String limba = set.getString("limba");

                return  new Opera(num, oras, ora1, durata, pret, data1, numet, limba);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
