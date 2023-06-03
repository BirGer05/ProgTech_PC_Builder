package Objects;

import Observer.Observer;
import Observer.Pc_Observer;

import java.util.ArrayList;
import java.util.List;

public class PC_object {
    private List<Pc_Observer> observers = new ArrayList<Pc_Observer>();
    private int alaplap_id,
            cpu_id,
            ram_id,
            gpu_id,
            hattertar_id,
            tap_id,
            gephaz_id;

    public PC_object(int alaplap_id, int cpu_id, int ram_id, int gpu_id, int hattertar_id, int tap_id, int gephaz_id) {
        this.alaplap_id = alaplap_id;
        this.cpu_id = cpu_id;
        this.ram_id = ram_id;
        this.gpu_id = gpu_id;
        this.hattertar_id = hattertar_id;
        this.tap_id = tap_id;
        this.gephaz_id = gephaz_id;
        //itt kell
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

    public void attach(Pc_Observer pcObserver) {
        observers.add(pcObserver);
    }
}
