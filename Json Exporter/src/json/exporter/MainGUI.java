
package json.exporter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

 
public class MainGUI {

   

    public MainGUI() {
       
        
        
        fieldPanel=new JPanel();     
        
        jsonEditor=new JTextArea("");
        pane=new JScrollPane(jsonEditor);
        pane.setBounds(0, 200, 450, 380);
        
       
        lblId=new JLabel("Field ID");
        lblTitle=new JLabel("Field Title");
        lblFieldType=new JLabel("Field Type");
       
        
        btnAdd=new JButton("Add");
        btnAdd.setEnabled(false);
        btnClear=new JButton("Reset");
        exportJson=new JButton("Export JSON");
        exportJson.setBounds(250, 590, 110, 30);
        exportJson.addActionListener( new ActionExporter());
        btnAdd.setBounds(30, 150, 70, 30);
         btnClear.setBounds(110, 150, 70, 30);
        fieldPanel.add(btnAdd);
        fieldPanel.add(btnClear);
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
        txtFieldName.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                 btnAdd.setEnabled(true);
            }
            /*);*/
            @Override
            public void keyPressed(KeyEvent e) {
             }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!(txtFieldName.getText().length() >0)){
                    btnAdd.setEnabled(false);
                }               
             }
        });
        typeCombo=new JComboBox(dataType);
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
        help.add(about);
        file.add(openSQL=new JMenuItem("Open SQL File"));
        file.add(saveTo=new JMenuItem("Export JSON"));
       
        menuBar.add(file);
        menuBar.add(help);
        frame.setJMenuBar(menuBar);
      
        btnAdd.addActionListener(new AddAction());
        btnClear.addActionListener(new ClearAction());
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
        frame.setSize(450, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
      
    }
    class AddAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {                                   
           
          field.add(txtId.getText());
          field.add(txtFieldName.getText());
          field.add(typeCombo.getSelectedItem());       
          String fieldData="{\n"+" \"fieldId\": "+txtId.getText()+",\n \"fieldName\": \""+field.get(1)+"\",\n \"fieldType\": \""+field.get(2)+"\"";
       
          if(required.isSelected() || minLength.isSelected() || maxLength.isSelected()){
             fieldData=fieldData+",\n \"fieldValidateRules\": [ \n";
             if(required.isSelected()){
                 fieldData=fieldData+" \"required\"";
             }
              if(minLength.isSelected()){
                 fieldData=fieldData+",\n \"minlength\"";
             }
               if(maxLength.isSelected()){
                 fieldData=fieldData+",\n \"maxlength\"\n]";
             }
                if(minLength.isSelected()){
                  fieldData=fieldData+",\n\"fieldValidateRulesMinlength\":\""+txtMinLength.getText()+"\"";
             }
               if(maxLength.isSelected()){
                 fieldData=fieldData+"\n\"fieldValidateRulesMaxlength\":\""+txtMaxLength.getText()+"\"";
             }             
             }

          fieldData=fieldData+"\n}";
          fieldList.add(fieldData);
          if(fieldList.size() >1)
              fieldData=",\n"+fieldData;          
          jsonEditor.append(fieldData);          
          fieldData="";
          field.clear();
          String jsonData=jsonEditor.getText();
           txtId.setText(Integer.toString(Integer.parseInt(txtId.getText())+1));
           txtFieldName.setText("");
           btnAdd.setEnabled(false);
           txtFieldName.requestFocus(true);
        
        
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
        
        public void exportToJson(){
            
        }
   }
    class ActionExporter implements ActionListener{           
        @Override
        public void actionPerformed(ActionEvent e) {
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
"\"relationships\": [],\n" +
"\"fields\": ["+
                             jsonEditor.getText()
                             +"\n],\n" +
"    \"changelogDate\":\"20150807153017\",\n" +
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
         }      
    }
    
    JFrame frame=new JFrame();
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem about;
    private JMenuItem openSQL;
    private JMenuItem saveTo;
    private JButton btnAdd,btnClear,exportJson;    
    private JLabel lblId,lblTitle,lblFieldType;
    private JTextField txtId,txtFieldName,txtMinLength,txtMaxLength;
    private JCheckBox required,minLength,maxLength;   
    private JPanel fieldPanel;
    private JTextArea jsonEditor;
    private JScrollPane pane;
    private JComboBox typeCombo;
    ArrayList<Object > field=new ArrayList<>();
    ArrayList fieldList=new ArrayList<>();
    int incrementField=1;
    int incrementRelationId=1;
    final String[] dataType={"String","LocalDate","Integer","BigDecimal","Double","byte[]"};
    private JFileChooser chooser;
    private File createFile;
    

}
