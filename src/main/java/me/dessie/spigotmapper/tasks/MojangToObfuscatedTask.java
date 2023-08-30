package me.dessie.spigotmapper.tasks;

import me.dessie.spigotmapper.MapperExtension;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class MojangToObfuscatedTask extends MapTask {
    
    @Inject
    public MojangToObfuscatedTask(MapperExtension extension) {
        super(extension);
        this.setDescription("Generates a JAR where Mojangs Mappings are mapped to obfuscated. This JAR cannot run on a Spigot server.");
    }

    @TaskAction
    public void mapMojangToObfuscated() {
        Project project = this.getProject();
        MapperExtension extension = this.getExtension();

        String remappedMojangJar = this.getHomeDir() + extension.getLocalSpigotRepo() + extension.getMinecraftVersion() + "/spigot-" + extension.getMinecraftVersion() + "-remapped-mojang.jar";
        String mojangMapsFile = this.getHomeDir() + extension.getLocalMinecraftRepo() + extension.getMinecraftVersion() + "/minecraft-server-" + extension.getMinecraftVersion() + "-maps-mojang.txt";

        project.exec(execSpec -> {
            execSpec.setCommandLine("java");
            execSpec.args("-cp", this.getToolingDir() + (extension.isUseWindowsSeparator() ? ";" : ":") + this.getHomeDir() + remappedMojangJar, "net.md_5.specialsource.SpecialSource");
            execSpec.args("-l");
            execSpec.args("-i", project.getTasks().getByName("jar").getOutputs().getFiles().getSingleFile().getAbsolutePath());
            execSpec.args("-o", this.getObfJarPath());
            execSpec.args("-m", mojangMapsFile);
            execSpec.args("-r");
            execSpec.args("-q");
        });
    }

}
