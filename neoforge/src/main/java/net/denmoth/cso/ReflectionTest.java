package net.denmoth.cso;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) {
        for (Method m : StructureProcessor.class.getDeclaredMethods()) {
            System.out.println(m.toString());
        }
    }
}
