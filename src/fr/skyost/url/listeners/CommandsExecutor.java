package fr.skyost.url.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import fr.skyost.url.URLToSign;
import fr.skyost.url.utils.Utils;

public class CommandsExecutor implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		Player player = null;
		if(sender instanceof Player) {
			player = (Player)sender;
		}
		else {
			sender.sendMessage(ChatColor.RED + "Please do this from the game !");
		}
		if(args.length == 0) {
			return false;
		}
		if(Utils.isURL(args[0])) {
			try {
				final Block block = player.getTargetBlock(null, 1000);
				if(block.getState() instanceof Sign) {
					URLToSign.config.Signs.put(Utils.locationToString(block.getLocation()), args[0]);
					URLToSign.config.save();
					sender.sendMessage(ChatColor.GREEN + "Done with success !");
				}
				else {
					sender.sendMessage(ChatColor.RED + "You must be looking at a sign !");
				}
			}
			catch(InvalidConfigurationException ex) {
				ex.printStackTrace();
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "Invalid URL.");
		}
		return true;
	}

}
