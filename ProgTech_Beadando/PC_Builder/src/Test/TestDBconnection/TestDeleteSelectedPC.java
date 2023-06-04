package TestDBconnection;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.GetAllBuiltLaptopCommand;
import DBconnection.DeleteSelectedPcCommand;
import DBconnection.DeleteSelectedLaptopCommand;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class TestDeleteSelectedPC {
    private DBconnection dBconnection = new DBconnection(
            "jdbc:mysql://localhost:3306/pc_builder",
            "root",
            "");
    @Test
    public void Test_DeletePC() throws SQLException{
        int id = 5; //example for a value
        DeleteSelectedPcCommand deleteSelectedPcCommand = new DeleteSelectedPcCommand(
                id,
                this.dBconnection
        );
        String exMsg = id + "-t nem sikerült törölni";
        deleteSelectedPcCommand.execute();
        assertNotEquals(deleteSelectedPcCommand.getMsg(),exMsg);
        GetAllBuiltPcCommand getAllBuiltPcCommand = new GetAllBuiltPcCommand(this.dBconnection);
        getAllBuiltPcCommand.execute();
        assertFalse(getAllBuiltPcCommand.getPcIDlist().contains(id));
    }
}
