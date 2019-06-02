package com.thekingelessar.minecraftmagic.init;

import com.thekingelessar.minecraftmagic.itemgroup.ModItemGroup;

public class ItemGroupInit {

    public static final ModItemGroup modItemGroup = new ModItemGroup("ModItemGroup", ()->(ItemInit.moditem));
    public static final ModItemGroup falconryItemGroup = new ModItemGroup("Falconry", ()->(ItemInit.jesses));

}
