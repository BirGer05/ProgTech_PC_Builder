package Observer;

import Objects.Laptop_object;
import Objects.PC_object;

public class Laptop_Observer extends Observer{
    public Laptop_object laptop;

    public Laptop_Observer(Laptop_object laptop) {
        this.laptop = laptop;
        this.laptop.attach(this);
    }

    @Override
    public boolean update() {
        return true;
    }
}
