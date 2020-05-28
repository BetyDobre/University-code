package gui;

import model.Opera;
import model.StandUp;
import model.Teatru;
import repositories.opera.DBOperaRepository;
import repositories.standup.DBStandUpRepository;
import repositories.theater.DBTeatruRepository;

import javax.swing.*;

public class AdminFrame extends JFrame {
    private JLabel w = new JLabel("Add a show(theater, opera, stand-up) !");
    private JLabel tip = new JLabel("SHOW TYPE: ");
    private JTextField tip1 = new JTextField(20);
    private JLabel nume = new JLabel("NAME:");
    private JTextField nume1 = new JTextField(20);
    private JLabel oras = new JLabel("CITY:");
    private JTextField oras1 = new JTextField(20);
    private JLabel ora = new JLabel("HOUR:");
    private JTextField ora1 = new JTextField(20);
    private JLabel durata = new JLabel("DURATION:");
    private JTextField durata1 = new JTextField(20);
    private JLabel price = new JLabel("PRICE:");
    private JTextField price1 = new JTextField(20);
    private JLabel date = new JLabel("DATE:");
    private JTextField date1 = new JTextField(20);
    private JLabel nameot = new JLabel("NAME OF THE OPERA/THEATER:");
    private JLabel optional = new JLabel("(OPTIONAL)");
    private JTextField nameot1 = new JTextField(20);
    private JLabel actori = new JLabel("ACTORS OR COMEDIANS ");
    private  JLabel language = new JLabel("OR LANGUAGE: ");
    private JTextField actori1 = new JTextField(50);
    private JButton adauga = new JButton("ADD");

    public AdminFrame() {
        super("Add a show");
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
        add(oras);
        oras.setBounds(100,80,100,100);
        add(oras1);
        oras1.setBounds(180,120,150,20);
        add(ora);
        ora.setBounds(100,110,100,100);
        add(ora1);
        ora1.setBounds(180,150,150,20);
        add(durata);
        durata.setBounds(100,140,100,100);
        add(durata1);
        durata1.setBounds(180,180,150,20);
        add(price);
        price.setBounds(100,170,100,100);
        add(price1);
        price1.setBounds(180,210,150,20);
        add(date);
        date.setBounds(100,200,100,100);
        add(date1);
        date1.setBounds(180,240,150,20);
        add(nameot);
        nameot.setBounds(0,230,200,100);
        add(nameot1);
        nameot1.setBounds(200,270,150,20);
        add(optional);
        optional.setBounds(100,260,100,100);
        add(actori);
        actori.setBounds(0,290,200,100);
        add(actori1);
        actori1.setBounds(180,330,150,20);
        add(language);
        language.setBounds(60,320,100,100);
        add(adauga);
        adauga.setBounds(200,400,80,30);

        adauga.addActionListener(ev->{
            String tip = tip1.getText();
            String nume = nume1.getText();
            String oras = oras1.getText();
            int ora = Integer.parseInt(ora1.getText());
            int durata = Integer.parseInt(durata1.getText());
            int pret = Integer.parseInt(price1.getText());
            String data = date1.getText();
            String numeot = nameot1.getText();
            String actori = actori1.getText();

            if(tip.toLowerCase().equals("teatru")){
                Teatru t = new Teatru(nume, oras, ora, durata, pret, data, numeot, actori);
                DBTeatruRepository rep = new DBTeatruRepository();
                rep.addSpectacol(t);
                JOptionPane.showMessageDialog(null, "Succes !");
            }
            else if(tip.toLowerCase().equals("opera")){
                Opera o = new Opera(nume, oras, ora, durata, pret, data, numeot, actori);
                DBOperaRepository rep = new DBOperaRepository();
                rep.addSpectacol(o);
                JOptionPane.showMessageDialog(null, "Succes !");
            }
            else if(tip.toLowerCase().equals("stand-up")){
                StandUp s = new StandUp(nume, oras, ora, durata, pret, data, actori);
                DBStandUpRepository rep = new DBStandUpRepository();
                rep.addSpectacol(s);
                JOptionPane.showMessageDialog(null, "Succes !");
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect type !");
            }

        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }
}
