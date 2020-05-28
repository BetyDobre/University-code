package gui;

import model.Client;
import repositories.client.DBClientRepository;

import javax.swing.*;

public class SignUpFrame extends JFrame {

    private JLabel title = new JLabel("SIGN UP");
    private JLabel u = new JLabel("GIVE THE USERNAME: ");
    private JLabel p = new JLabel("GIVE THE PASSWORD: ");
    private JLabel p1 = new JLabel("CONFIRM PASSWORD: ");
    private JTextField ut = new JTextField(20);
    private JPasswordField pt = new JPasswordField(20);
    private JPasswordField p1t = new JPasswordField(20);
    private JButton create = new JButton("CREATE ACCOUNT");

    public SignUpFrame(){
        super("SignUp page");
        setLayout(null);
        add(title);
        title.setBounds(210,10,100,100);
        add(u);
        u.setBounds(50,100,150,100);
        add(ut);
        ut.setBounds(190,140,150,20);
        add(p);
        p.setBounds(50,130,150,100);
        add(pt);
        pt.setBounds(190,170,150,20);
        add(p1);
        p1.setBounds(50,160,150,100);
        add(p1t);
        p1t.setBounds(190,200,150,20);
        add(create);
        create.setBounds(170,260,160,20);

        create.addActionListener(ev->{
            String username = ut.getText();
            String password = pt.getText();
            String confirm = p1t.getText();

            Client c = new Client();
            c.setNume(username);
            c.setParola(password);

            if(username.length() > 0) {
                if(password.length() > 0) {
                    if (password.equals(confirm)) {
                        if (password.length() >= 4) {
                            DBClientRepository rep = new DBClientRepository();
                            rep.addClient(c);
                            JOptionPane.showMessageDialog(null, "Account successfully created !");
                        } else {
                            JOptionPane.showMessageDialog(null, "Password to short(minimum 4 characters) !");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Password must not be null ) !");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Username must not be null !");
            }

        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);
    }

}
