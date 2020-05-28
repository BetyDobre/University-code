package gui;

import model.Opera;
import model.Spectacol;
import model.StandUp;
import model.Teatru;
import repositories.opera.DBOperaRepository;
import repositories.standup.DBStandUpRepository;
import repositories.theater.DBTeatruRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowsFrame extends JFrame implements ActionListener {

    private JLabel tip = new JLabel("Show type: ");
    private JTextField tip1 = new JTextField(20);
    private JPanel panel;

    public ShowsFrame() {
        super("Shows");

        add(tip);
        tip.setBounds(100,20,100,20);
        add(tip1);
        tip1.setBounds(180,20,100,20);

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        add(panel, BorderLayout.CENTER);

        JButton button = new JButton("SHOW");
        add(button, BorderLayout.SOUTH);
        button.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 420);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (tip1.getText().equals("teatru")){
            DBTeatruRepository rep = new DBTeatruRepository();
            ArrayList<Teatru> teatru = rep.afisSpectacole();
            for(Teatru t:teatru){
                this.panel.add(new JLabel(t.toString()), BorderLayout.CENTER);
                validate();
            }
        }
        if(tip1.getText().equals("opera")){
            DBOperaRepository rep = new DBOperaRepository();
            ArrayList<Opera> opera = rep.afisSpectacole();
            for(Opera o:opera){
                this.panel.add(new JLabel(o.toString()), BorderLayout.CENTER);
                validate();
            }
        }
        if(tip1.getText().equals("stand-up")) {
            DBStandUpRepository rep = new DBStandUpRepository();
            ArrayList<StandUp> stand = rep.afisSpectacole();
            for(StandUp s: stand){
                this.panel.add(new JLabel(s.toString()), BorderLayout.CENTER);
                validate();
            }
        }
        if(tip1.getText().equals("all")){
            DBTeatruRepository rep1 = new DBTeatruRepository();
            DBOperaRepository rep2 = new DBOperaRepository();
            DBStandUpRepository rep3 = new DBStandUpRepository();
            ArrayList<Spectacol> s = new ArrayList<>();
            ArrayList<Teatru> teatru = rep1.afisSpectacole();
            ArrayList<Opera> opera = rep2.afisSpectacole();
            ArrayList<StandUp> stand = rep3.afisSpectacole();
            s.addAll(teatru);
            s.addAll(opera);
            s.addAll(stand);
            for(Spectacol r: s){
                this.panel.add(new JLabel(r.toString()), BorderLayout.CENTER);
                validate();
            }
        }
    }
}
