package me.alres.alexmod.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    // Create a deferred registry instance for blocks
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "alexmod");
    // Create an item
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> {
        return new Item((new Item.Properties()).tab(ItemInit.AlexModTab.instance));
    });


    // Create an CreativeModeTab extension class for the mod
    public static class AlexModTab extends CreativeModeTab {
        public static final AlexModTab instance = new AlexModTab(CreativeModeTab.TABS.length, "alexmod_main");

        private AlexModTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack((ItemLike)ItemInit.TEST_ITEM.get());
        }

        static {
        }
    }
}
