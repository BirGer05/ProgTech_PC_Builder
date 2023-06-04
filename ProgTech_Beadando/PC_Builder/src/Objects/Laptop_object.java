package Objects;

import DBconnection.DBconnection;
import Observer.Laptop_Observer;

import Observer.Pc_Observer;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.awt.image.RescaleOp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Laptop_object {
    private static Logger logger = Logger.getLogger("Getting compatibilty for laptop");
    private List<Laptop_Observer> observers = new ArrayList<Laptop_Observer>();
    private DBconnection dbConnection;
    private ResultSet isCompatible;
    private ArrayList<Integer> compatibleValue;

    public ArrayList<Integer> getCompatibleValue() {
        return compatibleValue;
    }

    public void setCompatibleValue(ArrayList<Integer> compatibleValue) {
        this.compatibleValue = compatibleValue;
    }

    private JFrame frame;
    private int alaplap_id,
            cpu_id,
            ram_id,
            gpu_id,
            hattertar_id,
            monitor_id,
            billentyuzet,
            touchpad;

    public Laptop_object(DBconnection dbConnection, int alaplap_id, int cpu_id, int ram_id, int gpu_id, int hattertar_id, int monitor_id, int billentyuzet, int touchpad) {
        this.alaplap_id = alaplap_id;
        this.cpu_id = cpu_id;
        this.ram_id = ram_id;
        this.gpu_id = gpu_id;
        this.hattertar_id = hattertar_id;
        this.monitor_id = monitor_id;
        this.billentyuzet = billentyuzet;
        this.touchpad = touchpad;
        this.dbConnection = dbConnection;
        compatibleValue = new ArrayList<Integer>();
    }

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

    public int getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(int monitor_id) {
        this.monitor_id = monitor_id;
    }

    public int getBillentyuzet() {
        return billentyuzet;
    }

    public void setBillentyuzet(int billentyuzet) {
        this.billentyuzet = billentyuzet;
    }

    public int getTouchpad() {
        return touchpad;
    }

    public void setTouchpad(int touchpad) {
        this.touchpad = touchpad;
    }

    public boolean Dependecy(){
        try {
            Laptop_object laptopObject = null;
            PreparedStatement compatible = dbConnection.getDbConnection().prepareStatement("SELECT if(a.foglalat=c.foglalat AND a.ram_tipus=r.ram_tipus,1,0) as Kompatibilis FROM alaplap a, cpu c, ram r WHERE a.id = ? AND c.id = ? AND r.id = ?");
            compatible.setInt(1, this.getAlaplap_id());
            compatible.setInt(2, this.getCpu_id());
            compatible.setInt(3, this.getRam_id());
            this.isCompatible = compatible.executeQuery();
            isCompatible.next();
            compatibleValue.add(isCompatible.getInt(1));
            if (compatibleValue.get(0) == 0) {
                notifyAllObservers();
                return false;
            }
            return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(frame,e.getMessage(),"HIBA",JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public void attach(Laptop_Observer laptopObserver) {
        observers.add(laptopObserver);
    }

    public void notifyAllObservers(){
        for (Laptop_Observer observer : observers) {
            observer.Notification();
        }
    }
}
