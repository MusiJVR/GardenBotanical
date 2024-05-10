package net.gardenbotanical.util;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

public class Utils {
    public static final CauldronBehavior CLEAN_DYEABLE_PAPER = (state, world, pos, player, hand, stack) -> {
        Item item = stack.getItem();
        if (item == Items.PAPER && nbtContains(stack, "display", "color")) {
            if (!world.isClient) {
                ItemStack itemStack = stack.copyWithCount(1);
                itemStack.removeSubNbt("display");
                stack.decrement(1);

                if (stack.isEmpty()) {
                    player.setStackInHand(hand, itemStack);
                } else if (player.getInventory().insertStack(itemStack)) {
                    player.playerScreenHandler.syncState();
                } else {
                    player.dropItem(itemStack, false);
                }

                player.incrementStat(Stats.CLEAN_ARMOR);
                LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
            }
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    };

    public static int blendColors(int color1, int color2) {
        int red1 = (color1 >> 16) & 0xFF;
        int green1 = (color1 >> 8) & 0xFF;
        int blue1 = color1 & 0xFF;

        int red2 = (color2 >> 16) & 0xFF;
        int green2 = (color2 >> 8) & 0xFF;
        int blue2 = color2 & 0xFF;

        return ((red1 + red2) / 2 << 16) | ((green1 + green2) / 2 << 8) | ((blue1 + blue2) / 2);
    }

    public static int checkDisplayColorNbt(ItemStack stack, int defaultColor) {
        if (nbtContains(stack, "display", "color")) {
            return stack.getSubNbt("display").getInt("color");
        } else {
            return defaultColor;
        }
    }

    public static boolean nbtContains(ItemStack stack, String... nbtKeys) {
        if (nbtKeys.length == 1) {
            if (stack.getNbt() == null) return false;
            return stack.getNbt().contains(nbtKeys[0]);
        } else if (nbtKeys.length == 2) {
            if (stack.getSubNbt(nbtKeys[0]) == null) return false;
            return stack.getSubNbt(nbtKeys[0]).contains(nbtKeys[1]);
        } else {
            return false;
        }
    }
}
