package DBconnection;
import org.apache.log4j.Logger;
import Objects.PC_object;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class GetAllComponentsCommand implements Command{
    private static Logger logger = Logger.getLogger("Getting all the components");
    private PC_object newPC;
    private ResultSet mobo;
    private ResultSet cpu;
    private ResultSet ram;
    private ResultSet gpu;
    private ResultSet storage;
    private ResultSet haz;
    private  ResultSet tap;
    private ResultSet monitor;

    public ResultSet getMobo() {
        return mobo;
    }

    public ResultSet getCpu() {
        return cpu;
    }

    public ResultSet getRam() {
        return ram;
    }

    public ResultSet getGpu() {
        return gpu;
    }

    public ResultSet getStorage() {
        return storage;
    }

    public ResultSet getTap() { return tap; }

    public ResultSet getHaz() { return haz; }

    public ResultSet getMonitor() { return monitor; }

    private JFrame frame = new JFrame();
    private DBconnection dBconnection;
    private ArrayList<String> listOfMobos;
    private ArrayList<String> listOfCPUs;
    private ArrayList<String> listOfRAMs;
    private ArrayList<String> listOfGPUs;
    private ArrayList<String> listOfStorage;

    public ArrayList<String> getListOfStorage() {
        return listOfStorage;
    }

    public ArrayList<String> getListOfMobos() {
        return listOfMobos;
    }

    public ArrayList<String> getListOfCPUs() {
        return listOfCPUs;
    }

    public ArrayList<String> getListOfRAMs() {
        return listOfRAMs;
    }

    public ArrayList<String> getListOfGPUs() {
        return listOfGPUs;
    }

    public GetAllComponentsCommand(DBconnection dBconnection) {
        this.dBconnection = dBconnection;
        listOfMobos = new ArrayList<String>();
        listOfCPUs = new ArrayList<String>();
        listOfRAMs = new ArrayList<String>();
        listOfGPUs = new ArrayList<String>();
        listOfStorage = new ArrayList<String>();
    }

    public void setMobo(ResultSet mobo) {
        this.mobo = mobo;
    }

    public void setCpu(ResultSet cpu) {
        this.cpu = cpu;
    }

    public void setRam(ResultSet ram) {
        this.ram = ram;
    }

    public void setGpu(ResultSet gpu) {
        this.gpu = gpu;
    }

    public void setStorage(ResultSet storage) {
        this.storage = storage;
    }

    public void setHaz(ResultSet haz) {
        this.haz = haz;
    }

    public void setTap(ResultSet tap) {
        this.tap = tap;
    }

    public void setMonitor(ResultSet monitor) {
        this.monitor = monitor;
    }

    @Override
    public void execute() {
        try{
                this.setMobo(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,foglalat,ram_tipus from alaplap"));

                this.setCpu(this.dBconnection.getDbConnection().createStatement().executeQuery("select  id,gyarto,tipus,foglalat from cpu"));

                this.setRam(this.dBconnection.getDbConnection().createStatement().executeQuery("select  id,gyarto,ram_tipus,meret from ram"));

                this.setGpu(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,vram from gpu"));

                this.setStorage(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,meret,if(ssd_e = 0, 'HDD', 'SSD') as ssd_e from hattertar"));

                this.setHaz(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,nev from gephaz"));

                this.setTap(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,teljesitmeny,modularis_e from tap"));

                this.setMonitor(this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,atmero,felbontas,hdmi_szama,dp_szama,dvi_szama,integralt_e from monitor"));
                logger.info("Sikeres PC alkatrészek lekérdezés!");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,"Adatbázis hiba az alkatrészek lekérdezése esetén","HIBA",JOptionPane.ERROR_MESSAGE);
        }
    }
}
