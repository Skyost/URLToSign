package fr.skyost.url.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.Location;

public class Utils {
	
	public static final boolean isURL(final String string) {
		try {
			new URL(string);
			return true;
		}
		catch(MalformedURLException ex) {
			return false;
		}
	}
	
	public static final String locationToString(final Location loc) {
		return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ();
	}

}
