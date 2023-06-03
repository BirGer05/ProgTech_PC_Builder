package DBconnection;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.sql.PreparedStatement;

public class DeleteSelectedPcCommand implements Command{
    private Logger logger = Logger.getLogger("Delete selected PC");
    private DBconnection dataBaseConnection;
    private int id;
    private JFrame frame = new JFrame();
    public DeleteSelectedPcCommand(int id, DBconnection dataBaseConnection) {
        this.id = id;
        this.dataBaseConnection = dataBaseConnection;
    }

    @Override
    public void execute() {
        try{
            PreparedStatement delSelected = this.dataBaseConnection.getDbConnection().prepareStatement("Delete from asztali_pc where id=?");
            delSelected.setInt(1,this.id);
            int delCount = delSelected.executeUpdate();
            if(delCount==0){
                logger.error("Delete wasn't successful");
                JOptionPane.showMessageDialog(frame,this.id+"-t nem sikerült törölni","Hiba a törléskor",JOptionPane.WARNING_MESSAGE);
            }
            else{
                logger.info("Delete was successful");
                JOptionPane.showMessageDialog(frame,this.id+"-val ellátot a mindennapjaintak megkönnyítő eszközt sikerült törölni","Hiba a törléskor",JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,e.getMessage(),"Hiba történt a kiválasztott PC törlésekor",JOptionPane.ERROR_MESSAGE);
        }
    }
}
