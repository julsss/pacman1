package graphique;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Fenetre extends JFrame{

  JPanel content = new JPanel();
  

  public Fenetre(){
    this.setTitle("PackMan");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    content.setBackground(Color.red);
    content.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
    this.setContentPane(content);

    JPanel jeu = new JPanel();
    jeu.setPreferredSize(new Dimension(this.getWidth(),9*this.getHeight()/10));
    jeu.setBackground(Color.black);	
    this.setLayout(new BorderLayout());
    content.add(jeu,"North");
    
    JPanel info = new JPanel();
    info.setSize(new Dimension(this.getWidth(), this.getHeight()/10));
    info.setBackground(Color.blue);		
    content.add(info,"South");
    
    JPanel vie = new JPanel();
    vie.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()/10));
    vie.setBackground(Color.black);		
    info.setLayout(new BorderLayout());
    vie.setLayout(new BorderLayout());
    JLabel life = new JLabel(" LIFE : ");
    life.setFont(new Font("Verdana",50,40));
    life.setForeground(Color.yellow);
    vie.add(life,"West");
    vie.setBorder(new LineBorder(Color.yellow));
    info.add(vie,"East");
 
    
    JPanel score = new JPanel();
    score.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()/10));
    score.setBackground(Color.black);
    score.setLayout(new BorderLayout());
    JLabel point = new JLabel(" SCORE : ");
    point.setFont(new Font("Verdana",50,40));
    point.setForeground(Color.yellow);
    score.add(point,"West");
    score.setBorder(new LineBorder(Color.yellow));
    info.add(score,"West");
    
    
    this.setVisible(true);

  }	
}