package me.dessie.spigotobfuscator;

import org.gradle.api.Project;

public class ObfuscationExtension {

    private final Project project;

    private String specialSourceVersion = "1.11.0";

    /**
     * Defines which version of Minecraft you're building against.
     */
    private String minecraftVersion = "1.20.1-R0.1-SNAPSHOT";

    /**
     * Changes the output name for the JAR with obfuscated mappings.
     * You generally will probably have no use for this JAR.
     */
    private String obfuscatedMappingsOutput;

    /**
     * Changes the output name for the JAR with Spigot Mappings.
     * Remember, this version is the one that should be placed in your server's plugins folder.
     */
    private String spigotMappingsOutput;

    /**
     * Uses a semicolon (;) instead of a colon (:) to separate classpath arguments when using the java command.
     * Linux machines will use a colon, so set this to false if you're getting errors building on a Linux machine.
     */
    private boolean useWindowsSeparator = true;

    /**
     * Determines if the {@link ObfuscateTask} is automatically run after the 'jar' task.
     */
    private boolean finalizedByJar = true;

    private String localMinecraftRepo = "/.m2/repository/org/spigotmc/minecraft-server/";
    private String localSpigotRepo = "/.m2/repository/org/spigotmc/spigot/";

    public ObfuscationExtension(Project project) {
        this.project = project;
        this.obfuscatedMappingsOutput = project.getName() + "-obf-mappings-" + project.getVersion();
        this.spigotMappingsOutput = project.getName() + "-spigot-mappings-" + project.getVersion();
    }

    public String getSpecialSourceVersion() {
        return specialSourceVersion;
    }

    public void setSpecialSourceVersion(String specialSourceVersion) {
        this.specialSourceVersion = specialSourceVersion;
    }

    public String getMinecraftVersion() {
        return minecraftVersion;
    }

    public void setMinecraftVersion(String minecraftVersion) {
        this.minecraftVersion = minecraftVersion;
    }

    public boolean isUseWindowsSeparator() {
        return useWindowsSeparator;
    }

    public void setUseWindowsSeparator(boolean useWindowsSeparator) {
        this.useWindowsSeparator = useWindowsSeparator;
    }

    public String getLocalMinecraftRepo() {
        return localMinecraftRepo;
    }

    public void setLocalMinecraftRepo(String localMinecraftRepo) {
        this.localMinecraftRepo = localMinecraftRepo;
    }

    public String getObfuscatedMappingsOutput() {
        return obfuscatedMappingsOutput;
    }

    public void setObfuscatedMappingsOutput(String obfuscatedMappingsOutput) {
        this.obfuscatedMappingsOutput = obfuscatedMappingsOutput;
    }

    public String getSpigotMappingsOutput() {
        return spigotMappingsOutput;
    }

    public void setSpigotMappingsOutput(String spigotMappingsOutput) {
        this.spigotMappingsOutput = spigotMappingsOutput;
    }

    public String getLocalSpigotRepo() {
        return localSpigotRepo;
    }

    public void setLocalSpigotRepo(String localSpigotRepo) {
        this.localSpigotRepo = localSpigotRepo;
    }

    public boolean isFinalizedByJar() {
        return finalizedByJar;
    }

    public void setFinalizedByJar(boolean finalizedByJar) {
        this.finalizedByJar = finalizedByJar;
    }
}
