package me.allink.NukkitWeapons.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import me.allink.NukkitWeapons.Main;
import me.allink.NukkitWeapons.weapon.Weapon;

import java.util.List;
import java.util.Locale;

public class CommandWeapons implements CommandExecutor {
    private void addWeapon(final Inventory inventory, final Item item, final String name) {
        item.setCustomName(name);
        inventory.addItem(item);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Command has to be run by a player");
        } else {
            final Player player = (Player) sender;
            final String weaponName = args[0];
            Weapon foundWeapon = null;
            final List<Weapon> weapons = Main.getWeapons();

            for(Weapon weapon: weapons) {
                if(weapon.name.toLowerCase(Locale.ROOT).contains(weaponName.toLowerCase(Locale.ROOT))) {
                    foundWeapon = weapon;
                    break;
                }
            }

            if(foundWeapon != null) {
                final Item item = foundWeapon.item;
                item.setCustomName(foundWeapon.name);
                player.giveItem(item);

                sender.sendMessage("Given you the " + foundWeapon.name + "!");
            } else {
                sender.sendMessage(TextFormat.RED + String.format("Weapon \"%s\" not found.", weaponName));
            }
        }

        return true;
    }
}
