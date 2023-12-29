package me.alres.alexmod.init;

import java.rmi.registry.Registry;
import java.util.function.Supplier;
import me.alres.alexmod.init.ItemInit.AlexModTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries.Keys;

@EventBusSubscriber(
    bus = Bus.MOD
)
public class BlockInit {
    // Create a deferred registry instance for blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "alexmod");
    // Initialize the blocks to the registry
    public static final RegistryObject<Block> ROCKY_DIRT = BLOCKS.register("rocky_dirt", () -> {
        return new Block(Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL));
    });
    public static final RegistryObject<Block>EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> {
        return new Block(Properties.copy(Blocks.STONE).requiresCorrectToolForDrops());
    });

    // Create a registry event handler to register items for all created blocks
    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        if (event.getRegistryKey().equals(Keys.ITEMS)) {
            BLOCKS.getEntries().forEach((blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties = (new Item.Properties()).tab(AlexModTab.instance);
                Supplier<Item> blockItemFactory = () -> {
                    return new BlockItem(block, properties);
                };
                event.register(Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }

    }
}
