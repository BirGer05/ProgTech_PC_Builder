package DBconnection;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllBuiltLaptopCommand implements Command{
    private Logger logger = Logger.getLogger("All built PC");
    private DBconnection dataBaseConnection;
    private ResultSet results;
    private ResultSet LaptopID;

    public ResultSet getLaptopID() {
        return LaptopID;
    }

    public void setLaptopID(ResultSet laptopID) {
        LaptopID = laptopID;
    }

    private JFrame frame = new JFrame();
    private ArrayList<String> getLaptopID;

    public ArrayList<String> getGetLaptopID() {
        return getLaptopID;
    }

    public void setGetLaptopID(ArrayList<String> getLaptopID) {
        this.getLaptopID = getLaptopID;
    }

    public GetAllBuiltLaptopCommand(DBconnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
        getLaptopID = new ArrayList<String>();
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
                    "lap.id,\n"+
                    "concat(a.gyarto,\" (\",a.foglalat,\") \") as Alaplap,\n" +
                    "concat(c.gyarto,\" \",c.tipus) as CPU,\n" +
                    "concat(r.gyarto,\" (\",r.orajel, \"MHz)\") as RAM,\n" +
                    "gp.gyarto as GPU,\n" +
                    "concat(h.gyarto,\" (\",h.meret,\" GB)\",if(h.ssd_e = 0, ' HDD',' SSD')) as Hattertar,\n" +
                    "concat(m.gyarto,\" (\",m.atmero,\" col)\",m.felbontas) as Monitor,\n" +
                    "concat(billentyuzet), concat(touchpad)" +
                    "FROM laptop lap\n" +
                    "INNER JOIN alaplap a ON lap.alaplap_id = a.id\n" +
                    "INNER JOIN cpu c ON lap.cpu_id = c.id\n" +
                    "INNER JOIN ram r on lap.ram_id = r.id\n" +
                    "INNER JOIN gpu gp ON lap.gpu_id = gp.id\n" +
                    "INNER JOIN hattertar h ON lap.hattertar_id = h.id\n" +
                    "INNER JOIN monitor m ON lap.monitor_id = m.id\n");
            logger.info("Successful query execution!");
            this.LaptopID = this.dataBaseConnection.getDbConnection().createStatement().executeQuery("SELECT id from laptop");
            this.LaptopID.next();
            getLaptopID.add(LaptopID.getString(1));

        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,e.getMessage(),"Adabázisbeli hiba az épített laptopok lekérdezésekor",JOptionPane.ERROR_MESSAGE);
        }
    }
}
