package com.thekingelessar.minecraftmagic.common.item.spell.conjuration;

import com.thekingelessar.minecraftmagic.common.itemgroup.ItemGroupInit;
import com.thekingelessar.minecraftmagic.common.spell.spells.conjuration.SpellConjureFangRow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemConjureFangRow extends Item
{
    
    public ItemConjureFangRow()
    {
        super(new Properties().group(ItemGroupInit.itemGroupMagic));
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote)
        {
            ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
            playerIn.setActiveHand(handIn); // Shield function
            
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function
            
        }
        else
        {
            
            SpellConjureFangRow spellConjureFangRow = new SpellConjureFangRow(playerIn);
            
            if (spellConjureFangRow.target != null)
            {
                spellConjureFangRow.castClient();
                
                ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
                playerIn.setActiveHand(handIn); // Shield function
                
                return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function
                
            }
            else
            {
                ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
                playerIn.setActiveHand(handIn); // Shield function
                return new ActionResult<>(EnumActionResult.PASS, itemstack); // Shield function
            }
        }
    }
    
}
