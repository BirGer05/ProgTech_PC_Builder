package Test.TestDBconnection;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.DeleteSelectedPcCommand;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class TestDeleteSelectedPC {
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
    public void Test_DeletePC() throws SQLException{
        int id = 5; //example for an existing value
        DeleteSelectedPcCommand getAllBuiltPcCommand = new DeleteSelectedPcCommand(
                id,
                this.dBconnection
        );
        getAllBuiltPcCommand.execute();
    }
}
