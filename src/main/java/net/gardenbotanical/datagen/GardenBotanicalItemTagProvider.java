package net.gardenbotanical.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gardenbotanical.block.GardenBotanicalBlocks;
import net.gardenbotanical.item.GardenBotanicalItems;
import net.gardenbotanical.tag.GardenBotanicalTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;


public class GardenBotanicalItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public GardenBotanicalItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(
                GardenBotanicalBlocks.BOUVARDIA.asItem(),
                GardenBotanicalBlocks.BRUNIA.asItem(),
                GardenBotanicalBlocks.GERBERA.asItem(),
                GardenBotanicalBlocks.HERBAL_PEONY.asItem(),
                GardenBotanicalBlocks.VERONICA.asItem(),
                GardenBotanicalBlocks.DULL_PINK_TULIP.asItem(),
                GardenBotanicalBlocks.POINSETTIA.asItem(),
                GardenBotanicalBlocks.SETARIA.asItem(),
                GardenBotanicalBlocks.ALOE_TRASKI.asItem(),
                GardenBotanicalBlocks.ASTER.asItem(),
                GardenBotanicalBlocks.SNOW_HYDRANGEA.asItem(),
                GardenBotanicalBlocks.CHICORY.asItem(),
                GardenBotanicalBlocks.IVY.asItem(),
                GardenBotanicalBlocks.AQUILEGIA.asItem(),
                GardenBotanicalBlocks.BLUE_MOUNTAIN_TULIP.asItem(),
                GardenBotanicalBlocks.DRY_VIOLA.asItem(),
                GardenBotanicalBlocks.BIG_DANDELION.asItem(),
                GardenBotanicalBlocks.AHSOKA.asItem(),
                GardenBotanicalBlocks.BURGUNDY_ROSE.asItem(),
                GardenBotanicalBlocks.CALENDULA.asItem(),
                GardenBotanicalBlocks.STRONGYLODON.asItem(),
                GardenBotanicalBlocks.SCULK_FLOWER.asItem(),
                GardenBotanicalBlocks.DIANTHUS.asItem(),
                GardenBotanicalBlocks.DARK_RED_PHLOX.asItem(),
                GardenBotanicalBlocks.PINK_MATTHIOLA.asItem()
        );
        getOrCreateTagBuilder(GardenBotanicalTags.FLOWER_POWDERS).add(
                GardenBotanicalItems.POWDERED_BOUVARDIA,
                GardenBotanicalItems.POWDERED_BRUNIA,
                GardenBotanicalItems.POWDERED_GERBERA,
                GardenBotanicalItems.POWDERED_HERBAL_PEONY,
                GardenBotanicalItems.POWDERED_VERONICA,
                GardenBotanicalItems.POWDERED_DULL_PINK_TULIP,
                GardenBotanicalItems.POWDERED_POINSETTIA,
                GardenBotanicalItems.POWDERED_SETARIA,
                GardenBotanicalItems.POWDERED_ALOE_TRASKI,
                GardenBotanicalItems.POWDERED_ASTER,
                GardenBotanicalItems.POWDERED_SNOW_HYDRANGEA,
                GardenBotanicalItems.POWDERED_CHICORY,
                GardenBotanicalItems.POWDERED_IVY,
                GardenBotanicalItems.POWDERED_AQUILEGIA,
                GardenBotanicalItems.POWDERED_BLUE_MOUNTAIN_TULIP,
                GardenBotanicalItems.POWDERED_DRY_VIOLA,
                GardenBotanicalItems.POWDERED_BIG_DANDELION,
                GardenBotanicalItems.POWDERED_AHSOKA,
                GardenBotanicalItems.POWDERED_BURGUNDY_ROSE,
                GardenBotanicalItems.POWDERED_CALENDULA,
                GardenBotanicalItems.POWDERED_STRONGYLODON,
                GardenBotanicalItems.POWDERED_SCULK_FLOWER,
                GardenBotanicalItems.POWDERED_DIANTHUS,
                GardenBotanicalItems.POWDERED_DARK_RED_PHLOX,
                GardenBotanicalItems.POWDERED_PINK_MATTHIOLA
        );

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_ITEM_TYPES)
                .addTag(GardenBotanicalTags.COLORIZER_ARMOR_TYPES)
                .add(Items.PAPER);

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_ARMOR_TYPES)
                .addTag(GardenBotanicalTags.COLORIZER_HELMET)
                .addTag(GardenBotanicalTags.COLORIZER_CHESTPLATE)
                .addTag(GardenBotanicalTags.COLORIZER_LEGGINGS)
                .addTag(GardenBotanicalTags.COLORIZER_BOOTS)
                .addTag(GardenBotanicalTags.COLORIZER_HORSE_ARMOR);

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_HELMET).add(
                Items.LEATHER_HELMET
        );

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_CHESTPLATE).add(
                Items.LEATHER_CHESTPLATE
        );

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_LEGGINGS).add(
                Items.LEATHER_LEGGINGS
        );

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_BOOTS).add(
                Items.LEATHER_BOOTS
        );

        getOrCreateTagBuilder(GardenBotanicalTags.COLORIZER_HORSE_ARMOR).add(
                Items.LEATHER_HORSE_ARMOR
        );
    }
}
