package net.pixeldreamstudios.soulscraft_tarnished_legacy.forge.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

/**
 * Rolls another loot table into the generated loot. Forge 1.20.1 has no built-in equivalent of
 * NeoForge's {@code add_table} modifier.
 */
public final class AddTableLootModifier extends LootModifier {
    public static final Supplier<Codec<AddTableLootModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(instance -> codecStart(instance)
                    .and(ResourceLocation.CODEC.fieldOf("table").forGetter(modifier -> modifier.table))
                    .apply(instance, AddTableLootModifier::new)));

    private final ResourceLocation table;

    public AddTableLootModifier(LootItemCondition[] conditions, ResourceLocation table) {
        super(conditions);
        this.table = table;
    }

    // getRandomItemsRaw skips global loot modifiers, so the injected table can't re-trigger this one.
    @Override
    @SuppressWarnings("deprecation")
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        context.getResolver()
                .getLootTable(this.table)
                .getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), generatedLoot::add));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
