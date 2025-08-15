package net.faroob.agility;

import net.minecraft.nbt.NbtCompound;

public interface Component {
    void readFromNbt(NbtCompound tag);
    void writeToNbt(NbtCompound tag);
}

