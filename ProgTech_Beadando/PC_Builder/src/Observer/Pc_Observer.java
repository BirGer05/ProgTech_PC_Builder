package Observer;

import Objects.PC_object;

public class Pc_Observer extends Observer{
    public PC_object pc;

    public Pc_Observer(PC_object pc) {
        this.pc = pc;
        this.pc.attach(this);
    }

    @Override
    public boolean update() {
        return true;
    }
}
