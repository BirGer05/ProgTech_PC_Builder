package AddNew;

import DBconnection.DBconnection;
import DBconnection.GetAllComponentsCommand;
import DBconnection.InsertNewLaptopCommand;
import DBconnection.InsertNewPcCommand;
import Objects.Laptop_object;
import Objects.PC_object;
import Observer.Laptop_Observer;
import Observer.Pc_Observer;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
                Insert();
            }
        });
        laptop_e.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent d) {
                if(isLaptop()){
                    cb_gephaz.setEnabled(false);
                    cb_tap.setEnabled(false);
                    cb_monitor.setEnabled(true);
                }
                else{
                    cb_gephaz.setEnabled(true);
                    cb_tap.setEnabled(true);
                    cb_monitor.setEnabled(false);
                }
            }
        });
    }
    private void GetAll(){
        GetAllComponentsCommand getAllComponentsCommand = new GetAllComponentsCommand(this.dBconnection);
        getAllComponentsCommand.execute();
        try{
            ResultSet mobo = getAllComponentsCommand.getMobo();
            while (mobo.next()){
                cb_alaplap.addItem(String.format("%d - %s %s %s",mobo.getInt("id"), mobo.getString("gyarto"), mobo.getString("foglalat"), mobo.getString("ram_tipus")));
            }
            ResultSet cpu = getAllComponentsCommand.getCpu();
            while (cpu.next()){
                cb_cpu.addItem(String.format("%d - %s %s %s",cpu.getInt("id"), cpu.getString("gyarto"), cpu.getString("tipus"), cpu.getString("foglalat")));
            }
            ResultSet ram = getAllComponentsCommand.getRam();
            while (ram.next()){
                cb_ram.addItem(String.format("%d - %s %s %s (GB)",ram.getInt("id"), ram.getString("gyarto"), ram.getString("ram_tipus"), ram.getString("meret")));
            }
            ResultSet gpu = getAllComponentsCommand.getGpu();
            while (gpu.next()){
                cb_gpu.addItem(String.format("%d - %s %s (GB)",gpu.getInt("id"), gpu.getString("gyarto"), gpu.getString("vram")));
            }
            ResultSet storage = getAllComponentsCommand.getStorage();
            while (storage.next()){
                cb_hattertar.addItem(String.format("%d - %s %s (GB) %s",storage.getInt("id"), storage.getString("gyarto"), storage.getString("meret"), storage.getString("ssd_e")));
            }
            ResultSet tap = getAllComponentsCommand.getTap();
            while (tap.next()){
                cb_tap.addItem(String.format("%d - %s %s (Watt)",tap.getInt("id"), tap.getString("gyarto"), tap.getString("teljesitmeny")));
            }
            ResultSet haz = getAllComponentsCommand.getHaz();
            while (haz.next()){
                cb_gephaz.addItem(String.format("%d - %s %s",haz.getInt("id"), haz.getString("gyarto"), haz.getString("nev")));
            }
            ResultSet monitor = getAllComponentsCommand.getMonitor();
            while (monitor.next()){
                cb_monitor.addItem(String.format("%d - %s %s'' %s",monitor.getInt("id"), monitor.getString("gyarto"), monitor.getString("atmero"), monitor.getString("felbontas")));
            }
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }
    }
    private void Insert(){
        if(isLaptop()){
            Laptop_object newLaptop = new Laptop_object(
                    this.dBconnection,
                    GetSelectedItemIDvalue(cb_alaplap),
                    GetSelectedItemIDvalue(cb_cpu),
                    GetSelectedItemIDvalue(cb_ram),
                    GetSelectedItemIDvalue(cb_gpu),
                    GetSelectedItemIDvalue(cb_hattertar),
                    GetSelectedItemIDvalue(cb_monitor),
                    1,
                    1
                    );
            newLaptop.attach(new Laptop_Observer(newLaptop));
            InsertNewLaptopCommand newLaptopCommand = new InsertNewLaptopCommand(newLaptop, this.dBconnection);
            if(newLaptop.Dependecy()) {
                newLaptopCommand.execute();
            }

        }
        else {
            PC_object newPc = new PC_object(
                    this.dBconnection,
                    GetSelectedItemIDvalue(cb_alaplap),
                    GetSelectedItemIDvalue(cb_cpu),
                    GetSelectedItemIDvalue(cb_ram),
                    GetSelectedItemIDvalue(cb_gpu),
                    GetSelectedItemIDvalue(cb_hattertar),
                    GetSelectedItemIDvalue(cb_tap),
                    GetSelectedItemIDvalue(cb_gephaz)
            );
            newPc.attach(new Pc_Observer(newPc));
            InsertNewPcCommand newPcCommand = new InsertNewPcCommand(newPc, this.dBconnection);
            if(newPc.Dependecy()) {
                newPcCommand.execute();
            }
        }
    }
    private boolean isLaptop(){
        return laptop_e.isSelected();
    }
    private int GetSelectedItemIDvalue(JComboBox jComboBox){
        String splitting = jComboBox.getSelectedItem().toString();
        String[] objects = splitting.split(" - ");
        int index = Integer.parseInt(objects[0]);
        return index;
    }
}
