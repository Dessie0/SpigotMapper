package me.dessie.spigotobfuscator;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;

public class ObfuscateTask extends DefaultTask {

    private final ObfuscationExtension extension;

    @Inject
    public ObfuscateTask(ObfuscationExtension extension) {
        this.extension = extension;

        this.setDescription("Generates a JAR where Mojangs Mappings are first mapped to obfuscated, and then mapped to Spigots mappings. This jar can run on a Spigot server.");
        this.setGroup("obfuscation");
    }

    @TaskAction
    public void obfuscate() {
        Project project = this.getProject();

        String homeDir = project.getGradle().getGradleUserHomeDir().getParent();

        //Find the JAR file for SpecialSource
        String toolingDir = project.getConfigurations().getByName("compileClasspath")
                .getFiles().stream()
                .filter(it -> it.getName().startsWith("SpecialSource-" + extension.getSpecialSourceVersion() + "-shaded"))
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        if(toolingDir == null) throw new IllegalStateException("Unable to find the SpecialSource JAR.");

        String obfJarPath = project.getBuildDir().toPath().resolve("libs").resolve(extension.getObfuscatedMappingsOutput() + ".jar").toString();
        String spigotJarPath = project.getBuildDir().toPath().resolve("libs").resolve(extension.getSpigotMappingsOutput() + ".jar").toString();

        String remappedMojangJar = homeDir + extension.getLocalSpigotRepo() + extension.getMinecraftVersion() + "/spigot-" + extension.getMinecraftVersion() + "-remapped-mojang.jar";
        String remappedObfJar = homeDir + extension.getLocalSpigotRepo() + extension.getMinecraftVersion() + "/spigot-" + extension.getMinecraftVersion() + "-remapped-obf.jar";
        String mojangMapsFile = homeDir + extension.getLocalMinecraftRepo() + extension.getMinecraftVersion() + "/minecraft-server-" + extension.getMinecraftVersion() + "-maps-mojang.txt";
        String spigotMapsFile = homeDir + extension.getLocalMinecraftRepo() + extension.getMinecraftVersion() + "/minecraft-server-" + extension.getMinecraftVersion() + "-maps-spigot.csrg";


        project.exec(execSpec -> {
            execSpec.setCommandLine("java");
            execSpec.args("-cp", toolingDir + (extension.isUseWindowsSeparator() ? ";" : ":") + homeDir + remappedMojangJar, "net.md_5.specialsource.SpecialSource");
            execSpec.args("-l");
            execSpec.args("-i", project.getTasks().getByName("jar").getOutputs().getFiles().getSingleFile().getAbsolutePath());
            execSpec.args("-o", obfJarPath);
            execSpec.args("-m", mojangMapsFile);
            execSpec.args("-r");
            execSpec.args("-q");
        });

        project.exec(execSpec -> {
            execSpec.setCommandLine("java");
            execSpec.args("-cp", toolingDir + (extension.isUseWindowsSeparator() ? ";" : ":") + homeDir + remappedObfJar, "net.md_5.specialsource.SpecialSource");
            execSpec.args("-l");
            execSpec.args("-i", obfJarPath);
            execSpec.args("-o", spigotJarPath);
            execSpec.args("-m", spigotMapsFile);
            execSpec.args("-q");
        });

    }

}
