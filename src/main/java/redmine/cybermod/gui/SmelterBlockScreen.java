package redmine.cybermod.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.BeaconScreen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketDirection;
import net.minecraft.network.play.client.CUpdateBeaconPacket;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import redmine.cybermod.container.SmelterBlockContainer;
import redmine.cybermod.network.SimplChannel;
import redmine.cybermod.network.SmelterBlockProcessPacket;
import redmine.cybermod.tileentity.SmelterBlockTile;
import redmine.cybermod.utils.Reference;

public class SmelterBlockScreen extends ContainerScreen<SmelterBlockContainer> {
    public static final ResourceLocation GUI = new ResourceLocation(Reference.MOD_ID, "textures/gui/smelter_block_gui.png");
    SmelterBlockContainer smelterBlockContainer;
    int tick = 1;
    boolean augmente = true;
    boolean change = true;

    private ProcessButton processButton;

    public SmelterBlockScreen(SmelterBlockContainer smelterBlockContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(smelterBlockContainer, inv, titleIn);
        this.smelterBlockContainer = smelterBlockContainer;
    }

    @Override
    protected void init() {
        super.init();
        this.processButton = this.addButton(new ProcessButton(this.getGuiLeft() + 115, this.getGuiTop() + 59, smelterBlockContainer.getTileEntity().getBlockPos()));
    }

    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        super.renderLabels(matrixStack, x, y);
        int fuel = ((SmelterBlockTile) (smelterBlockContainer.getTileEntity())).getFuel();
        this.font.draw(matrixStack, "Fuel:" + fuel + "/100", (float) this.inventoryLabelX, (float) (this.inventoryLabelY * 0.85), 4210752);

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());
        int xLavaOffset, yLavaOffset = 0;
        int jauge = (int) (((SmelterBlockTile) smelterBlockContainer.getTileEntity()).getFuel() / 1.25);
        if (tick <= 10) {
            xLavaOffset = 176;
            yLavaOffset = (16 * tick) - 16;
        } else {
            xLavaOffset = 192;
            yLavaOffset = (16 * (tick - 10)) - 16;
        }
         for (int a = 0; a < (jauge / 16); a++) {

                    int XLava = 139;
                int YLava = 68 - (16 * a);

                if(jauge != 0) {

                    this.blit(matrixStack, i + XLava, j + YLava, xLavaOffset, yLavaOffset, 16, 16);
                }
        }
        if (jauge % 16 != 0) {
            int jauge1 = jauge % 16;
            int YLava = 4 + (80 - jauge);
            this.blit(matrixStack, i + 139, j + YLava, xLavaOffset, yLavaOffset + (16 - jauge1), 16, jauge1);
        }


        if(smelterBlockContainer.getTileEntity().isFlag()) {
            float a = (float)(smelterBlockContainer.getTileEntity().getLitTime()) /  (float)(smelterBlockContainer.getTileEntity().getLitDuration());
            this.blit(matrixStack, getGuiLeft() + 68, getGuiTop() + 39, 208, 0, 11, (int)(a * 32));
            this.blit(matrixStack, getGuiLeft() + 84, getGuiTop() + 39, 224, 0, 10, (int)(a * 18));
            this.blit(matrixStack, getGuiLeft() + 97, getGuiTop() + 39, 237, 0, 18, (int)(a * 32));
        }
    }

    @Override
    public void tick() {
        processButton.CanProcess = smelterBlockContainer.getTileEntity().canProcess;
        super.tick();
        if (!change) {
            change = true;
            return;
        }
        if (augmente) {
            if (!(tick > 19)) {
                tick++;
            } else {
                augmente = false;
            }
        } else {
            if ((tick > 2)) {
                tick--;
            } else {
                augmente = true;
            }
        }
        change = false;
    }
}

class ProcessButton extends AbstractButton {

    BlockPos blockPos;
    boolean CanProcess = false;

    public ProcessButton(int x, int y, BlockPos blockPos) {
        super(x, y, 18, 18, StringTextComponent.EMPTY);
        Minecraft.getInstance().getTextureManager().bind(SmelterBlockScreen.GUI);
        this.blockPos = blockPos;
    }


    @Override
    public void renderButton(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        Minecraft.getInstance().getTextureManager().bind(SmelterBlockScreen.GUI);
        int j = 0;
        if(this.isHovered()) j = 36;
        if(!CanProcess) j = 18;
        this.blit(pMatrixStack, this.x , this.y, j, 169, 18, 18);
    }

    @Override
    public void onPress() {
        SimplChannel.INSTANCE.sendToServer(new SmelterBlockProcessPacket(blockPos, 0));
    }
}
