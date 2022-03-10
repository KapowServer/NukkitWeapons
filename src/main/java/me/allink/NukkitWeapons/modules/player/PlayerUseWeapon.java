package me.allink.NukkitWeapons.modules.player;

import cn.nukkit.block.BlockID;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import me.allink.NukkitWeapons.modules.weapons.WeaponAnvilDropper;
import me.allink.NukkitWeapons.modules.weapons.WeaponArcher;
import me.allink.NukkitWeapons.modules.weapons.WeaponArmageddon;

public class PlayerUseWeapon implements Listener {
    @EventHandler
    void onPlayerInteract(final PlayerInteractEvent event) {
        System.out.println("hi");
        if(event.getItem() != null) {
            if(event.getItem().getBlockId() == BlockID.AIR) {
                return;
            }
        } else {
            if(event.isCancelled()) {
                return;
            }
        }

        String name = "";

        name = event.getItem().getCustomName();

        final PlayerInteractEvent.Action action = event.getAction();
        final Item item = event.getItem();

        WeaponAnvilDropper.click(item, name, event);
        WeaponArcher.click(item, name, event);
        WeaponArmageddon.click(item, name, event);
    }
}
