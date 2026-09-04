package br.net.gmj.nobookie.LTItemMail.module;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import br.net.gmj.nobookie.LTItemMail.module.ConfigurationModule.Type;
import br.net.gmj.nobookie.LTItemMail.LTItemMail;
import br.net.gmj.nobookie.LTItemMail.util.BukkitUtil;

public final class ConsoleModule {

	public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BROWN = "\u001B[33m"; // brown doesn't exist, use yellow or custom RGB

	private ConsoleModule() {}
	private static final String PREFIX = "[" + LTItemMail.getInstance().getDescription().getName() + "] ";
	private static final ConsoleCommandSender sender() {
		return Bukkit.getConsoleSender();
	}
	public static final void hello() {
		//□
		String logo = "\n" +
        GREEN + " _______ _____   " + BLUE + "_______ " + GREEN + "______ " + BLUE + "_______\n" +
        GREEN + "|     __|     |_|" + BLUE + "       |" + GREEN + "   __ \\" + BLUE + "    ___|\n" +
        GREEN + "|    |  |       |" + BLUE + "   □   |" + GREEN + "   __ <" + BLUE + "    ___|\n" +
        BROWN + "|_______|_______|" + BLUE + "_______|" + BROWN + "______/" + BLUE + "_______\n" +
        RED + " ________ _______ ______ __  __ _______\n" +
        RED + "|  |  |  |       |   __ \\  |/  |     __|\n" +
        RED + "|  |  |  |   □   |      <     <|__     |\n" +
        RED + "|________|_______|___|__|__|\\__|_______|\n" +
        YELLOW + "            <ItemMail>\n" +
        RESET;
        sender().sendMessage(logo);
		sender().sendMessage(PREFIX + "Originally created by: https://github.com/Nobookie");
		sender().sendMessage(PREFIX + "Forked and updated by: https://github.com/dpembo");
		sender().sendMessage(PREFIX + "Globeworks ItemMail v" + ConfigurationModule.get(Type.VERSION_NUMBER));
		

		// final Map<String, Object> params = new HashMap<>();
		// params.put("format", "dd/MM/yyyy HH:mm:ss z");
		// sender().sendMessage(ChatColor.DARK_AQUA + " _   _______ _____ __  __ ");
		// sender().sendMessage(ChatColor.DARK_AQUA + "| | |__   __|_   _|  \\/  |");
		// sender().sendMessage(ChatColor.DARK_AQUA + "| |    | |    | | | \\  / |" + ChatColor.WHITE + "  Build number: #" + ConfigurationModule.get(Type.BUILD_NUMBER));
		// sender().sendMessage(ChatColor.DARK_AQUA + "| |____| |   _| |_| |\\/| |" + ChatColor.WHITE + "  Build date: " + buildDate.replaceAll(System.lineSeparator(), ""));
		// sender().sendMessage(ChatColor.DARK_AQUA + "|______|_|  |_____|_|  |_|" + ChatColor.WHITE + "  Need support or have any questions? " + ChatColor.DARK_GREEN + DataModule.DISCORD);
	}
	public static final void info(final String message) {
		sender().sendMessage(PREFIX + message);
	}
	public static final void warning(final String message) {
		sender().sendMessage(PREFIX + ChatColor.YELLOW + message);
	}
	public static final void severe(final String message) {
		sender().sendMessage(PREFIX + ChatColor.RED + message);
	}
	public static final void raw(final String message) {
		sender().sendMessage(PREFIX + ChatColor.RESET + message);
	}
	public static final void debug(final Class<?> clazz, final String message) {
		if((Boolean) ConfigurationModule.get(ConfigurationModule.Type.PLUGIN_DEBUG)) sender().sendMessage(PREFIX + ChatColor.GRAY + "[" + clazz.getName() + "] " + message);
	}
	public static final void server(final List<String> warnings) {
		sender().sendMessage(PREFIX + ChatColor.WHITE + "<!-- " + ChatColor.DARK_AQUA + "LT Item Mail " + ChatColor.RED + "Version Control");
		for(final String warning : warnings) sender().sendMessage(ChatColor.WHITE + "#   " + BukkitUtil.Text.Color.format(warning));
		sender().sendMessage(ChatColor.WHITE + "-->");
	}
	public static final void board(final CommandSender receiver, final Map<Integer, Map<String, Map<String, List<String>>>> messages) {
		receiver.sendMessage(PREFIX + ChatColor.WHITE + "" + ChatColor.BOLD + "<!-- " + ChatColor.DARK_AQUA + ChatColor.BOLD + "LT Item Mail " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Messages Board");
		receiver.sendMessage("");
		receiver.sendMessage("");
		for(final Integer id : messages.keySet()) {
			final Map<String, Map<String, List<String>>> contents1 = messages.get(id);
			for(final String title : contents1.keySet()) {
				final Map<String, List<String>> contents2 = contents1.get(title);
				for(final String datetime : contents2.keySet()) {
					receiver.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + title + " (" + datetime + ")");
					for(final String message : contents2.get(datetime)) receiver.sendMessage(ChatColor.WHITE + "#   " + BukkitUtil.Text.Color.format(message));
				}
				receiver.sendMessage("");
				receiver.sendMessage("");
			}
		}
		receiver.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "-->");
	}
	public static final void br() {
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣾⣷⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⡿⠿⠟⠻⠿⢿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⣀⣤⣾⣿⠟⠁⠀⠀⠀⠀⠀⠀⠈⠻⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⣀⣴⣾⣿⣿⣿⡿⠿⠿⠿⠶⢶⣦⣤⣀⠀⠀⢹⣿⣿⣿⣷⣦⣀⠀⠀⠀");
		sender().sendMessage("⠀⠰⢾⣿⣿⣿⣿⣿⣿⡁⠀⠀⠀⠀⠀⠀⠈⠙⠳⣦⣈⣿⣿⣿⣿⣿⣿⡷⠆⠀");
		sender().sendMessage("⠀⠀⠀⠉⠻⢿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⣿⣿⣿⡿⠟⠉⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⠉⠛⢿⣿⣦⡀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣷⣶⣦⣴⣶⣾⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
		sender().sendMessage("");
		final List<ChatColor> cores = Arrays.asList(ChatColor.GREEN,
				ChatColor.YELLOW);
		int n = 0;
		String obrigado = "";
		for(char letra : new String("Obrigado por instalar o meu plugin! Aproveite =D").toCharArray()) {
			obrigado = obrigado + cores.get(n) + letra;
			n++;
			if(n >= cores.size()) n = 0;
		}
		sender().sendMessage(obrigado);
	}
}