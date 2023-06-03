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
    private JComboBox cb_monitor;
    private JPanel BuildPanel;
    private JButton btn_beszur;
    private JCheckBox laptop_e;
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
            while (mobo.next()){
                cb_alaplap.addItem(String.format("%s %s %s", mobo.getString("gyarto"), mobo.getString("foglalat"), mobo.getString("ram_tipus")));
            }
            ResultSet cpu = getAllComponentsCommand.getCpu();
            while (cpu.next()){
                cb_cpu.addItem(String.format("%s %s %s", cpu.getString("gyarto"), cpu.getString("tipus"), cpu.getString("foglalat")));
            }
            ResultSet ram = getAllComponentsCommand.getRam();
            while (ram.next()){
                cb_ram.addItem(String.format("%s %s %s (GB)", ram.getString("gyarto"), ram.getString("ram_tipus"), ram.getString("meret")));
            }
            ResultSet gpu = getAllComponentsCommand.getGpu();
            while (gpu.next()){
                cb_gpu.addItem(String.format("%s %s (GB)", gpu.getString("gyarto"), gpu.getString("vram")));
            }
            ResultSet storage = getAllComponentsCommand.getStorage();
            while (storage.next()){
                cb_hattertar.addItem(String.format("%s %s (GB) %s", storage.getString("gyarto"), storage.getString("meret"), storage.getString("ssd_e")));
            }
            ResultSet tap = getAllComponentsCommand.getTap();
            while (tap.next()){
                cb_tap.addItem(String.format("%s %s (Watt)", tap.getString("gyarto"), tap.getString("teljesitmeny")));
            }
            ResultSet haz = getAllComponentsCommand.getHaz();
            while (haz.next()){
                cb_gephaz.addItem(String.format("%s %s", haz.getString("gyarto"), haz.getString("nev")));
            }
            ResultSet monitor = getAllComponentsCommand.getMonitor();
            while (monitor.next()){
                cb_monitor.addItem(String.format("%s %s'' %s", monitor.getString("gyarto"), monitor.getString("atmero"), monitor.getString("felbontas")));
            }
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }
    }
}
