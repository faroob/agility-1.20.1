package net.faroob.agility;

import net.minecraft.nbt.NbtCompound;

public interface DoubleComponent extends Component{
    double getComponentValue();
    void addTo(double value);
    void subtractFrom(double value);
    void multiplyBy(double value);
    void divideBy(double value);
}
