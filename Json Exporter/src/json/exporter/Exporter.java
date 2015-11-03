
package json.exporter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author arifhosain
 */
public class Exporter {

    public Exporter() {
        JFileChooser fc=new JFileChooser();
        fc.showSaveDialog(null);
    }
    public static void main(String[] args) {
        new Exporter();
    }
    
}
