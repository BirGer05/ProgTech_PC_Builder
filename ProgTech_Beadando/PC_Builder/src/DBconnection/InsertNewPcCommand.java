package DBconnection;
import org.apache.log4j.Logger;
import Objects.PC_object;

import javax.swing.*;
import java.sql.PreparedStatement;

public class InsertNewPcCommand implements Command{
    private static Logger logger = Logger.getLogger("Insert new PC");
    private PC_object newPC;
    private JFrame frame = new JFrame();
    private DBconnection dBconnection;

    public InsertNewPcCommand(PC_object newPC_object, DBconnection dBconnection) {
        this.newPC = newPC_object;
        this.dBconnection = dBconnection;
    }

    @Override
    public void execute() {
        try{
            PreparedStatement insertPc = this.dBconnection.getDbConnection().prepareStatement("insert into asztali_pc(alaplap_id,cpu_id,ram_id,gpu_id,hattertar_id,tap_id,gephaz_id)" +
                    "VALUES (?,?,?,?,?,?,?)");
            insertPc.setInt(1,newPC.getAlaplap_id());
            insertPc.setInt(2,newPC.getCpu_id());
            insertPc.setInt(3,newPC.getRam_id());
            insertPc.setInt(4,newPC.getGpu_id());
            insertPc.setInt(5,newPC.getHattertar_id());
            insertPc.setInt(6,newPC.getTap_id());
            insertPc.setInt(7,newPC.getGephaz_id());
            insertPc.executeUpdate();
            logger.info("Új számító került az adatbázisba");
            JOptionPane.showMessageDialog(frame,"Új számító került az adatbázisba","Info",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,"Adatbázis hiba az új számító beszúrása esetén","HIBA",JOptionPane.ERROR_MESSAGE);
        }
    }
}
