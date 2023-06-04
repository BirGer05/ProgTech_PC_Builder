package DBconnection;
import Objects.Laptop_object;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.PreparedStatement;

public class InsertNewLaptopCommand implements Command{
    private static Logger logger = Logger.getLogger("Insert new laptop");
    private Laptop_object newLaptop;
    private JFrame frame = new JFrame();
    private DBconnection dBconnection;

    public InsertNewLaptopCommand(Laptop_object newLaptop,DBconnection dBconnection) {
        this.newLaptop = newLaptop;
        this.dBconnection = dBconnection;
    }

    @Override
    public void execute() {
        try{
            PreparedStatement insertLaptop = this.dBconnection.getDbConnection().prepareStatement("insert into laptop(alaplap_id,cpu_id,ram_id,gpu_id,hattertar_id,monitor_id,billentyuzet,touchpad)" +
                    "VALUES (?,?,?,?,?,?,?,?)");
            insertLaptop.setInt(1,newLaptop.getAlaplap_id());
            insertLaptop.setInt(2,newLaptop.getCpu_id());
            insertLaptop.setInt(3,newLaptop.getRam_id());
            insertLaptop.setInt(4,newLaptop.getGpu_id());
            insertLaptop.setInt(5,newLaptop.getHattertar_id());
            insertLaptop.setInt(6,newLaptop.getMonitor_id());
            insertLaptop.setInt(7,newLaptop.getBillentyuzet());
            insertLaptop.setInt(8,newLaptop.getTouchpad());
            insertLaptop.executeUpdate();
            logger.info("Új laptop került az adatbázisba");
            JOptionPane.showMessageDialog(frame,"Új laptop került az adatbázisba","Info",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,"Hiba történt az új laptop felvitelekor","HIBA",JOptionPane.ERROR_MESSAGE);
        }
    }
}
