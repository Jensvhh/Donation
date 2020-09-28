package me.siaco.combodonation;

import java.util.HashSet;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    getLogger().info("");
    getServer().getPluginManager().registerEvents(this, this);
  }
  
  public void onDisable()
  {
    getLogger().info("");
  }
  
  private HashSet<String> DonationCooldowns = new HashSet();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("donation")) {
      if ((sender instanceof Player))
      {
        Player p = (Player)sender;
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7(&e&lCombo&6&lMC&7) &cYou must be console to run this command."));
      }
      else if ((args.length != 0) && (!this.DonationCooldowns.contains(args[0])))
      {
        if ((args.length == 1) && (Bukkit.getServer().getPlayer(args[0]).isOnline()))
        {
          final String name = args[0];
          Bukkit.broadcastMessage("");
          Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7(&e&lCombo&6&lMC&7) &aThank you &f" + args[0] + "&a for supporting ComboMC."));
          Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&f              &nstore.combomc.org"));
          
          Player donator = Bukkit.getServer().getPlayer(args[0]);
          
          Firework fireworks = (Firework)donator.getWorld().spawnEntity(donator.getLocation(), EntityType.FIREWORK);
          FireworkMeta fireworkmeta = fireworks.getFireworkMeta();
          FireworkEffect fw = FireworkEffect.builder().flicker(true).withColor(Color.YELLOW).withFade(Color.ORANGE).trail(true).build();
          fireworkmeta.addEffect(fw);
          fireworkmeta.setPower(0);
          fireworks.setFireworkMeta(fireworkmeta);
          
          this.DonationCooldowns.add(name);
          new BukkitRunnable()
          {
            public void run()
            {
              Main.this.DonationCooldowns.remove(name);
            }
          }.runTaskLater(this, 200L);
        }
        else if ((args.length == 2) && (Bukkit.getServer().getPlayer(args[0]).isOnline()))
        {
          final String name = args[0];
          Bukkit.broadcastMessage("");
          Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7(&e&lCombo&6&lMC&7) &aThank you &f" + args[0] + "&a for supporting ComboMC."));
          Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&f              &nstore.combomc.org&6 (SALE - " + args[1] + "&6% OFF)"));
          
          Player donator = Bukkit.getServer().getPlayer(args[0]);
          
          Firework fireworks = (Firework)donator.getWorld().spawnEntity(donator.getLocation(), EntityType.FIREWORK);
          FireworkMeta fireworkmeta = fireworks.getFireworkMeta();
          FireworkEffect fw = FireworkEffect.builder().flicker(true).withColor(Color.YELLOW).withFade(Color.ORANGE).trail(true).build();
          fireworkmeta.addEffect(fw);
          fireworkmeta.setPower(0);
          fireworks.setFireworkMeta(fireworkmeta);
          
          this.DonationCooldowns.add(name);
          new BukkitRunnable()
          {
            public void run()
            {
              Main.this.DonationCooldowns.remove(name);
            }
          }.runTaskLater(this, 200L);
        }
      }
    }
    return true;
  }
}
