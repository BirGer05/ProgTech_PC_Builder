package TestObserver;
import DBconnection.DBconnection;
import DBconnection.GetAllBuiltPcCommand;
import DBconnection.GetAllBuiltLaptopCommand;
import Objects.Laptop_object;
import Objects.PC_object;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    private DBconnection dBconnection = new DBconnection(
            "jdbc:mysql://localhost:3306/pc_builder",
            "root",
            "");

    @Test
    public void PcObserverTest(){
        PC_object newPc = new PC_object(dBconnection,1,1,1,1,1,1,1); //test adat
        if (newPc.Dependecy()){
            newPc.Dependecy();
            System.out.println("Az alkatrész foglalatok megegyeznek!");
        }
        else{
            newPc.Dependecy();
            System.out.println("Nem egyeznek meg a foglalatok!");
        }
    }

    @Test
    public void LaptopObserverTest(){
        Laptop_object newLaptop = new Laptop_object(dBconnection,1,1,1,1,1,1,1,1); //test adat
        if (newLaptop.Dependecy()){
            newLaptop.Dependecy();
            System.out.println("Az alkatrész foglalatok megegyeznek!");
        }
        else{
            newLaptop.Dependecy();
            System.out.println("Nem egyeznek meg a foglalatok!");
        }
    }
}