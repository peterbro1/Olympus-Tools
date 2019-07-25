package me.gmx.olympus.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemFlag;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.core.OlympusAction;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.items.CloudBoots;
import me.gmx.olympus.items.DiscoBall;
import me.gmx.olympus.items.Dna;
import me.gmx.olympus.items.Flamethrower;
import me.gmx.olympus.items.IllusionSword;
import me.gmx.olympus.items.K9;
import me.gmx.olympus.items.Necronomicon;
import me.gmx.olympus.items.Nuke;
import me.gmx.olympus.items.Porcupine;
import me.gmx.olympus.items.PortableCrafter;
import me.gmx.olympus.items.ThorAxe;
import me.gmx.olympus.items.ThorAxeUpgraded;
import me.gmx.olympus.setup.HandlerSetup;
import me.gmx.olympus.tools.OlympusItem;

public class OlympusItems {
	
	public static OlympusItem<PlayerInteractEvent> PORTABLE_CRAFTER;
	public static OlympusItem<PlayerInteractEvent> THOR_AXE;
	public static OlympusItem<PlayerInteractEvent> K9_UNIT;
	public static OlympusItem<PlayerInteractEvent> DISCO_BALL;
	public static OlympusItem<PlayerInteractEvent> NECRONOMICON;
	public static OlympusItem<PlayerInteractEvent> PORCUPINE;
	public static OlympusItem<PlayerInteractEvent> STORMBREAKER;
	public static OlympusItem<PlayerInteractEvent> FLAMETHROWER;
	public static OlympusItem<PlayerInteractEvent> NUKE;
	public static OlympusItem<PlayerInteractEvent> ILLUSIONSWORD;
	public static OlympusItem<PlayerItemConsumeEvent> DNA;
	public static OlympusItem<PlayerToggleFlightEvent> CLOUD_BOOTS;
	
	public static ArrayList<OlympusItem> list;
	private OlympusTools ins;
	
	public OlympusItems(OlympusTools instance) {
		this.ins = instance;
		createItems();
		registerAll();
	}
	
	@SuppressWarnings({ "serial" })
	public void createItems() {
	 PORTABLE_CRAFTER = new PortableCrafter(
			 Rarity.MORTAL,
			 Material.WORKBENCH,
			 "Portable Crafting Table",
			 "CRAFTER",
			 new String[] { "Oh, useful!" },
			 true,
			 0,
			 null,
			 null,
			 PlayerInteractEvent.class,
			 (byte)0);
	 
	 
	 THOR_AXE = new ThorAxe(
			 Rarity.HERO,
			 Material.GOLD_AXE,
			 "Mjolnir",
			 "MJOLNIR",
			 new String[] {"Though Asgardians may be tough, they still need a protector"},
			 false,
			 0,
			 new ArrayList<ItemFlag>() { {add(ItemFlag.HIDE_ENCHANTS); add(ItemFlag.HIDE_ATTRIBUTES);} },
			 new HashMap<Enchantment,Integer>(){{put(Enchantment.DAMAGE_ALL,2);}},
			 PlayerInteractEvent.class,
			 (byte)0);
	 
	 
	 K9_UNIT = new K9(
			 Rarity.HERO,
			 Material.MONSTER_EGG,
			 "Shadow Demons",
			 "K9",
			 new String[] {"Some call them evil. Me? They're just my pups"},
			 PlayerInteractEvent.class,
			 (byte)0);
	 
	 DISCO_BALL = new DiscoBall(
			 Rarity.APOSTLE,
			 Material.DIAMOND_BLOCK,
			 "Disco Ball",
			"DISCO",
			 new String[] {"The party god will smite all who refuse to dance"},
			 true,
			 0,
			 null,
			 null,
			 PlayerInteractEvent.class,
			 (byte)0);
	 
	 NECRONOMICON = new Necronomicon(
			 Rarity.DEITY,
			 Material.BOOK,
			 "Necronomicon",
			 "NECRONOMICON",
			 new String[] {"\"...and so I became that which they feared. A monster that could sense their very existence\""},
			 false,
			 0,
			 new ArrayList<ItemFlag>() {{add(ItemFlag.HIDE_ENCHANTS);}},
			 new HashMap<Enchantment,Integer>(){{put(Enchantment.DAMAGE_UNDEAD,5);}},
			 PlayerInteractEvent.class,
			 (byte)0);
	 
		CLOUD_BOOTS = new CloudBoots(
				Rarity.LEGEND,
				Material.DIAMOND_BOOTS,
				"Hermes Boots",
				"CLOUDBOOTS",
				new String[] {"May he soar as high as an eagle."},
				false,
				0,
				null,
				null,
				PlayerToggleFlightEvent.class,
				(byte)0);
	
		
		PORCUPINE = new Porcupine(
				Rarity.APOSTLE,
				Material.RAW_FISH,
				"Porcupine",
				"PORCUPINE",
				new String[] {"May you be as safe as a porcupine surrounded by bunnies"},
				false,
				0,
				new ArrayList<ItemFlag>() {{add(ItemFlag.HIDE_ENCHANTS);}},
				new HashMap<Enchantment,Integer>(){{put(Enchantment.KNOCKBACK,3);}},
				 PlayerInteractEvent.class,
				 (byte)3);
		
		STORMBREAKER = new ThorAxeUpgraded(
				Rarity.DEITY,
				Material.DIAMOND_AXE,
				"Stormbreaker",
				"STORMBREAKER",
				new String[] {"\"So is facing Thanos without that axe\""},
				false,
				0,
				new ArrayList<ItemFlag>() {{add(ItemFlag.HIDE_ENCHANTS); add(ItemFlag.HIDE_ATTRIBUTES);}},
				new HashMap<Enchantment,Integer>() {{put(Enchantment.DURABILITY,0);}},
				 PlayerInteractEvent.class,
				 (byte)0);
		
		FLAMETHROWER = new Flamethrower(
				Rarity.HERO,
				Material.BLAZE_ROD,
				"Hell Fire",
				"FLAMETHROWER",
				new String[] {"\"...that the fire stolen from the gods will light men their way even while it burns their hands?\""},
				false,
				0,
				null,
				null,
				PlayerInteractEvent.class,
				(byte)0);
		
		DNA = new Dna(
				Rarity.HERO,
				Material.POTION,
				"Zeus's DNA",
				"DNA",
				new String[] {"Don't ask how this was obtained"},
				false,
				0,
				new ArrayList<ItemFlag>() {{add(ItemFlag.HIDE_ENCHANTS);}},
				new HashMap<Enchantment,Integer>() {{put(Enchantment.DURABILITY,0);}},
				PlayerItemConsumeEvent.class,
				(byte)3);
		
		NUKE = new Nuke(
				Rarity.GOD,
				Material.STICK,
				"Cleo's Server Nuke Stick",
				"NUKE",
				new String[] {"How did it come to this?"},
				false,
				0,
				new ArrayList<ItemFlag>() {{add(ItemFlag.HIDE_ENCHANTS);}},
				new HashMap<Enchantment,Integer>() {{put(Enchantment.DURABILITY,0);}},
				PlayerInteractEvent.class,
				(byte)0);
		
		ILLUSIONSWORD = new IllusionSword(
				Rarity.HERO,
				Material.DIAMOND_SWORD,
				"Sword of Illusions",
				"ILLUSIONSWORD",
				new String[] {"Nobody knows"},
				false,
				0,
				null,
				new HashMap<Enchantment,Integer>() {{put(Enchantment.DAMAGE_ALL,3);}},
				null,
				(byte)0);

				
		
	
	
	
	PORTABLE_CRAFTER.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	PORCUPINE.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	THOR_AXE.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	K9_UNIT.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	DISCO_BALL.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_BLOCK));
	NECRONOMICON.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR));
	CLOUD_BOOTS.setActions(Arrays.asList(OlympusAction.DOUBLE_JUMP));
	STORMBREAKER.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	FLAMETHROWER.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));
	DNA.setActions(Arrays.asList(OlympusAction.ITEM_CONSUME));
	NUKE.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR));
	ILLUSIONSWORD.setActions(Arrays.asList(OlympusAction.RIGHT_CLICK_AIR,OlympusAction.RIGHT_CLICK_BLOCK));

	}
	
	public void registerAll() {
		createItems();
		itemInit();
		list = new ArrayList<OlympusItem>();
		list.add(PORTABLE_CRAFTER);
		list.add(THOR_AXE);
		list.add(K9_UNIT);
		list.add(DISCO_BALL);
		list.add(NECRONOMICON);
		list.add(CLOUD_BOOTS);
		list.add(PORCUPINE);
		list.add(STORMBREAKER);
		list.add(FLAMETHROWER);
		list.add(DNA);
		list.add(NUKE);
		list.add(ILLUSIONSWORD);
		HandlerSetup.initHandlers();
	}
	
	public void itemInit() {
		((Porcupine) PORCUPINE).initListener();
	}
	

	
	public static OlympusItem getByName(String s)throws NullPointerException {
		for (OlympusItem o : list) {
			if (o.getDisplayName().equals(s)) {
				return o;
			}
		}
		throw new NullPointerException("Item not in list");
	}
	
	public static  List<OlympusItem<?>> getByAction(OlympusAction aa) {
		ArrayList<OlympusItem<?>> a = new ArrayList<OlympusItem<?>>();
		for (OlympusItem item : list) {
			if (item.allowedActions.contains(aa)) {
				a.add(item);
			}
		}
		return a;
				
	}
	
	
	public static OlympusItem getById(String s)throws NullPointerException {
		for (OlympusItem o : list) {
			if (o.getId().equals(s)) {
				return o;
			}
		}
		throw new NullPointerException("Item ID not in list");
	}
}

/*
 *
Dark Red (dark_red)	§4	
Red (red)	§c	
Gold (gold)	§6	
Yellow (yellow)	§e	
Dark Green (dark_green)	§2	
Green (green)	§a	
Aqua (aqua)	§b	
Dark Aqua (dark_aqua)	§3	
Dark Blue (dark_blue)	§1	
Blue (blue)	§9	\u00A79	5555FF
Light Purple (light_purple)	§d	
Dark Purple (dark_purple)	§5	
White (white)	§f	
Gray (gray)	§7	
Dark Gray (dark_gray)	§8	
Black (black)	§0	
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
