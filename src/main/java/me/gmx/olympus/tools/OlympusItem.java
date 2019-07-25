package me.gmx.olympus.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.OlympusAction;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.handler.AbstractHandler;
import me.gmx.olympus.util.CooldownUtil;
import me.gmx.olympus.util.ItemMetadata;
import me.gmx.olympus.util.StringUtils;

public abstract class OlympusItem<T extends Event> extends ItemStack{
	public String id;
	public AbstractHandler handler;
	public List<OlympusAction> allowedActions;
	protected Class<? extends Event> a;
	private byte data;
	private boolean unplaceable;
	private Rarity r;
	public OlympusItem(ItemStack item) {
		super(item);
	}
	

	/*
	 * 
	 * @param type - Material type of item
	 * @param name - Display name
	 * @param id - Internal ID
	 * @param ench - Map of enchantments
	 * @param flags - ItemFlag collection
	 * @param lore - String[] of lore
	 * @param unplaceable - Whether this block can be placed
	 * @param durability - Durability. Set to 0 for full
	 * 
	 */
	@SuppressWarnings("serial")
	public OlympusItem(Rarity r,Material type, String name,String id, String[] lore,boolean unplaceable,int durability,ArrayList<ItemFlag> flags, Map<Enchantment,Integer> ench, Class<? extends Event> clas,byte data) {
		
		super(ItemMetadata.newWithNBTData(new ItemStack(type,1,(byte)data),new HashMap<String,String>(){
			
			{
				put("ID",id);
				if (unplaceable)
					put("unplaceable","true");
			}
		}));
		this.id = id;
		this.r = r;
		//null checks
		this.a = clas;
		this.unplaceable = unplaceable;
		this.data = data;
		if (flags == null)
			flags = new ArrayList<ItemFlag>();
		if (ench == null) 
			ench = new HashMap<Enchantment,Integer>();
		
		
		
		ArrayList<String> newlore = new ArrayList<String>();
		for (String s : lore) {
			newlore.addAll(Arrays.asList(me.gmx.olympus.util.StringUtils.wrap(s, 30).split("\n")));
		}
		
		ItemMeta im = getItemMeta();
		//NBT stuff
		
		//Lore stuff
		im.setDisplayName(r.getColor() + ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: newlore) {
			lines.add(ChatColor.DARK_GRAY + ChatColor.translateAlternateColorCodes('&', line));
		}
		lines.add(ChatColor.ITALIC + r.getTitle());
		im.setLore(lines);
		for (ItemFlag flag : flags) {
			im.addItemFlags(flag);
		}
		setItemMeta(im);
		
		
		if (data > 0)
			setDurability((short) data);
		
		
		
		
		
			
		for (Enchantment e : ench.keySet()) {
			addUnsafeEnchantment(e,ench.get(e));
		}
		ItemMetadata.addNBTDataString(this, "ID", id);
	}
	public ItemStack asMirror(Material m) {
		ItemStack sta = new ItemStack(ItemMetadata.newWithNBTData(new ItemStack(m,1,(byte)data),new HashMap<String,String>(){
			
			{
				put("ID",id);
				if (unplaceable)
					put("unplaceable","true");
			}
		}));

		ArrayList<String> newlore = new ArrayList<String>();
		for (String s : getItemMeta().getLore()) {
			newlore.addAll(Arrays.asList(me.gmx.olympus.util.StringUtils.wrap(s, 30).split("\n")));
		}
		
		ItemMeta im = sta.getItemMeta();
		//NBT stuff
		
		sta.setItemMeta(getItemMeta());
		
		
		if (data > 0)
			sta.setDurability((short) data);
		
		for (Enchantment e : getEnchantments().keySet()) {
			sta.addUnsafeEnchantment(e,getEnchantments().get(e));
		}
		return sta;
	}
	
	public Class<? extends Event> getEvent() {
		return this.a;
	}
	
	
	public void setHandler(AbstractHandler c) {
		this.handler = c;
		
		
		//Bukkit.getServer().getPluginManager().registerEvents(this.handler, OlympusTools.getInstance());

	}
	
	public void setActions(List<OlympusAction> map) {
		allowedActions = map;
	}
	public AbstractHandler getHandler() {
		return this.handler;
	}
	
	/*public boolean executeAction(Action a, Player p) {
		
		if (CooldownUtil.onCooldown(p.getName(), id)) {
			p.sendMessage(Lang.MSG_ITEM_ONCOOLDOWN.toMsg().replace("%time%", String.valueOf(CooldownUtil.getCooldown(p.getName(), id)/1000))
					.replace("%item%",getDisplayName() + ChatColor.RESET));
			return false;

		}else {
			CooldownUtil.addCooldown(p.getName(), id, Settings.getCooldownById(id));
			return true;
		}
		
		


		
	}*/
	
	
	public void destroy() {
		setType(Material.AIR);
	}
	
	
	
	public String getId() {
		return this.id;
	}

	
	public static ItemStack withIDNBT(Material type, HashMap<String,String> map) {
		ItemStack i = new ItemStack(type);
		for (String s : map.keySet()) {
			ItemMetadata.addNBTDataString(i,s, map.get(s));
		}
		return i;
		
	}
	
	public OlympusItem(Rarity r, Material type, String name, String id, String[] lore, Class<? extends Event> clas,byte data) {
		this(r, type,name,id,lore,false,0,null,null,clas,data);
	}
	
	

	
	public boolean canExecute(Player p) {
		if (CooldownUtil.onCooldown(p.getName(), getId())) {
			p.sendMessage(Lang.MSG_ITEM_ONCOOLDOWN.toMsg().replace("%time%", String.valueOf(CooldownUtil.getCooldown(p.getName(), getId())/1000))
					.replace("%item%",getDisplayName() + ChatColor.RESET));
			return false;

		}else {
			if (Settings.getCooldownById(getId()) > 0) {
				CooldownUtil.addCooldown(p.getName(), getId(), Settings.getCooldownById(getId()));
			return true;
			}else if (Settings.getCooldownById(getId()) == 0)
				return true;
			return false;
			
		}
	}
	
	

	public List<String> getLore() {
		if (!hasItemMeta()) return new ArrayList<String>();
		else if (!getItemMeta().hasLore()) return new ArrayList<String>();
		else return getItemMeta().getLore();
	}
	
	public String getDisplayName() {
		if (!hasItemMeta()) return "";
		else if (!getItemMeta().hasDisplayName()) return "";
		else return getItemMeta().getDisplayName();
	}
	
	public boolean hasEnchantment(Enchantment enchantment) {
		return getEnchantments().containsKey(enchantment);
	}
	
	public int getEnchantmentLevel(Enchantment enchantment){
		return hasEnchantment(enchantment) ? getEnchantmentLevel(enchantment):0;
	}


	public abstract boolean executeAction(Player player, T e);
	
}
