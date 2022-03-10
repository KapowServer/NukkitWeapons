package me.allink.NukkitWeapons.weapon;

import cn.nukkit.item.Item;

public class Weapon {
    public String name;
    public Item item;

    /**
     * Create a weapon
     * @param name Item name
     * @param item Item object
     */
    public Weapon(String name, Item item) {
        this.name = name;
        this.item = item;
    }
}
