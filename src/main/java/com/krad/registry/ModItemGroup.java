package com.krad.registry;

import com.krad.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup CITRINE = FabricItemGroup.builder(new Identifier(TutorialMod.MOD_ID))
            .displayName(Text.translatable("itemgroup.tutorial-mod.citrine"))
            .icon(() -> new ItemStack(ModItems.CITRINE))
            .build();
    public static void registerModItemGroup(){
        TutorialMod.LOGGER.debug("Registering mod item group for" + TutorialMod.MOD_ID);
    }
}
