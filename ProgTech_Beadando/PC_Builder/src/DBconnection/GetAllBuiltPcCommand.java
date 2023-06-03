package DBconnection;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllBuiltPcCommand implements Command{
    private Logger logger = Logger.getLogger("All built PC");
    private DBconnection dataBaseConnection;
    private ResultSet results;
    private JFrame frame = new JFrame();
    public GetAllBuiltPcCommand(DBconnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    public ResultSet getResults() {
        return results;
    }

    public void setResults(ResultSet results) {
        this.results = results;
    }
    @Override
    public void execute() {
        try{
            this.results = this.dataBaseConnection.getDbConnection().createStatement().executeQuery("SELECT\n" +
                    "pc.id,\n"+
                    "concat(a.gyarto,\" (\",a.foglalat,\") \") as Alaplap,\n" +
                    "concat(c.gyarto,\" \",c.tipus) as CPU,\n" +
                    "concat(r.gyarto,\" \",r.ram_tipus, \" (\",r.orajel, \"MHz)\") as RAM,\n" +
                    "gp.gyarto as GPU,\n" +
                    "concat(h.gyarto,\" (\",h.meret,\" GB)\",if(h.ssd_e = 0, ' HDD',' SSD')) as Hattertar,\n" +
                    "concat(t.gyarto,\" (\",t.teljesitmeny,\" W)\") as Tap,\n" +
                    "gh.gyarto as Gephaz\n" +
                    "FROM asztali_pc pc\n" +
                    "INNER JOIN alaplap a ON pc.alaplap_id = a.id\n" +
                    "INNER JOIN cpu c ON pc.cpu_id = c.id\n" +
                    "INNER JOIN ram r on pc.ram_id = r.id\n" +
                    "INNER JOIN gpu gp ON pc.gpu_id = gp.id\n" +
                    "INNER JOIN hattertar h ON pc.hattertar_id = h.id\n" +
                    "INNER JOIN tap t ON pc.tap_id = t.id\n" +
                    "INNER JOIN gephaz gh ON pc.gephaz_id = gh.id");
            logger.info("Successful query execution!");

        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,e.getMessage(),"Adabázisbeli hiba az épített PC-k lekérdezésekor",JOptionPane.ERROR_MESSAGE);
        }
    }
}
