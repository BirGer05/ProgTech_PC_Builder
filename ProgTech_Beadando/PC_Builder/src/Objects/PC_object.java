package Objects;

import Observer.Observer;
import Observer.Pc_Observer;
import DBconnection.DBconnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PC_object {
    private List<Pc_Observer> observers = new ArrayList<Pc_Observer>();
    private int compatibleValue, alaplap_id,
            cpu_id,
            ram_id,
            gpu_id,
            hattertar_id,
            tap_id,
            gephaz_id;
    private ResultSet isCompatible;
    private JFrame frame;

    public PC_object(DBconnection dBconnection, int alaplap_id, int cpu_id, int ram_id, int gpu_id, int hattertar_id, int tap_id, int gephaz_id) {
        this.alaplap_id = alaplap_id;
        this.cpu_id = cpu_id;
        this.ram_id = ram_id;
        this.gpu_id = gpu_id;
        this.hattertar_id = hattertar_id;
        this.tap_id = tap_id;
        this.gephaz_id = gephaz_id;
        this.dBconnection = dBconnection;
    }
    private DBconnection dBconnection;
    public int getAlaplap_id() {
        return alaplap_id;
    }

    public void setAlaplap_id(int alaplap_id) {
        this.alaplap_id = alaplap_id;
    }

    public int getCpu_id() {
        return cpu_id;
    }

    public void setCpu_id(int cpu_id) {
        this.cpu_id = cpu_id;
    }

    public int getRam_id() {
        return ram_id;
    }

    public void setRam_id(int ram_id) {
        this.ram_id = ram_id;
    }

    public int getGpu_id() {
        return gpu_id;
    }

    public void setGpu_id(int gpu_id) {
        this.gpu_id = gpu_id;
    }

    public int getHattertar_id() {
        return hattertar_id;
    }

    public void setHattertar_id(int hattertar_id) {
        this.hattertar_id = hattertar_id;
    }

    public int getTap_id() {
        return tap_id;
    }

    public void setTap_id(int tap_id) {
        this.tap_id = tap_id;
    }

    public int getGephaz_id() {
        return gephaz_id;
    }

    public void setGephaz_id(int gephaz_id) {
        this.gephaz_id = gephaz_id;
    }
    public boolean Dependecy(){
        try {
            PC_object pcObject = null;
            PreparedStatement compatible = dBconnection.getDbConnection().prepareStatement("SELECT if(a.foglalat=c.foglalat AND a.ram_tipus=r.ram_tipus,1,0) as Kompatibilis FROM alaplap a, cpu c, ram r WHERE a.id = ? AND c.id = ? AND r.id = ?");
            compatible.setInt(1, this.getAlaplap_id());
            compatible.setInt(2, this.getCpu_id());
            compatible.setInt(3, this.getRam_id());
            this.isCompatible = compatible.executeQuery();
            this.isCompatible.next();
            pcObject.compatibleValue = isCompatible.getInt(1);
            if (compatibleValue == 0) {
                notifyAllObservers();
                return false;
            }

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(frame,e.getMessage(),"HIBA",JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public void attach(Pc_Observer pcObserver) {
        observers.add(pcObserver);
    }

    public void notifyAllObservers(){
        for (Pc_Observer observer : observers) {
            observer.Notification();
        }
    }
}
