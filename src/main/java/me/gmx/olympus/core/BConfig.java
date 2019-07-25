/*    */ package me.gmx.olympus.core;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;

/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.java.JavaPlugin;

import me.gmx.olympus.util.FileUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BConfig
/*    */ {
/*    */   private JavaPlugin main;
/*    */   private String name;
/*    */   private FileConfiguration fileConfiguration;
/*    */   private File file;
/*    */   
/*    */   public BConfig(JavaPlugin paramJavaPlugin, String paramString) {
/* 22 */     this.main = paramJavaPlugin;
/* 23 */     this.name = paramString;
/* 24 */     load();
/*    */   }
/*    */   
/*    */   private void load() {
/* 28 */     if (!this.main.getDataFolder().exists()) {
/* 29 */       FileUtils.mkdir(this.main.getDataFolder());
/*    */     }
/*    */     
/* 32 */     File file1 = new File(this.main.getDataFolder(), this.name);
/* 33 */     if (!file1.exists()) {
/* 34 */       FileUtils.copy(this.main.getResource(this.name), file1);
/*    */     }
/*    */     
/* 37 */     this.file = file1;
/*    */     
/* 39 */     this.fileConfiguration = YamlConfiguration.loadConfiguration(file1);
/* 40 */     this.fileConfiguration.options().copyDefaults(true);
/*    */   }
/*    */   
/*    */   public void save() {
/*    */     try {
/* 45 */       this.fileConfiguration.options().copyDefaults(true);
/* 46 */       this.fileConfiguration.save(this.file);
/* 47 */     } catch (IOException iOException) {
/* 48 */       iOException.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 53 */   public FileConfiguration getConfig() { return this.fileConfiguration; }
/*    */ }


/* Location:              C:\Users\peter\Desktop\gangs\GangsPlus-2.2.1-SNAPSHOT.jar!\net\brcdev\gangs\core\BConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.3
 */