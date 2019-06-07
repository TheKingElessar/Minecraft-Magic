package com.thekingelessar.minecraftmagic.common.item;

import com.thekingelessar.minecraftmagic.common.itemgroup.ItemGroupInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemModItem extends Item {

    public ItemModItem() {
        super(new Properties().group(ItemGroupInit.modItemGroup));
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
        playerIn.setActiveHand(handIn); // Shield function

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function
    }
}
