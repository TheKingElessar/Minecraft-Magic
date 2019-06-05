package com.thekingelessar.minecraftmagic.item;

import com.thekingelessar.minecraftmagic.itemgroup.ItemGroupInit;
import com.thekingelessar.minecraftmagic.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.network.packets.PacketGlowSingleEntity;
import com.thekingelessar.minecraftmagic.network.packets.PacketSummonEvokerFang;
import com.thekingelessar.minecraftmagic.renderer.ExtendedRange;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class ItemModItem extends Item {

    public ItemModItem() {
        super(new Properties().group(ItemGroupInit.modItemGroup));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
            playerIn.setActiveHand(handIn); // Shield function

            int newCount = itemstack.getCount() - 1;

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

            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function

        } else {

            Double blockX = null;
            Double blockY = null;
            Double blockZ = null;

            ExtendedRange extendedRange = new ExtendedRange(null, 20.0F, RayTraceFluidMode.NEVER, Minecraft.getInstance(), playerIn);
            extendedRange.getMouseOver();
            RayTraceResult result = extendedRange.mcObjectMouseOver;

            if (result != null) {
                System.out.println("Not null! :D");
                Vec3d lookingAt = result.hitVec;
                Entity lookingAtEntity = result.entity;

                if(lookingAtEntity != null) {
                    UUID entityID = lookingAtEntity.getUniqueID();
                    String stringEntityID = entityID.toString();

                    MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketGlowSingleEntity(stringEntityID));

                    System.out.println("Sent packet to glow entity");

                }

                System.out.println("Length RAYTRACE: " + lookingAt.length());
                blockX = lookingAt.x;
                blockY = lookingAt.y;
                blockZ = lookingAt.z;
                System.out.println("Client looking at: " + blockX + " " + blockY + " " + blockZ);

            } else {
                System.out.println("Null :(");
            }



            System.out.println("");

            if(blockX != null && blockY != null && blockZ != null) {
                MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketSummonEvokerFang(blockX, blockY, blockZ));
            }


            ItemStack itemstack = playerIn.getHeldItem(handIn); // Shield function
            playerIn.setActiveHand(handIn); // Shield function

            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack); // Shield function
        }
    }

}
