package com.thekingelessar.minecraftmagic.init;

import com.thekingelessar.minecraftmagic.item.ModItems;
import com.thekingelessar.minecraftmagic.itemgroup.ModItemGroup;

public class ItemGroupInit {

    public static final ModItemGroup modItemGroup = new ModItemGroup("ModItemGroup", ()->(ModItems.MODITEM));
    public static final ModItemGroup falconryItemGroup = new ModItemGroup("Falconry", ()->(ModItems.JESSES));

}
