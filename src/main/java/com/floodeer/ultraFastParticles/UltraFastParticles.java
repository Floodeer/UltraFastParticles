package com.floodeer.ultraFastParticles;

import com.floodeer.ultraFastParticles.particle.CycloneParticle;
import com.floodeer.ultraFastParticles.particle.HelixParticle;
import com.floodeer.ultraFastParticles.particle.ParticleManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltraFastParticles extends JavaPlugin {

    private static UltraFastParticles main;
    private ParticleManager particleManager;

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public static UltraFastParticles get() {
        return main;
    }

    @Override
    public void onEnable() {
        main = this;
        particleManager = new ParticleManager();
        getCommand("ufp").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player player))
                return false;

            if(args.length == 0)
                return true;

            if(args[0].equalsIgnoreCase("cyclone")) {
                particleManager.addEffect(player, new CycloneParticle(player));
            }else if(args[0].equalsIgnoreCase("helix")) {
                particleManager.addEffect(player, new HelixParticle(player));
            }
            return true;
        });
    }

    @Override
    public void onDisable() {

    }
}
