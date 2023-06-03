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

    public boolean IsBuilderWantsPC(Class o){
        return o.isInstance(newPC);
    }
    @Override
    public void execute() {
        try{
            /*if(!IsBuilderWantsPC(Objects.class)) {
                this.setResults(this.dBconnection.getDbConnection().createStatement().executeQuery("select * from alaplap, cpu, ram,gpu,hattertar,monitor,billlentyuzet,touchpad"));
                logger.info("Sikeres laptop alkatrészek lekérdezés!");
            }*/

                this.mobo = this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,foglalat,ram_tipus from alaplap");
                while (this.mobo.next()){
                    listOfMobos.add(this.mobo.getString("id"));
                    listOfMobos.add(this.mobo.getString("gyarto"));
                    listOfMobos.add(this.mobo.getString("foglalat"));
                    listOfMobos.add(this.mobo.getString("ram_tipus"));
                }
                this.cpu = this.dBconnection.getDbConnection().createStatement().executeQuery("select  id,gyarto,tipus,foglalat from cpu");
                while (this.cpu.next()){
                    listOfCPUs.add(this.cpu.getString("id"));
                    listOfCPUs.add(this.cpu.getString("gyarto"));
                    listOfCPUs.add(this.cpu.getString("tipus"));
                    listOfCPUs.add(this.cpu.getString("foglalat"));
                }
                this.ram = this.dBconnection.getDbConnection().createStatement().executeQuery("select  id,gyarto,ram_tipus from ram");
                while (this.ram.next()){
                    listOfRAMs.add(this.ram.getString("id"));
                    listOfRAMs.add(this.ram.getString("gyarto"));
                    listOfRAMs.add(this.ram.getString("ram_tipus"));
                }
                this.gpu = this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,vram from gpu");
                while(this.gpu.next()){
                    listOfGPUs.add(this.gpu.getString("id"));
                    listOfGPUs.add(this.gpu.getString("gyarto"));
                    listOfGPUs.add(this.gpu.getString("vram"));
                }
                this.storage = this.dBconnection.getDbConnection().createStatement().executeQuery("select id,gyarto,meret,ssd_e from hattertar");
                while (this.storage.next()){
                    listOfStorage.add(this.storage.getString("id"));
                    listOfStorage.add(this.storage.getString("gyarto"));
                    listOfStorage.add(this.storage.getString("meret"));
                    listOfStorage.add(this.storage.getString("ssd_e"));
                }
                logger.info("Sikeres PC alkatrészek lekérdezés!");
                logger.info(listOfMobos);
                logger.info(listOfCPUs);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            JOptionPane.showMessageDialog(frame,"Adatbázis hiba az alkatrészek lekérdezése esetén","HIBA",JOptionPane.ERROR_MESSAGE);
        }
    }
}
