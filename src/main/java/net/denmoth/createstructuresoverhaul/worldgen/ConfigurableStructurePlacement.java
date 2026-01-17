package net.denmoth.createstructuresoverhaul.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.denmoth.createstructuresoverhaul.config.CSOConfig;
import net.minecraft.core.Vec3i;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class ConfigurableStructurePlacement extends RandomSpreadStructurePlacement {

    public static final Codec<ConfigurableStructurePlacement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(ConfigurableStructurePlacement::locateOffset),
            StructurePlacement.FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", StructurePlacement.FrequencyReductionMethod.DEFAULT).forGetter(ConfigurableStructurePlacement::frequencyReductionMethod),
            Codec.FLOAT.optionalFieldOf("frequency", 1.0F).forGetter(ConfigurableStructurePlacement::frequency),
            Codec.INT.fieldOf("salt").forGetter(ConfigurableStructurePlacement::salt),
            StructurePlacement.ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(ConfigurableStructurePlacement::exclusionZone),
            Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ConfigurableStructurePlacement::spacing),
            Codec.intRange(0, 4096).fieldOf("separation").forGetter(ConfigurableStructurePlacement::separation),
            RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(ConfigurableStructurePlacement::spreadType),
            CfgType.CODEC.fieldOf("cfg_type").forGetter(ConfigurableStructurePlacement::getCfgType)
    ).apply(instance, ConfigurableStructurePlacement::new));

    private final CfgType cfgType;

    public ConfigurableStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<StructurePlacement.ExclusionZone> exclusionZone, int spacing, int separation, RandomSpreadType spreadType, CfgType cfgType) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.cfgType = cfgType;
    }

    public ConfigurableStructurePlacement(Vec3i locateOffset, RandomSpreadType spreadType, int salt, int spacing, int separation, CfgType cfgType) {
        super(locateOffset, StructurePlacement.FrequencyReductionMethod.DEFAULT, 1.0f, salt, Optional.empty(), spacing, separation, spreadType);
        this.cfgType = cfgType;
    }

    public CfgType getCfgType() {
        return cfgType;
    }

    @Override
    public StructurePlacementType<?> type() {
        return ModPlacementTypes.CONFIGURABLE.get();
    }

    // === OVERRIDES TO USE CONFIG ===

    @Override
    public int spacing() {
        // Если конфиг загружен, берем значения оттуда. Иначе берем дефолт из JSON.
        if (CSOConfig.COMMON_SPEC.isLoaded()) {
            return switch (cfgType) {
                case WINDMILLS -> CSOConfig.WINDMILLS_SPACING.get();
                case SPOOKY -> CSOConfig.SPOOKY_SPACING.get();
                case NETHER -> CSOConfig.NETHER_SPACING.get();
            };
        }
        return super.spacing();
    }

    @Override
    public int separation() {
        if (CSOConfig.COMMON_SPEC.isLoaded()) {
            int spacing = spacing(); // Берем актуальный spacing
            int separation = switch (cfgType) {
                case WINDMILLS -> CSOConfig.WINDMILLS_SEPARATION.get();
                case SPOOKY -> CSOConfig.SPOOKY_SEPARATION.get();
                case NETHER -> CSOConfig.NETHER_SEPARATION.get();
            };
            // Валидация: Separation должен быть меньше Spacing
            return Math.min(separation, spacing - 1);
        }
        return super.separation();
    }

    // === PUBLIC ACCESSORS FOR CODEC ===

    @Override
    public Vec3i locateOffset() { return super.locateOffset(); }
    @Override
    public FrequencyReductionMethod frequencyReductionMethod() { return super.frequencyReductionMethod(); }
    @Override
    public float frequency() { return super.frequency(); }
    @Override
    public int salt() { return super.salt(); }
    @Override
    public Optional<ExclusionZone> exclusionZone() { return super.exclusionZone(); }
    @Override
    public RandomSpreadType spreadType() { return super.spreadType(); }

    public enum CfgType implements StringRepresentable {
        WINDMILLS("windmills"),
        SPOOKY("spooky"),
        NETHER("nether");

        public static final Codec<CfgType> CODEC = StringRepresentable.fromEnum(CfgType::values);
        private final String name;
        CfgType(String name) { this.name = name; }
        @Override
        public String getSerializedName() { return name; }
    }
}