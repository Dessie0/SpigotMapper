package me.dessie.spigotmapper.tasks;

import me.dessie.spigotmapper.MapperExtension;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class ObfuscatedToSpigotTask extends MapTask {

    @Inject
    public ObfuscatedToSpigotTask(MapperExtension extension) {
        super(extension);
        this.setDescription("Generates a JAR where obfuscated mappings are mapped to Spigot. This JAR can run on a Spigot server.");
    }

    @TaskAction
    public void mapObfuscatedToSpigot() {
        Project project = this.getProject();
        MapperExtension extension = this.getExtension();

        String spigotJarPath = project.getBuildDir().toPath().resolve("libs").resolve(extension.getSpigotMappingsOutput() + ".jar").toString();
        String remappedObfJar = this.getHomeDir() + extension.getLocalSpigotRepo() + extension.getMinecraftVersion() + "/spigot-" + extension.getMinecraftVersion() + "-remapped-obf.jar";
        String spigotMapsFile = this.getHomeDir() + extension.getLocalMinecraftRepo() + extension.getMinecraftVersion() + "/minecraft-server-" + extension.getMinecraftVersion() + "-maps-spigot.csrg";

        project.exec(execSpec -> {
            execSpec.setCommandLine("java");
            execSpec.args("-cp", this.getToolingDir() + (extension.isUseWindowsSeparator() ? ";" : ":") + this.getHomeDir() + remappedObfJar, "net.md_5.specialsource.SpecialSource");
            execSpec.args("-l");
            execSpec.args("-i", this.getObfJarPath());
            execSpec.args("-o", spigotJarPath);
            execSpec.args("-m", spigotMapsFile);
            execSpec.args("-q");
        });
    }

}
