
package json.exporter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import static json.exporter.Relationship.relationList;

    //class MainGUI
public class MainGUI {
            //default constructor MainGUI
    public MainGUI() {                   
        fieldPanel=new JPanel();             
        jsonEditor=new JTextArea("");
        pane=new JScrollPane(jsonEditor);
        pane.setBounds(0, 200, 450, 380);               
        lblId=new JLabel("Field ID");
        lblTitle=new JLabel("Field Title");
        lblFieldType=new JLabel("Field Type");             
        btnAdd=new JButton("Add");
        btnRelation=new JButton("Relation");
        btnAdd.setEnabled(false);
        btnClear=new JButton("Reset");
        exportJson=new JButton("Export Entity");
        exportJson.setBounds(250, 590, 110, 30);
        exportJson.addActionListener( new ActionExporter());
        btnAdd.setBounds(30, 150, 70, 30);
        btnClear.setBounds(110, 150, 70, 30);
        btnRelation.setBounds(180, 150, 80, 30);
        fieldPanel.add(btnAdd);
        fieldPanel.add(btnClear);
        fieldPanel.add(btnRelation);
        btnRelation.addActionListener(new ActionRelation());
        required=new JCheckBox("Required");
        minLength=new JCheckBox("Min");
        maxLength=new JCheckBox("Max");
        maxLength.setSelected(true);
        
        minLength.setBounds(130, 120, 50, 20);
        fieldPanel.add(minLength);
        maxLength.setBounds(230, 120, 50, 20);
        fieldPanel.add(maxLength);
        
        required.setBounds(250, 22, 100, 20);
        required.setSelected(true);
        fieldPanel.add(required);
        
        txtId=new JTextField(10);
        txtId.setText(Integer.toString(incrementField));
        txtFieldName=new JTextField(10);
        txtFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((txtFieldName.getText().length() >0)){
                    btnAdd.setEnabled(true);
                     if(minLength.isSelected()){
                      if(txtMaxLength.getText() ==null){
                        btnAdd.setEnabled(false);
                     }
                   }if(maxLength.isSelected()){
                    if(txtMaxLength.getText() ==null){
                        btnAdd.setEnabled(false);
                    }
            }
                }else{
                    btnAdd.setEnabled(false);
                }      
                
             }//end method
        });
        typeCombo=new JComboBox(dataType);
                                        //item listener will work when user change selection of item.
        typeCombo.addItemListener(new ItemListener() {           
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object selectedItem=typeCombo.getSelectedItem();
                if(selectedItem.equals("String")){
                    setTrue();
                }else if(selectedItem.equals("LocalDate")){
                    setFalse();
                }else if(selectedItem.equals("Integer")){
                   setTrue();
                }else if(selectedItem.equals("BigDecimal")){
                    setFalse();
                }else if(selectedItem.equals("Double")){
                    setFalse();
                }
                else if(selectedItem.equals("byte[]")){
                  setFalse();
                }
              }
        });
        minLength.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(minLength.isSelected()){
                    txtMinLength.setEnabled(true);
                    required.setSelected(true);
                    if(txtMinLength.getText().equals("") || txtMinLength.getText()==null || txtFieldName.getText()==null)
                  btnAdd.setEnabled(false);
                    else
                        btnAdd.setEnabled(true);
                }
                if(!minLength.isSelected()){
                    if(txtFieldName.getText() !=null){
                        btnAdd.setEnabled(true);
                        txtMinLength.setEnabled(false);
                    }
                } 
            }
        });
       
        txtMinLength=new JTextField(10);
        txtMaxLength=new JTextField(5);
        
        txtMinLength.setBounds(180, 120, 40, 25);
        txtMinLength.setEnabled(true);
        fieldPanel.add(txtMinLength);
        txtMaxLength.setBounds(280, 120, 40, 25);
        txtMaxLength.setText("45");
       
        fieldPanel.add(txtMaxLength);        
        lblId.setBounds(20, 20, 100, 20);
        fieldPanel.add(lblId);      
        lblTitle.setBounds(20, 50, 100, 20);
        fieldPanel.add(lblTitle);        
        lblFieldType.setBounds(20, 80, 100, 20);
        fieldPanel.add(lblFieldType);              
        txtId.setBounds(130, 20, 100, 30);
        fieldPanel.add(txtId);      
        txtFieldName.setBounds(130, 50, 250, 30);
        fieldPanel.add(txtFieldName);
        
        typeCombo.setBounds(130, 80, 250, 30);
        fieldPanel.add(typeCombo);
        
        
        
        fieldPanel.setLayout(null);
        fieldPanel.setSize(500, 200);
        frame.add(fieldPanel);
        frame.add(pane);
        frame.add(exportJson,BorderLayout.NORTH);
        menuBar=new JMenuBar();
        file=new JMenu("File");
        help=new JMenu("Help");
        about=new JMenuItem("About");
        about.addActionListener(new ActionAbout());
        
        help.add(about);
        file.add(openSQL=new JMenuItem("Open SQL File"));
        file.add(saveTo=new JMenuItem("Export JSON"));
        changeLog=new JMenuItem("Change Log");
        file.add(changeLog);
        menuBar.add(file);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);
      
        btnAdd.addActionListener(new AddAction());
        btnClear.addActionListener(new ClearAction());
       
        changeLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    changeLogId=JOptionPane.showInputDialog("Changelog ID", "");
             }
        });
        
         txtMinLength.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(required.isSelected()){
                     if(txtMinLength.getText().length()>0){
                      btnAdd.setEnabled(true);
                  }
                  else
                      btnAdd.setEnabled(false);
                } 
                
             }
        });       
  
    }             
    public static void main(String[] args) {
                    try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // If Nimbus is not available, you can set the GUI to another look and feel.
            }
                      new MainGUI().displayGUI();               
    }
    
    public void displayGUI(){
        
        frame.setLayout(null);
        frame.setSize(450, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("JSON Exporter");
    }
    class AddAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {                                   
           
          field.add(txtId.getText());
          field.add(txtFieldName.getText());
          field.add(typeCombo.getSelectedItem());       
          String fieldData="{"+"\n \"fieldId\": "+txtId.getText()+",\n \"fieldName\": \""+field.get(1)+"\",\n \"fieldType\": \""+field.get(2)+"\"";
       
          if(required.isSelected() || minLength.isSelected() || maxLength.isSelected()){
             fieldData=fieldData+",\n \"fieldValidateRules\": [ \n";
             if(required.isSelected()){
                 fieldData=fieldData+" \"required\"";
             }if(minLength.isSelected() && !required.isSelected()){
                 fieldData=fieldData+"\"minlength\"\n]";
             }else if(minLength.isSelected() && required.isSelected()){
                  fieldData=fieldData+",\n  \"minlength\"";
             }             
               if(maxLength.isSelected()){
                   if(!required.isSelected() && !minLength.isSelected()){
                       fieldData=fieldData+"\"maxlength\"\n]";
                   }else
                 fieldData=fieldData+",\n \"maxlength\"\n]";
             }
                if(minLength.isSelected()){
                  fieldData=fieldData+",\n\"fieldValidateRulesMinlength\":\""+txtMinLength.getText()+"\"";
             }
               if(maxLength.isSelected()){
                 fieldData=fieldData+",\n\"fieldValidateRulesMaxlength\":\""+txtMaxLength.getText()+"\"";
             }             
             }

          fieldData=fieldData+"\n}";
          fieldList.add(fieldData);
          if(fieldList.size() >1)
              fieldData=","+fieldData;          
          jsonEditor.append(fieldData);          
          fieldData="";
          field.clear();
          String jsonData=jsonEditor.getText();
           txtId.setText(Integer.toString(Integer.parseInt(txtId.getText())+1));
           txtFieldName.setText("");
           btnAdd.setEnabled(false);
           txtFieldName.requestFocus(true); 
          exportJson.setEnabled(true);
      }
       
    }
    class ClearAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            required.setSelected(false);
            maxLength.setSelected(false);
            minLength.setSelected(false);
            txtFieldName.setText("");
            txtMinLength.setText("");
            txtMaxLength.setText("");
            
        }          
   }
    class ActionExporter implements ActionListener{           
        @Override
        public void actionPerformed(ActionEvent e) {
            exportJson.setEnabled(false);
            String relationField="";
            relations=Relationship.relationList;
            for (int i = 0; i < relationList.size(); i++) {
             ArrayList  list= new ArrayList();
                list.add(relationList.get(i));
                  for (int j = 0; j < list.size(); j++) {
                     Object data=list.get(j).toString();
                       relationField+=data.toString();            
                  }
                  if(relationList.size()>1){
                      if((i+1)==relationList.size()){
                       relationField="{\n"+relationField.substring(1, relationField.length()-1)+"\n}\n"; 
                      }else
                        relationField="{\n"+relationField.substring(1, relationField.length()-1)+"\n},\n"; 
                  }else{
                        relationField="{\n"+relationField.substring(1, relationField.length()-1)+"\n}\n"; 
                  }
                  fullRelationship+=relationField;
                  relationField="";
              }
            
            
            chooser=new JFileChooser();
            chooser.setDialogTitle("Save Json File");
    
            int choose=chooser.showSaveDialog(null);
            if(choose==JFileChooser.APPROVE_OPTION){
                
                String filename=chooser.getSelectedFile().getName();
                String directory=chooser.getCurrentDirectory().toString();
                createFile=new File(directory+"/"+filename+".json");
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(createFile);
                     byte[] jsonBytes=(" {\n" +
"\"relationships\": [\n"+fullRelationship+"],\n" +"\"fields\": ["+
                             jsonEditor.getText()
                             +"\n],\n" +
"    \"changelogDate\":\""+changeLogId+"\",\n" +
"    \"dto\": \"no\",\n" +
"    \"pagination\":\"pagination\"\n" +
"}"
                             ).getBytes();
                        fos.write(jsonBytes);
                        fos.flush();
                    jsonEditor.setText("");
                    txtId.setText("1");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
                }                   
                              
            }
             fullRelationship="";
         }      
    }
    class ActionRelation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             new Relationship();
        }
        
    }
      class ActionAbout implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new About();
        }
        
    }
    public void setFalse(){
        minLength.setEnabled(false);
        maxLength.setEnabled(false);
        txtMinLength.setEnabled(false);
        txtMaxLength.setEnabled(false);
    }
    public void setTrue(){
        minLength.setEnabled(true);
        maxLength.setEnabled(true);
        txtMinLength.setEnabled(true);
        txtMaxLength.setEnabled(true);
    }
    
    JFrame frame=new JFrame();
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem about,openSQL,saveTo,changeLog;
    private JButton btnAdd,btnClear,exportJson,btnRelation;    
    private JLabel lblId,lblTitle,lblFieldType;
    private JTextField txtId,txtFieldName,txtMinLength,txtMaxLength;
    private JCheckBox required,minLength,maxLength;   
    private JPanel fieldPanel;
    private JTextArea jsonEditor;
    private JScrollPane pane;
    private JComboBox typeCombo;
    ArrayList<Object > field=new ArrayList<>();
    ArrayList fieldList=new ArrayList<>();
    ArrayList<String> relations;
    int incrementField=1;
    int incrementRelationId=1;
    final String[] dataType={"String","LocalDate","Integer","BigDecimal","Double","byte[]"};
    private JFileChooser chooser;
    private File createFile;
    private String changeLogId="20150807153017";
    private String fullRelationship="";

}
