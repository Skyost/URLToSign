package fr.skyost.url.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.base.Joiner;

import fr.skyost.url.URLToSign;
import fr.skyost.url.utils.Utils;

public class EventsListener implements Listener {
	
	@EventHandler
	private final void onPlayerInteract(final PlayerInteractEvent event) throws IOException {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Player player = event.getPlayer();
			if(!player.hasPermission("urltosign.click")) {
				return;
			}
			final Block block = event.getClickedBlock();
			String url = URLToSign.config.Signs.get(Utils.locationToString(block.getLocation()));
			String message;
			if(url == null) {
				final BlockState blockState = block.getState();
				if(!(blockState instanceof Sign)) {
					return;
				}
				url = Joiner.on("").join(((Sign)blockState).getLines());
				if(!Utils.isURL(url)) {
					return;
				}
			}
			if(URLToSign.config.SuportAuthor) {
				String cacheUrl = URLToSign.cache.get(url);
				if(cacheUrl == null) {
					final HttpURLConnection con = (HttpURLConnection)new URL("http://api.adf.ly/api.php?key=368a4eec97ad8daa8bfc43e2d2c6b15e&uid=549897&advert_type=int&domain=adf.ly&url=" + url).openConnection();
					con.addRequestProperty("User-Agent", "URLToSign by Skyost");
					cacheUrl = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
					URLToSign.cache.put(url, cacheUrl);
				}
				url = cacheUrl;
			}
			message = URLToSign.config.Message.replaceAll("/url/", url).replaceAll("/lineseparator/", "\n");
			player.sendMessage(message);
		}
	}

}
