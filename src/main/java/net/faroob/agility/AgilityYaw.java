package net.faroob.agility;

import net.minecraft.nbt.NbtCompound;

public class AgilityYaw implements DoubleComponent{
    private double componentValue = 0.0;

    @Override public double getComponentValue() { return this.componentValue; }
    @Override public void addTo(double value) { componentValue += value; }
    @Override public void subtractFrom(double value) { componentValue -= value; }
    @Override public void multiplyBy(double value) { componentValue *= value; }
    @Override public void divideBy(double value) { componentValue /= value; }
    @Override public void readFromNbt(NbtCompound tag) { this.componentValue = tag.getInt("componentValue"); }
    @Override public void writeToNbt(NbtCompound tag) { tag.putDouble("componentValue", this.componentValue); }
}
