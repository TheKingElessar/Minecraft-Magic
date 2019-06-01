package com.thekingelessar.minecraftmagic.itemgroup;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroup extends net.minecraft.item.ItemGroup {

    private ItemStack icon;

    public ModItemGroup(String label, ItemStack icon) {
        super(label);
        this.icon = icon;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return icon;
    }
}
