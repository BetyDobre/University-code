package service;

import managers.DBConnectionManager;
import model.*;
import repositories.SpectacolRepository;
import repositories.opera.DBOperaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBRezOperaService {

    SpectacolRepository operaRepository= SpectacolRepository.build(SpectacolRepository.Type.DBTeatru);

    public DBRezOperaService() {}

    public void rezervare(Client client, DBLoginService loginService, Opera opera, LocTeatruOpera loc){
        if (loginService.login(client)){
            Opera result =(Opera) operaRepository.findSpectacol(opera.getOra(), opera.getData(), opera.getNume());
            if(loc.isLiber()){
                String sql = "INSERT INTO rezervari VALUES (NULL, ?, ?, ?, ?, ?, ?)";
                try (
                        Connection con = DBConnectionManager.getInstance().createConnection();
                        PreparedStatement statement = con.prepareStatement(sql);
                ) {
                    statement.setString(1, client.getNume());
                    statement.setString(2, opera.getNume());
                    statement.setString(3, opera.getData());
                    statement.setInt(4, opera.getOra());
                    statement.setInt(5, loc.getNrLoc());
                    statement.setString(6, loc.getZona());

                    statement.executeUpdate();
                    loc.setLiber(false);
                    System.out.println("Rezervarea a fost efectuata cu succes !");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Locul este ocupat !");
            }
        };
    }

    public ArrayList<Rezervare> afisRezervari() {
        String sql = "SELECT * FROM rezervari";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            ResultSet set = statement.executeQuery(); // in RS avem randurile din tabela

            ArrayList<Rezervare> result = new ArrayList<>();

            while (set.next()) {
                int nr = set.getInt("nr");
                String nume = set.getString("numeClient");
                String numes = set.getString("numeSpectacol");
                String data = set.getString("data");
                int ora = set.getInt("ora");
                int loc = set.getInt("loc");
                String sector = set.getString("sector");

                Rezervare n = new Rezervare(nr, nume, numes, data, ora, loc, sector);
                result.add(n);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DBRezOperaService getInstance() {
        return DBRezOperaService.SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final  DBRezOperaService INSTANCE = new DBRezOperaService();
    }
}
