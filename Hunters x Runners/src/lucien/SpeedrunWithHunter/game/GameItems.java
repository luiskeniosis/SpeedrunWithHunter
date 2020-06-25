package lucien.SpeedrunWithHunter.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class GameItems {
    //ForgeSystem Items
    public static ArrayList<Material> forgableItems = new ArrayList<Material>(Arrays.asList(Material.IRON_AXE, Material.IRON_BOOTS, Material.IRON_CHESTPLATE,
	    Material.IRON_HELMET, Material.IRON_HOE, Material.IRON_HORSE_ARMOR, Material.IRON_INGOT, Material.IRON_LEGGINGS, Material.IRON_PICKAXE,
	    Material.IRON_SHOVEL, Material.IRON_SWORD, Material.GOLD_INGOT, Material.GOLDEN_AXE, Material.GOLDEN_BOOTS, Material.GOLDEN_CHESTPLATE,
	    Material.GOLDEN_HELMET, Material.GOLDEN_HOE, Material.GOLDEN_HORSE_ARMOR, Material.GOLDEN_LEGGINGS, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
	    Material.GOLDEN_SWORD)); 
    public static ItemStack lavaHead = new ItemStack(Material.PLAYER_HEAD);
    //Lobby Items
    public static ItemStack notReadyWool = new ItemStack(Material.RED_WOOL);
    public static ItemStack readyWool = new ItemStack(Material.GREEN_WOOL);
    public static ItemStack runnerKitSelector = new ItemStack(Material.CHEST);
    public static ItemStack hunterKitSelector = new ItemStack(Material.ENDER_CHEST);
    //Game Items
    public static List<ItemStack> uniqueItems = new ArrayList<ItemStack>();
    public static ItemStack playerTracker = new ItemStack(Material.COMPASS);
    
    public static void generateLobbyItems() {
	ItemMeta notReadyWoolMeta = notReadyWool.getItemMeta();
	notReadyWoolMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "NOT READY");
	List<String> notReadyWoolLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to be ready."));
	notReadyWoolMeta.setLore(notReadyWoolLore);
	notReadyWool.setItemMeta(notReadyWoolMeta);

	ItemMeta readyWoolMeta = readyWool.getItemMeta();
	readyWoolMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "READY");
	readyWoolMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
	readyWoolMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	List<String> readyWoolLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to be not ready."));
	readyWoolMeta.setLore(readyWoolLore);
	readyWool.setItemMeta(readyWoolMeta);

	ItemMeta runnerKitSelectorMeta = runnerKitSelector.getItemMeta();
	runnerKitSelectorMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Kit Selector");
	runnerKitSelectorMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
	runnerKitSelectorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	List<String> runnerKitSelectorLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to choose a profession."));
	runnerKitSelectorMeta.setLore(runnerKitSelectorLore);
	runnerKitSelector.setItemMeta(runnerKitSelectorMeta);

	ItemMeta hunterKitSelectorMeta = hunterKitSelector.getItemMeta();
	hunterKitSelectorMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Kit Selector");
	hunterKitSelectorMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
	hunterKitSelectorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	List<String> hunterKitSelectorLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&8&l-&r&7Right click to choose a hunter."));
	hunterKitSelectorMeta.setLore(hunterKitSelectorLore);
	hunterKitSelector.setItemMeta(hunterKitSelectorMeta);
    }
    
    @SuppressWarnings("deprecation")
    public static void generateGameItems() {
	ItemMeta playerTrackerMeta = playerTracker.getItemMeta();
	playerTrackerMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Player Tracker");
	playerTrackerMeta.addEnchant(Enchantment.LURE, 1, false);
	playerTrackerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	playerTracker.setItemMeta(playerTrackerMeta);
	uniqueItems.add(playerTracker);
	
	SkullMeta lavaHeadMeta = (SkullMeta) lavaHead.getItemMeta();
	lavaHeadMeta.setOwningPlayer(Bukkit.getServer().getOfflinePlayer("Akkalor"));
	lavaHead.setItemMeta(lavaHeadMeta);
    }
}
