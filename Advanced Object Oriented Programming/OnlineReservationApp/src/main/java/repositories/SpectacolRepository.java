package repositories;

import repositories.opera.DBOperaRepository;
import repositories.opera.FileOperaRepository;
import repositories.standup.DBStandUpRepository;
import repositories.standup.FileStandUpRepository;
import repositories.theater.DBTeatruRepository;
import repositories.theater.FileTeatruRepository;

import java.util.ArrayList;

public interface SpectacolRepository<T> {

    void addSpectacol(T x);
    ArrayList<T> findSpectacolByName(String nume);
    ArrayList<T> findSpectacolByData(String data);
    ArrayList<T> findSpectacolByOra(int ora);
    ArrayList<T> afisSpectacole();
    T findSpectacol(int ora, String data, String nume);
    void deleteSpectacolByNume(String s);
    void updateOraSpectacol(int ora_veche, int ora);

    static SpectacolRepository build(Type type){
        switch (type){
            case DBTeatru: return new DBTeatruRepository();
            case DBOpera: return new DBOperaRepository();
            case DBStandUp: return new DBStandUpRepository();
            case FileTeatru: return new FileTeatruRepository();
            case FileOpera: return new FileOperaRepository();
            case FileStandUp: return new FileStandUpRepository();
        }
        throw new RuntimeException("No such type");
    }

    enum Type{
        DBTeatru, DBOpera, DBStandUp, FileTeatru, FileOpera, FileStandUp
    };

}
