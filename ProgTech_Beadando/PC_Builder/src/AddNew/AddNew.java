package AddNew;

import DBconnection.DBconnection;

import javax.swing.*;
import java.util.logging.Logger;

public class AddNew {
    private static Logger logger = Logger.getLogger("Show all components");
    private JComboBox cb_alaplap;
    private JComboBox cb_cpu;
    private JComboBox cb_ram;
    private JComboBox cb_gpu;
    private JComboBox cb_hattertar;
    private JComboBox cb_tap;
    private JComboBox cb_gephaz;
    private JPanel BuildPanel;
    private JFrame frame;
    private DBconnection dBconnection;

    public AddNew(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
        ConfigureFrame();
    }
    private void ConfigureFrame(){
        this.frame = new JFrame();
        frame.setContentPane(this.BuildPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
        logger.info("Show Build frame configured");
    }
}
