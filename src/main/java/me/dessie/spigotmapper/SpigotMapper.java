package me.dessie.spigotmapper;

import me.dessie.spigotmapper.tasks.MojangToObfuscatedTask;
import me.dessie.spigotmapper.tasks.MojangToSpigotTask;
import me.dessie.spigotmapper.tasks.ObfuscatedToSpigotTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class SpigotMapper implements Plugin<Project> {
    public void apply(Project project) {
        MapperExtension mapper = project.getExtensions().create("spigotMapper", MapperExtension.class, project);
        project.getTasks().register("mapMojangToObfuscated", MojangToObfuscatedTask.class, mapper);
        project.getTasks().register("mapObfuscatedToSpigot", ObfuscatedToSpigotTask.class, mapper);
        project.getTasks().register("mapMojangToSpigot", MojangToSpigotTask.class, mapper);

        //Add the SpecialSource dependency to the project so we know it's there.
        project.getDependencies().add("compileOnly", "net.md-5:SpecialSource:" + mapper.getSpecialSourceVersion() + ":shaded");
    }
}