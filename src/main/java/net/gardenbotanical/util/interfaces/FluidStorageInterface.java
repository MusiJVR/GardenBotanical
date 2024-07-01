package net.gardenbotanical.util.interfaces;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.gardenbotanical.util.FluidStack;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.nbt.NbtCompound;


public interface FluidStorageInterface {
    SingleVariantStorage<FluidVariant> getFluidStorage();

    default void setFluidLevel(FluidVariant fluidVariant, long fluidLevel) {
        SingleVariantStorage<FluidVariant> fluidStorage = getFluidStorage();
        fluidStorage.variant = fluidVariant;
        fluidStorage.amount = fluidLevel;
    }

    default boolean fluidIsFull() {
        SingleVariantStorage<FluidVariant> fluidStorage = getFluidStorage();
        return fluidStorage.amount == fluidStorage.getCapacity();
    }

    default void fillFluid(FluidVariant variant, long amountMb) {
        try (Transaction transaction = Transaction.openOuter()) {
            getFluidStorage().insert(variant, amountMb, transaction);
            transaction.commit();
        }
    }

    default void fillFluid(FlowableFluid fluid, long amountDroplets) {
        fillFluid(FluidVariant.of(fluid), FluidStack.convertDropletsToMb(amountDroplets));
    }

    default void extractFluid(long amount) {
        SingleVariantStorage<FluidVariant> fluidStorage = getFluidStorage();
        try (Transaction transaction = Transaction.openOuter()) {
            fluidStorage.extract(fluidStorage.variant, amount, transaction);
            transaction.commit();
        }
    }

    default void extractFluid() {
        extractFluid(FluidConstants.BUCKET);
    }

    default void writeNbt(NbtCompound nbt) {
        getFluidStorage().writeNbt(nbt);
    }

    default void readNbt(NbtCompound nbt) {
        SingleVariantStorage<FluidVariant> fluidStorage = getFluidStorage();
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("variant"));
        fluidStorage.amount = nbt.getLong("amount");
    }
}
