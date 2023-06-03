package AddNew;

import DBconnection.DBconnection;
import DBconnection.GetAllComponentsCommand;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
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
    private JButton btn_beszur;
    private JFrame frame;
    private DBconnection dBconnection;

    public AddNew(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
        GetAll();
        ConfigureFrame();
        RegisterListeners();
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
    private void RegisterListeners(){
        btn_beszur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Insert();
            }
        });
    }
    private void GetAll(){
        GetAllComponentsCommand getAllComponentsCommand = new GetAllComponentsCommand(this.dBconnection);
        getAllComponentsCommand.execute();
        try{
            ResultSet mobo = getAllComponentsCommand.getMobo();
            logger.info(mobo.getString("gyarto"));
            while (mobo.next()){
                cb_alaplap.addItem(mobo.getString("gyarto"));
            }
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }
    }
}
