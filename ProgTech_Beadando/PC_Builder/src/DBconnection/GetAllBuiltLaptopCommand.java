package DBconnection;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllBuiltLaptopCommand implements Command{
    private Logger logger = Logger.getLogger("All built PC");
    private DBconnection dataBaseConnection;
    private ResultSet results;
    private ArrayList<String> listOfBuiltLaptops;
    private JFrame frame = new JFrame();
    public GetAllBuiltLaptopCommand(DBconnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
        listOfBuiltLaptops = new ArrayList<String>();
    }

    public ArrayList<String> getListOfBuiltLaptops() {
        return listOfBuiltLaptops;
    }

    @Override
    public void execute() {
        try{
            this.results = this.dataBaseConnection.getDbConnection().createStatement().executeQuery("select * from asztali_pc");
            while (this.results.next()){
                for(int i = 1 ; i<8;i++) {
                    listOfBuiltLaptops.add(this.results.getString(i));
                }
            }
            logger.info("Successful query execution!");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,e.getMessage(),"Adabázisbeli hiba az épített PC-k lekérdezésekor",JOptionPane.ERROR_MESSAGE);
        }
    }
}
