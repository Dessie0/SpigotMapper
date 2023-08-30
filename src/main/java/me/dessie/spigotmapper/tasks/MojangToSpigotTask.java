package me.dessie.spigotmapper.tasks;

import me.dessie.spigotmapper.MapperExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskProvider;

import javax.inject.Inject;

public class MojangToSpigotTask extends DefaultTask {

    private final MapperExtension extension;

    @Inject
    public MojangToSpigotTask(MapperExtension extension) {
        this.extension = extension;
        this.setDescription("Directly maps Mojang Mappings to Spigot Mappings. This JAR can run on a Spigot server.");
        this.setGroup("mapping");

        TaskProvider<Task> mojangToObfuscatedTask = this.getProject().getTasks().named("mapMojangToObfuscated");
        TaskProvider<Task> obfuscatedToSpigotTask = this.getProject().getTasks().named("mapObfuscatedToSpigot");

        dependsOn(mojangToObfuscatedTask, obfuscatedToSpigotTask);
    }
}
