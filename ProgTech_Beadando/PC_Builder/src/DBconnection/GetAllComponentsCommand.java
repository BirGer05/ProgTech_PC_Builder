package DBconnection;
import org.apache.log4j.Logger;
import Objects.PC_object;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.Objects;

public class GetAllComponentsCommand implements Command{
    private static Logger logger = Logger.getLogger("Getting all the components");
    private PC_object newPC;
    private ResultSet results;
    private JFrame frame = new JFrame();
    private DBconnection dBconnection;

    public GetAllComponentsCommand(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
    }

    public ResultSet getResults() {
        return results;
    }

    public void setResults(ResultSet results) {
        this.results = results;
    }

    private boolean IsBuilderWantsPC(Class o){
        return o.isInstance(newPC);
    }
    @Override
    public void execute() {
        try{
            if(!IsBuilderWantsPC(Objects.class)) {
                this.setResults(this.dBconnection.getDbConnection().createStatement().executeQuery("select * from alaplap, cpu, ram,gpu,hattertar,monitor,billlentyuzet,touchpad"));
                logger.info("Sikeres laptop alkatrészek lekérdezés!");
            }
            this.setResults(this.dBconnection.getDbConnection().createStatement().executeQuery("select * from alaplap, cpu, ram,gpu,hattertar,tap,gephaz"));
            logger.info("Sikeres PC alkatrészek lekérdezés!");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,"Adatbázis hiba az alkatrészek lekérdezése esetén","HIBA",JOptionPane.ERROR_MESSAGE);
        }
    }
}
