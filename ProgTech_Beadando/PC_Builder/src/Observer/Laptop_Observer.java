package Observer;

import Objects.Laptop_object;
import Objects.PC_object;

import javax.swing.*;

public class Laptop_Observer extends Observer{
    public Laptop_object laptop;
    private JFrame frame;

    public Laptop_Observer(Laptop_object laptop) {
        this.laptop = laptop;
        this.laptop.attach(this);
    }

    @Override
    public void Notification() {
        JOptionPane.showMessageDialog(frame,"Nem egyeznek meg a foglalatok!","Nem Jou",JOptionPane.WARNING_MESSAGE);
    }
}
