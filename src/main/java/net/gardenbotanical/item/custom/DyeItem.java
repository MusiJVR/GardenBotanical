package net.gardenbotanical.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;


public class DyeItem extends Item {
    public DyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (itemStack.getNbt() != null) {
            int color = itemStack.getNbt().getInt("color");
            MutableText text = Text.translatable("tooltip.gardenbotanical.dye").append(Text.literal(String.format("#%06X", color)));
            tooltip.add(text.setStyle(text.getStyle().withColor(color).withItalic(true)));
        }
    }
}
