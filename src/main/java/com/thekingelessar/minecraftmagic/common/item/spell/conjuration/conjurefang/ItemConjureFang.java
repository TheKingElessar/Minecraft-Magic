package com.thekingelessar.minecraftmagic.common.item.spell.conjuration;

import com.thekingelessar.minecraftmagic.common.itemgroup.ItemGroupInit;
import com.thekingelessar.minecraftmagic.common.spell.conjuration.SpellConjureFang;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemConjureFang extends Item {

    public ItemConjureFang() {
        super(new Properties().group(ItemGroupInit.modItemGroup));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
            playerIn.setActiveHand(handIn); // Shield function

 /*           int newCount = itemstack.getCount() - 1;
//used if you want to decrease the item stack by one when used
            if (handIn.equals(EnumHand.MAIN_HAND)) {
                if(newCount == 0) {
                    playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                } else {
                    itemstack.setCount(newCount);
                }
            } else if (handIn.equals(EnumHand.OFF_HAND)) {
                if(newCount == 0) {
                    playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
                } else {
                    itemstack.setCount(newCount);
                }
            }
*/
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function

        } else {

            SpellConjureFang spellConjureFang = new SpellConjureFang(playerIn);

            if(spellConjureFang.target != null) {
                spellConjureFang.cast();

                ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
                playerIn.setActiveHand(handIn); // Shield function

                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function

            } else {
                ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
                playerIn.setActiveHand(handIn); // Shield function
                return new ActionResult<>(EnumActionResult.PASS, itemstack); // Shield function
            }
        }
    }

}
