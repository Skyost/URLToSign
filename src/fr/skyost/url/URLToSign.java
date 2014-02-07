package fr.skyost.url;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import fr.skyost.url.listeners.CommandsExecutor;
import fr.skyost.url.listeners.EventsListener;
import fr.skyost.url.utils.MetricsLite;
import fr.skyost.url.utils.Skyupdater;

public class URLToSign extends JavaPlugin {
	
	public static ConfigFile config;
	public static final HashMap<String, String> cache = new HashMap<String, String>();
	
	@Override
	public final void onEnable() {
		try {
			config = new ConfigFile(this);
			config.init();
			final PluginCommand command = this.getCommand("url");
			command.setUsage(ChatColor.RED + "/url <url>.");
			command.setExecutor(new CommandsExecutor());
			Bukkit.getPluginManager().registerEvents(new EventsListener(), this);
			if(config.EnableUpdater) {
				new Skyupdater(this, 59033, this.getFile(), true, true);
			}
			new MetricsLite(this).start();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
