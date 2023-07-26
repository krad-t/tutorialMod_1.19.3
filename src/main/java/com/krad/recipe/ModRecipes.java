package com.krad.recipe;

import com.krad.TutorialMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(TutorialMod.MOD_ID, MortarRecipe.Serializer.ID),
                MortarRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(TutorialMod.MOD_ID, MortarRecipe.Type.ID),
                MortarRecipe.Type.INSTANCE);
    }
}
