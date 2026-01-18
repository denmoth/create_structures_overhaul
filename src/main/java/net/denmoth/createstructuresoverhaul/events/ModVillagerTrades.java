package net.denmoth.createstructuresoverhaul.events;

import net.denmoth.createstructuresoverhaul.CreateStructuresOverhaulMod;
import net.denmoth.createstructuresoverhaul.datagen.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateStructuresOverhaulMod.MODID)
public class ModVillagerTrades {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CARTOGRAPHER) {
            var trades = event.getTrades();

            if (trades.get(3) != null) {
                trades.get(3).add((pTrader, pRandom) ->
                        createMapTrade(pTrader, ModTags.SPOOKY_LOCATIONS, "item.cso.haunted_map", MapDecoration.Type.MANSION, 50));
            }

            if (trades.get(4) != null) {
                trades.get(4).add((pTrader, pRandom) ->
                        createMapTrade(pTrader, ModTags.NETHER_COMPLEX, "item.cso.nether_map", MapDecoration.Type.RED_X, 50));
            }
        }
    }

    private static MerchantOffer createMapTrade(net.minecraft.world.entity.Entity pTrader, net.minecraft.tags.TagKey<net.minecraft.world.level.levelgen.structure.Structure> tag, String translationKey, MapDecoration.Type icon, int radius) {
        if (!(pTrader.level() instanceof ServerLevel serverLevel)) return null;

        ItemStack mapItem;
        var pos = serverLevel.findNearestMapStructure(tag, pTrader.blockPosition(), radius, true);

        if (pos != null) {
            mapItem = MapItem.create(serverLevel, pos.getX(), pos.getZ(), (byte) 2, true, true);
            MapItem.renderBiomePreviewMap(serverLevel, mapItem);
            MapItem.getSavedData(mapItem, serverLevel).addTargetDecoration(mapItem, pos, "+", icon);
            mapItem.setHoverName(Component.translatable(translationKey));
        } else {
            return null;
        }

        return new MerchantOffer(
                new ItemStack(Items.EMERALD, 15),
                new ItemStack(Items.COMPASS),
                mapItem,
                5, 15, 0.05f
        );
    }
}