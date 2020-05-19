package gui;

import model.*;
import repositories.DBClientRepository;
import repositories.DBOperaRepository;
import repositories.DBStandUpRepository;
import repositories.DBTeatruRepository;
import service.DBLoginService;
import service.DBRezOperaService;
import service.DBRezStandUpService;
import service.DBRezTeatruService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationFrame extends JFrame  {
    private JPanel panel;
    private JLabel w = new JLabel("Make a reservation");

    private JLabel client = new JLabel("CLIENT: ");
    private JLabel client1 = new JLabel();

    private JLabel tip = new JLabel("Show type: ");
    private JTextField tip1 = new JTextField(20);

    private JLabel nume = new JLabel("NAME:");
    private JTextField nume1 = new JTextField(20);

    private JLabel date = new JLabel("DATE:");
    private JTextField date1 = new JTextField(20);

    private JLabel ora = new JLabel("HOUR:");
    private JTextField ora1 = new JTextField(20);

    private JLabel loc = new JLabel("SIT:");
    private JTextField loc1 = new JTextField(20);


    private JButton adauga = new JButton("BOOK NOW");
    private JButton afis = new JButton("SHOWS");

    public ReservationFrame(String username){
        super("Make a reservation");
        setLayout(null);
        add(w);
        w.setBounds(110,0,300,100);
        add(tip);
        tip.setBounds(100,20,100,100);
        add(tip1);
        tip1.setBounds(180,60,150,20);
        add(nume);
        nume.setBounds(100,50,100,100);
        add(nume1);
        nume1.setBounds(180,90,150,20);
        add(date);
        date.setBounds(100,80,100,100);
        add(date1);
        date1.setBounds(180,120,150,20);
        add(ora);
        ora.setBounds(100,110,100,100);
        add(ora1);
        ora1.setBounds(180,150,150,20);
        add(loc);
        loc.setBounds(100,140,100,100);
        add(loc1);
        loc1.setBounds(180,180,150,20);
        add(client);
        client.setBounds(100,200,150,100);
        add(client1);
        client1.setBounds(180,240,150,20);
        client1.setText(username);
        add(adauga);
        adauga.setBounds(150,300,150,30);
        add(afis);
        afis.setBounds(150,340,150,30);

        adauga.addActionListener(ev->{
            String tip = tip1.getText();
            String nume = nume1.getText();
            String data = date1.getText();
            int ora = Integer.parseInt(ora1.getText());
            int loc = Integer.parseInt(loc1.getText());

//            if(tip.toLowerCase() == "teatru" || tip.toLowerCase() == "opera") {
//                Rezervare r = new Rezervare();
//                r.setNumeClient(username);
//                r.setNumeSpectacol(nume);
//                r.setOra(ora);
//                r.setData(data);
//                r.setLoc(loc);
//                r.setSector(sector);
//            }
            DBClientRepository crep = new DBClientRepository();
            Client c = crep.findClientByUsername(username);
            DBLoginService service = DBLoginService.getInstance();
            int i;
            if(tip.toLowerCase().equals("teatru") || tip.toLowerCase().equals("opera")) {
                ArrayList<LocTeatruOpera> locuriPiesa = new ArrayList<>();
                for (i = 1; i <= 50; i++) {
                    LocTeatruOpera loc1 = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorA);
                    locuriPiesa.add(loc1);
                }
                for (i = 51; i <= 100; i++) {
                    LocTeatruOpera loc1 = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorB);
                    locuriPiesa.add(loc1);
                }
                for (i = 101; i <= 110; i++) {
                    LocTeatruOpera loc1 = new LocTeatruOpera(i, LocTeatruOpera.sector.Loja);
                    locuriPiesa.add(loc1);
                }
                for (i = 111; i <= 150; i++) {
                    LocTeatruOpera loc1 = new LocTeatruOpera(i, LocTeatruOpera.sector.Balcon);
                    locuriPiesa.add(loc1);
                }

                if(tip.toLowerCase().equals("opera")) {
                    DBOperaRepository repositoryDBO = new DBOperaRepository();
                    Opera o = repositoryDBO.findSpectacol(ora, data, nume);
                    DBRezOperaService rezerv = new DBRezOperaService();
                    if (o == null){
                        JOptionPane.showMessageDialog(null, "Show don't found !");
                    }
                    else {
                        rezerv.rezervare(c, service, o, locuriPiesa.get(loc));
                        JOptionPane.showMessageDialog(null, "Succes !");
                    }
                }
                else {
                    DBTeatruRepository repositoryDBT = new DBTeatruRepository();
                    Teatru o = repositoryDBT.findSpectacol(ora, data, nume);
                    DBRezTeatruService rezerv = new DBRezTeatruService();
                    if (o == null){
                        JOptionPane.showMessageDialog(null, "Show don't found !");
                    }
                    else {
                        rezerv.rezervare(c, service, o, locuriPiesa.get(loc));
                        JOptionPane.showMessageDialog(null, "Succes !");
                    }
                }
            }
            else if(tip.toLowerCase().equals("stand-up")){
                ArrayList<Loc> locuri = new ArrayList<>();
                //alocarea locurilor
                int j;
                for (j = 1; j <= 100; j++){
                    Loc loc2 = new Loc(j);
                    locuri.add(loc2);
                }

                DBStandUpRepository repositoryDBS = new DBStandUpRepository();
                StandUp o = repositoryDBS.findSpectacol(ora, data, nume);
                DBRezStandUpService rezerv = new DBRezStandUpService();
                if (o == null){
                    JOptionPane.showMessageDialog(null, "Show don't found !");
                }
                else {
                    rezerv.rezervare(c, service, o, locuri.get(loc));
                    JOptionPane.showMessageDialog(null, "Succes !");
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Wrong type!");
            }

        });

        afis.addActionListener(ev->{
            ShowsFrame s = new ShowsFrame();
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 420);
        setVisible(true);
    }


}
