package yaboichips.customcompost;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompostConfig {
    private static final ForgeConfigSpec CONFIG_SPEC;
    private static final CompostConfig INSTANCE;

    static{
        final Pair<CompostConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CompostConfig::new);
        CONFIG_SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> item30;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> item50;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> item65;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> item85;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> item100;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> drops;

    public CompostConfig(ForgeConfigSpec.Builder builder){
        builder.push("The percentage shown is the chance the for the item to add a layer to the composter. The percentages in this list are the same as vanilla.");
        builder.pop();
        item30 = builder.defineList("30% Items", Collections.emptyList(),
                obj -> obj instanceof String);
        item50 = builder.defineList("50% Items", Collections.emptyList(),
                obj -> obj instanceof String);
        item65 = builder.defineList("65% Items", Collections.emptyList(),
                obj -> obj instanceof String);
        item85 = builder.defineList("85% Items", Collections.emptyList(),
                obj -> obj instanceof String);
        item100 = builder.defineList("100% Items", Collections.emptyList(),
                obj -> obj instanceof String);

        builder.push("This list of items defines what a composter drops");
        builder.pop();

        drops = builder.defineList("Drops", Arrays.asList("minecraft:bone_meal"),
                obj -> obj instanceof String);
    }

    public static ForgeConfigSpec getConfigSpec() {
        return CONFIG_SPEC;
    }
    public static CompostConfig getInstance() {
        return INSTANCE;
    }

}
