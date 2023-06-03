package Objects;

import Observer.Laptop_Observer;
import Observer.Pc_Observer;

import java.util.ArrayList;
import java.util.List;

public class Laptop_object {
    private List<Laptop_Observer> observers = new ArrayList<Laptop_Observer>();
    private int alaplap_id,
            cpu_id,
            ram_id,
            gpu_id,
            hattertar_id,
            monitor_id,
            billentyuzet,
            touchpad;

    public Laptop_object(int alaplap_id, int cpu_id, int ram_id, int gpu_id, int hattertar_id, int monitor_id, int billentyuzet, int touchpad) {
        this.alaplap_id = alaplap_id;
        this.cpu_id = cpu_id;
        this.ram_id = ram_id;
        this.gpu_id = gpu_id;
        this.hattertar_id = hattertar_id;
        this.monitor_id = monitor_id;
        this.billentyuzet = billentyuzet;
        this.touchpad = touchpad;
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

    public void Dependecy(){
        //ide kell az sql vizsgálat!!!
    }

    public void attach(Laptop_Observer laptopObserver) {
        observers.add(laptopObserver);
    }
}
