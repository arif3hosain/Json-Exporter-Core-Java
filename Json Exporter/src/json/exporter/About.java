package json.exporter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class About extends JFrame implements ActionListener{
private JPanel panel;
private JLabel developed,lblFacebook,lblGoogleplus,lblGithub,lblLinkedin;
private JLabel linkFacebook,linkGoogle,linkGithub,linkLinkedin;

//        final URI facebook=new URI("");
//        final URI githubh=new URI("");
//        final URI linkedin=new URI("");
//        final URI googleplus=new URI("");
// 
    public About() {
        panel=new JPanel();
        developed=new JLabel("Developer: Arif Hosain");
        developed.setFont(new Font("Cambria", Font.BOLD, 14));
        developed.setBounds(40,5,250,30);
        lblFacebook=new JLabel("Facebook");
        lblFacebook.setBounds(30, 40, 80, 25);
        lblGoogleplus=new JLabel("Google Plus");
        lblGoogleplus.setBounds(30, 70, 100, 25);
        lblGithub=new JLabel("Github");
        lblGithub.setBounds(30, 100, 100, 25);
        lblLinkedin=new JLabel("LinkedIn");
        lblLinkedin.setBounds(30, 130, 100, 25);
        
        linkFacebook=new JLabel("Click here");
        linkFacebook.setBounds(140, 40, 100, 25);
        linkGoogle=new JLabel("Click here");
        linkGoogle.setBounds(140, 70, 100, 25);
        linkGithub=new JLabel("Click here");
        linkGithub.setBounds(140, 100, 100, 25);
        linkLinkedin=new JLabel("Click here");
        linkLinkedin.setBounds(140, 130, 100, 25);
        
        linkFacebook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkFacebook.setForeground(Color.blue);
        linkGoogle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkGoogle.setForeground(Color.blue);       
        linkGithub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkGithub.setForeground(Color.blue);       
        linkLinkedin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkLinkedin.setForeground(Color.blue);
        
        linkFacebook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {  
                    final URI uri=new URI("https://www.facebook.com/arif.mahmud.5494");
                    open(uri);
                } catch (URISyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "http not supported!");
                }
             }
        });
        linkGoogle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {  
                    final URI uri=new URI("https://plus.google.com/u/0/101345915864322133519/posts");
                    open(uri);
                } catch (URISyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "http not supported!");
                }
             }
        });
        linkGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {  
                    final URI uri=new URI("https://github.com/arif3hosain");
                    open(uri);
                } catch (URISyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "http not supported!");
                }
             }
        });
        linkLinkedin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {  
                    final URI uri=new URI("https://bd.linkedin.com/pub/arif-hosain/8b/128/86");
                    open(uri);
                } catch (URISyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "http not supported!");
                }
             }
        });
        panel.add(lblFacebook);
        panel.add(lblGithub);
        panel.add(lblLinkedin);
        panel.add(lblGoogleplus);
        panel.add(lblFacebook);
        panel.add(developed);
        panel.add(linkGithub);
        panel.add(linkLinkedin);
        panel.add(linkGoogle);
        panel.add(linkFacebook);
        panel.setLayout(null);
        setContentPane(panel);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public static void main(String[] args) {
        new About();
    }
    public void open(URI uri){
         if(Desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "http not found or not supported!");
            }
        }
    }
}   