package TestDBconnection;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.GetAllBuiltLaptopCommand;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDBconnection {
    private DBconnection dBconnection = new DBconnection(
            "jdbc:mysql://localhost:3306/pc_builder",
            "root",
            "");
    @Test
    public void TestConnection(){
        DBconnection testDB =  new DBconnection("jdbc:mysql://localhost:3306/pc_builder",
        "root",
                "");
        assertNotNull(testDB);
    }
    @Test
    public void Test_GetAPC() throws SQLException{
        String id = "2"; //example for an existing value
        GetAllBuiltPcCommand getAllBuiltPcCommand = new GetAllBuiltPcCommand(this.dBconnection);
        getAllBuiltPcCommand.execute();
        assertTrue(getAllBuiltPcCommand.getListOfBuiltPCs().contains(id));
    }
    @Test
    public void Test_GetALaptop() throws SQLException{
        String id = "1"; //example for an existing value
        GetAllBuiltLaptopCommand getAllBuiltLaptopCommand = new GetAllBuiltLaptopCommand(this.dBconnection);
        getAllBuiltLaptopCommand.execute();
        assertTrue(getAllBuiltLaptopCommand.getGetLaptopID().contains(id));
    }
}
