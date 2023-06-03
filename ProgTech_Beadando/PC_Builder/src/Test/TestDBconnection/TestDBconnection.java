package Test.TestDBconnection;
import DBconnection.*;
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
    void TestConnection(){
        assertNotNull(dBconnection);
    }
    @Test
    void Test_GetAllBuiltPC() throws SQLException{
        int id = 2; //example for
        GetAllBuiltPcCommand getAllBuiltPcCommand = new GetAllBuiltPcCommand(this.dBconnection);
        getAllBuiltPcCommand.execute();
        assertTrue(getAllBuiltPcCommand);
    }
}
