package Observer;

import Objects.PC_object;

import javax.swing.*;

public class Pc_Observer extends Observer{
    public PC_object pc;
    private JFrame frame;

    public Pc_Observer(PC_object pc) {
        this.pc = pc;
        this.pc.attach(this);
    }

    @Override
    public void Notification() {
        JOptionPane.showMessageDialog(frame,"Nem egyeznek meg a foglalatok!","Nem Jou",JOptionPane.WARNING_MESSAGE);
    }
}
