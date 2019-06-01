package com.thekingelessar.tutorialmod.item;

import com.thekingelessar.tutorialmod.init.ItemGroupInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Jesses extends Item {

    public Jesses() {
        super(new Properties().group(ItemGroupInit.falconryItemGroup));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        InventoryPlayer inventoryPlayer = playerIn.inventory;
        ItemStack currentItem = inventoryPlayer.getCurrentItem();

        inventoryPlayer.setInventorySlotContents(inventoryPlayer.currentItem, ItemStack.EMPTY);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
