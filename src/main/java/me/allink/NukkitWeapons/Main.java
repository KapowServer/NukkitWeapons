package me.allink.NukkitWeapons;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.math.BlockFace;
import cn.nukkit.plugin.PluginBase;
import me.allink.NukkitWeapons.commands.CommandWeapons;
import me.allink.NukkitWeapons.modules.player.PlayerUseWeapon;
import me.allink.NukkitWeapons.modules.weapons.WeaponAnvilDropper;
import me.allink.NukkitWeapons.modules.weapons.WeaponArcher;
import me.allink.NukkitWeapons.modules.weapons.WeaponArmageddon;
import me.allink.NukkitWeapons.weapon.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main extends PluginBase {
    private static HashSet<BlockFace> blockFaces = new HashSet<>();
    private static List<Block> colors = new ArrayList<>();
    private static List<Weapon> weapons = new ArrayList<>();

    @Override
    public void onLoad() {
        /* Fill lists */
        Collections.addAll(
                blockFaces,
                BlockFace.NORTH,
                BlockFace.SOUTH,
                BlockFace.WEST,
                BlockFace.EAST,
                BlockFace.UP,
                BlockFace.DOWN
        );

        Collections.addAll(
                weapons,
                new Weapon("§r§fArcher", Item.get(ItemID.ARROW)),
                new Weapon("§r§fArmageddon", Item.get(ItemID.FIRE_CHARGE)),
                new Weapon("§r§fBlobinator", Item.get(ItemID.MAGMA_CREAM)),
                new Weapon("§r§fGrenade", Item.get(ItemID.EGG)),
                new Weapon("§r§fLaser", Item.get(ItemID.BLAZE_POWDER)),
                new Weapon("§r§fLightning Stick", Item.get(ItemID.STICK)),
                new Weapon("§r§fMachine Gun", Item.get(ItemID.GOLD_HORSE_ARMOR)),
                new Weapon("§r§fNuker", Item.get(ItemID.BLAZE_ROD)),
                new Weapon("§r§fSniper", Item.get(ItemID.IRON_HORSE_ARMOR)),
                new Weapon("§r§fAnvil Dropper", Item.get(Block.get(BlockID.ANVIL).getItemId()))
        );

        for(int i = 0; i <= 15; i ++) {
            colors.add(BlockState.of(BlockID.WOOL, i).getBlock());
        }
    }

    @Override
    public void onEnable() {
        /* Commands */
        ((PluginCommand<?>)this.getCommand("weapons")).setExecutor(new CommandWeapons());


        /* Player Events */
        /*this.getServer().getPluginManager().registerEvents(new PlayerReceiveWeapon(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerUseWeapon(), this);*/

        /* Weapon Events */
        /*this.getServer().getPluginManager().registerEvents(new WeaponArcher(), this);
        this.getServer().getPluginManager().registerEvents(new WeaponArmageddon(), this);
        this.getServer().getPluginManager().registerEvents(new WeaponBlobinator(), this);
        this.getServer().getPluginManager().registerEvents(new WeaponGrenade(), this);
        this.getServer().getPluginManager().registerEvents(new WeaponMachineGun(), this);
        */
        this.getServer().getPluginManager().registerEvents(new WeaponArcher(), this);
        this.getServer().getPluginManager().registerEvents(new WeaponArmageddon(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerUseWeapon(), this);
    }

    public static HashSet<BlockFace> getBlockFaces() {
        return blockFaces;
    }

    public static List<Block> getColors() {
        return colors;
    }

    public static List<Weapon> getWeapons() {
        return weapons;
    }
}
