package repositories;

import model.Opera;
import model.Spectacol;
import model.StandUp;
import model.Teatru;

import java.util.ArrayList;

public interface SpectacolRepository<T> {

    void addSpectacol(T x);
    ArrayList<T> findSpectacolByName(String nume);
    ArrayList<T> findSpectacolByData(String data);
    ArrayList<T> findSpectacolByOra(int ora);
    ArrayList<T> afisSpectacole();
    T findSpectacol(int ora, String data, String nume);

    static SpectacolRepository build(Type type){
        switch (type){
            case Teatru: return new TeatruRepository();
            case Opera: return new OperaRepository();
            case StandUp: return new StandUpRepository();

        }
        throw new RuntimeException("No such type");
    }

    enum Type{
        Teatru, Opera, StandUp
    };

}
