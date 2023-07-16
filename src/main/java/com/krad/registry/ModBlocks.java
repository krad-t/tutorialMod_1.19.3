package com.krad.registry;

import com.krad.TutorialMod;
import com.krad.registry.Block.Extractor;
import com.krad.registry.Block.ExtractorEntity;
import com.krad.registry.Block.Mortar;
import com.krad.registry.Block.MortarEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block CITRINE_BLOCK = registerBlock("citrine_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()),
            ModItemGroup.CITRINE);

    public static final Block CITRINE_ORE = registerBlock("citrine_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(), UniformIntProvider.create(2, 6)),
            ModItemGroup.CITRINE);

    public static final Block DEEPSLATE_CITRINE_ORE = registerBlock("deepslate_citrine_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(), UniformIntProvider.create(2, 6)),
            ModItemGroup.CITRINE);

    public static final Block EXTRACTOR = registerBlock("extractor",
            new Extractor(FabricBlockSettings.of(Material.METAL).strength(4.0f)),
            ModItemGroup.CITRINE);

    public static final BlockEntityType<ExtractorEntity> EXTRACTOR_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(TutorialMod.MOD_ID, "extractor_entity"),
            FabricBlockEntityTypeBuilder.create(ExtractorEntity::new, EXTRACTOR).build()
    );

    public static final Block MORTAR = registerBlock(
            "mortar",
            new Mortar(FabricBlockSettings.of(Material.METAL)),
            ModItemGroup.CITRINE);

    public static final BlockEntityType<MortarEntity> MortarEntityInstance = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(TutorialMod.MOD_ID,"mortar_entity"),
            FabricBlockEntityTypeBuilder.create(MortarEntity::new,MORTAR).build()
    );



    public static Block registerBlock(String name, Block block, ItemGroup... itemGroups){
        ModItems.registerItem(name,new BlockItem(block,new FabricItemSettings()),itemGroups);
        return Registry.register(Registries.BLOCK,new Identifier(TutorialMod.MOD_ID,name),block);
    }


    public static void registerModBlocks(){
        TutorialMod.LOGGER.debug("Registering mod blocks for" + TutorialMod.MOD_ID);

    }
}
