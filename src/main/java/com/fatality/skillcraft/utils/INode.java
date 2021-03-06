/**
 * Created by Fatality
 */

/* You are free to:
 * 
 * Share — copy and redistribute the material in any medium or format
 * Adapt — remix, transform, and build upon the material
 * for any purpose, even commercially.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 * Under the following terms:
 * Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
 * ShareAlike — If you remix, transform, or build upon the material, you must distribute your contributions under the same license as the original.
 * No additional restrictions — You may not apply legal terms or technological measures that legally restrict others from doing anything the license permits.
 * Notices:
 * 
 * You do not have to comply with the license for elements of the material in the public domain or where your use is permitted by an applicable exception or limitation.
 * No warranties are given. The license may not give you all of the permissions necessary for your intended use. For example, other rights such as publicity, privacy, or moral rights may limit how you use the material.
 */

package com.fatality.skillcraft.utils;

import com.fatality.skillcraft.api.utils.GuiHelper;
import com.fatality.skillcraft.api.utils.experience;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class INode {
	
	public final ResourceLocation background = new ResourceLocation(ModInfo.MOD_ID + ":textures/guis/skillbook.png");
	private int guiXPos, guiYPos;
	private String title;
	private float titleScale = 0.86F;
	private int colour;
	
	public INode(int guiXPos, int guiYPos, String guiIcon, float guiIconScale, int iconXOffset, int iconYOffset,
	             String title, int colour) {
		this.guiXPos = guiXPos;
		this.guiYPos = guiYPos;
		this.title = title;
		this.colour = colour;
	}
	
	public void render(GuiContainer gui, int mouseX, int mouseY) {
		int l = 22;
		
		if ((this.colour & -67108864) == 0) {
			this.colour |= -16777216;
		}
		
		float red = (float) (this.colour >> 16 & 255) / 255.0F;
		float green = (float) (this.colour >> 8 & 255) / 255.0F;
		float blue = (float) (this.colour & 255) / 255.0F;
		float alpha = (float) (this.colour >> 24 & 255) / 255.0F;
		
		GlStateManager.color(red, green, blue, alpha);
		gui.drawTexturedModalRect(guiXPos, guiYPos + 2, 206, 50, 50, 50);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		gui.drawTexturedModalRect(guiXPos, guiYPos, 206, 0, 50, 50);
		titleScale = 1F;
		GuiHelper.addText(GuiHelper.getCenter(title, guiXPos, 50, titleScale), guiYPos + 55, title, titleScale, new Color(115, 110, 100).hashCode(),
				false);
		
		if ((mouseX >= guiXPos && mouseY >= guiYPos) && (mouseX <= guiXPos + 55 && mouseY <= guiYPos + 55)) {
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, 10.1F);
			
			float width = 57.5f;
			float height = 30;
			
			if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(title) * 1.25F > width) {
				width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(title) * 1.25F;
			}
			
			//Left Side
			GuiHelper.drawRectWithUV(mouseX + 7, mouseY + 2, 203, 0, 1, 1, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 7, mouseY + 3, 203, 1, 1, height, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 7, mouseY + 3 + height, 203, height + 1, 1, 1, 1, 1, background);
			
			//Middle
			GuiHelper.drawRectWithUV(mouseX + 8, mouseY + 2, 204, 0, width, 1, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 8, mouseY + 3, 204, 1, width, height, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 8, mouseY + 3 + height, 204, 2, width, 1, 1, 1, background);
			
			//Right
			GuiHelper.drawRectWithUV(mouseX + 8 + width, mouseY + 2, 205, 0, 1, 1, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 8 + width, mouseY + 3, 205, 1, 1, height, 1, 1, background);
			GuiHelper.drawRectWithUV(mouseX + 8 + width, mouseY + 3 + height, 205, 2, 1, 1, 1, 1, background);
			
			GuiHelper.addText(GuiHelper.getCenter(title, mouseX + 7, (int) Math.ceil(width), 0.85F), mouseY + 4, title, 0.85F, new Color(0, 0, 0).hashCode(),
					false);
			
			GuiHelper.addText(mouseX + 10, mouseY + 14, "Level: ", 0.85F, new Color(30, 125, 190).hashCode(),
					true);
			
			GuiHelper.addText(mouseX + 40, mouseY + 14, "55", 0.85F, new Color(30, 125, 190).hashCode(),
					true);
			
			GuiHelper.addText(mouseX + 10, mouseY + 24, "Exp: ", 0.85F, new Color(45, 150, 9).hashCode(),
					true);
			
			GuiHelper.addText(mouseX + 30, mouseY + 24, experience.getRequireExp(55) + "", 0.85F, new Color(45, 150, 9).hashCode(),
					true);
			
			GlStateManager.popMatrix();
		}
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(background);
	}
	
}