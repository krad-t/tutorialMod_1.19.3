package com.krad.registry;

import com.krad.TutorialMod;
import com.krad.registry.Item.CitrineToolMaterial;
import com.krad.registry.Item.HealChargedCitrineIngot;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CITRINE = registerItem(
            "citrine",
            new Item(new FabricItemSettings()),
            ModItemGroup.CITRINE,ItemGroups.INGREDIENTS);
    public static final Item RAW_CITRINE = registerItem(
            "raw_citrine",
            new Item(new FabricItemSettings()),
            ModItemGroup.CITRINE,ItemGroups.INGREDIENTS);
    public static final Item CHARGED_CITINE_INGOT = registerItem(
            "charged_citrine_ingot",
            new Item(new FabricItemSettings()),
            ModItemGroup.CITRINE);
    public static Item CITRINE_SWORD = registerItem("citrine_sword",
            new SwordItem(CitrineToolMaterial.INSTANCE, 3, -2.4F, new Item.Settings()),
            ModItemGroup.CITRINE);

    public static Item HEAL_CITRINE = registerItem("heal_citrine",
            new HealChargedCitrineIngot(new FabricItemSettings()),
            ModItemGroup.CITRINE);



    public static Item registerItem(String name, Item item, ItemGroup... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
        for (ItemGroup itemGroup : itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registeredItem));
        }
        return registeredItem;
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.debug("Registering mod items for" + TutorialMod.MOD_ID);
    }
}