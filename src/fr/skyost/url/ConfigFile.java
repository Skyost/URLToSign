package fr.skyost.url;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.Plugin;

import fr.skyost.url.utils.Config;

public class ConfigFile extends Config {
	
	public HashMap<String, String> Signs = new HashMap<String, String>();
	public boolean SuportAuthor = true;
	public boolean EnableUpdater = true;
	public String Message = "§2/url//lineseparator/Type T to open the chat, then click on the link above to open it.";
	
	public ConfigFile(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
		CONFIG_HEADER = "URLToSign Configuration";
		CONFIG_HEADER += "\nSupporting the author by automatically converting your link into an Adf.ly link :3";
	}
	
}
