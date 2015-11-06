
package json.exporter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Relationship extends JFrame{
 private JPanel panel;
 private  String [] type={"many-to-one", "one-to-many","many-to-many","one-to-one"};
 private JComboBox relations;
 private JLabel id,relationshipName,otherEntityName,relationshipType,otherEntityField,ownerSide;
 private JTextField txtId,txtRelationshipName,txtOtherEntityName,txtOtherEntityField,txtOwnerSide;
 private JButton btnAdd,btnDone;
 private int relationalFieldId=1;
 private ArrayList relationList=new ArrayList();
 private ArrayList attributeList;
 
    public Relationship() {
       
       panel=new JPanel();
       panel.setLayout(null);
       id=new JLabel("Relationship ID");
       id.setBounds(10, 20, 150, 20);
       relationshipName=new JLabel("Relationship Name");
       relationshipName.setBounds(10, 50, 150, 20);
       otherEntityName=new JLabel("Other Entity Name");
       otherEntityName.setBounds(10, 80, 150, 20);
       relationshipType=new JLabel("Relationship Type");
       relationshipType.setBounds(10, 110, 150, 20);
       otherEntityField=new JLabel("Other Entity Field");
       otherEntityField.setBounds(10, 140, 150, 20);
       ownerSide=new JLabel("OwnerSide");
       ownerSide.setBounds(10, 170, 150, 20);
       
       txtId=new JTextField(5);
       txtId.setText(Integer.toString(relationalFieldId));
       txtId.setBounds(170, 20, 30, 25);
       txtRelationshipName=new JTextField();
       txtRelationshipName.addKeyListener(new ActionRelationshipName());
       txtRelationshipName.setBounds(170, 50, 150, 25);
       txtOtherEntityName=new JTextField();
       txtOtherEntityName.setBounds(170, 80, 150, 25);      
       relations=new JComboBox(type);
       relations.setBounds(170, 110, 150, 25);
       txtOtherEntityField=new JTextField();
       txtOtherEntityField.setBounds(170, 140, 150, 25);
       txtOwnerSide=new JTextField();
       txtOwnerSide.setBounds(170, 170, 150, 25);
        
       btnAdd=new JButton("Add");
       btnAdd.setBounds(80, 210, 80, 25);
       btnAdd.addActionListener(new ActionAdd());
       btnDone=new JButton("Done");
       btnDone.setBounds(170, 210, 80, 25);
       panel.add(id);
       panel.add(txtId);
       panel.add(relationshipName);
       panel.add(txtRelationshipName);
       panel.add(otherEntityName);
       panel.add(txtOtherEntityName);
       panel.add(relationshipType);
       panel.add(txtOtherEntityField);
       panel.add(otherEntityField);
       panel.add(relations);
       panel.add(ownerSide);
       panel.add(txtOwnerSide);
       
       panel.add(btnAdd);
       panel.add(btnDone);
       txtId.setFocusable(false);
        txtRelationshipName.setFocusable(true);
        setContentPane(panel);
        setSize(350, 350);
        setLocationRelativeTo(null);
        setTitle("Set Relationship");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
  
    public static void main(String[] args) {
        new Relationship();
    }
    class ActionRelationshipName extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
           txtOtherEntityName.setText(txtRelationshipName.getText());
        }
        
    }
    class ActionAdd implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            attributeList=new ArrayList();
            attributeList.add(txtId.getText());
            attributeList.add(txtRelationshipName.getText());
            attributeList.add(txtOtherEntityName.getText());
            attributeList.add(relations.getSelectedItem());
            attributeList.add(txtOtherEntityField.getText());
            attributeList.add(ownerSide.getText());
            relationList.add(attributeList);     
            
            relationalFieldId++;          
            txtId.setText(Integer.toString(relationalFieldId));
            txtRelationshipName.setText("");
            txtId.setFocusable(true);
            txtRelationshipName.requestFocus(true);
            txtOtherEntityName.setText("");
            txtOwnerSide.setText("");
            txtOtherEntityField.setText("");
            
        }
        
    }
}