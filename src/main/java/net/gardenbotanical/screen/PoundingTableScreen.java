package net.gardenbotanical.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gardenbotanical.GardenBotanical;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;


public class PoundingTableScreen extends HandledScreen<PoundingTableScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(GardenBotanical.MOD_ID, "textures/gui/pounding_table_gui.png");

    public PoundingTableScreen(PoundingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 108, y + 36, 176, 0, 12, handler.getScaledProgress());
        }

        int amountFuel = MathHelper.clamp((18 * handler.getFuel() * 4 + 20 - 1) / 20, 0, 18);
        if (amountFuel > 0) {
            context.drawTexture(TEXTURE, x + 54, y + 51, 176, 15, amountFuel, 4);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
