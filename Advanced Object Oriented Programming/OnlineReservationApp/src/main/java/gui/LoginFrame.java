package gui;

import model.Client;
import service.DBLoginService;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private JLabel w = new JLabel("Welcome !");
    private JLabel e1 = new JLabel("USERNAME:");
    private JTextField t1 = new JTextField(20);
    private JLabel e2 = new JLabel("PASSWORD:");
    private JPasswordField t2 = new JPasswordField(20);
    private JButton loginButton = new JButton("LOGIN");
    private JButton signup = new JButton("SIGN UP");

    public LoginFrame() {
        super("Online reservations application !");
        setLayout(null);
        add(w);
        w.setBounds(210,10,100,100);
        add(e1);
        e1.setBounds(100,100,100,100);
        add(t1);
        t1.setBounds(180,140,150,20);
        add(e2);
        e2.setBounds(100,130,100,100);
        add(t2);
        t2.setBounds(180,170,150,20);
        add(loginButton);
        loginButton.setBounds(200,220,80,20);
        add(signup);
        signup.setBounds(195,250,90,20);

        loginButton.addActionListener(ev -> {
            String username = t1.getText();
            String password = t2.getText();

            DBLoginService service = DBLoginService.getInstance();

            Client u = new Client();
            u.setNume(username);
            u.setParola(password);

            boolean res = service.login(u);

            if(username.equals("admin") && password.equals("admin")){
                AdminFrame a = new AdminFrame();
            }
            else
            if(res) {
                    ReservationFrame r = new ReservationFrame(username);
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password");
            }
        });

        signup.addActionListener(ev -> {
            SignUpFrame s = new SignUpFrame();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);
    }
}

