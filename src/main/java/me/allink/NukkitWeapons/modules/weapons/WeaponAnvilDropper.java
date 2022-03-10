package me.allink.NukkitWeapons.modules.weapons;

import cn.nukkit.block.BlockID;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityFallingBlock;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

public class WeaponAnvilDropper {
    public static void click(final Item item, final String name, final PlayerInteractEvent event) {
        if (item.getBlockId() == BlockID.ANVIL
                && ("§r§fAnvil Dropper".equals(name))) {
            final int min = -2;
            final int max = 2;

            for (int x = min; x <= max; x++) {
                for (int z = min; z <= max; z++) {
                    final Location blockLocation = event.getPlayer().getLocation().subtract(x, 0, z);
                    final Level level = event.getPlayer().getLevel();

                    CompoundTag nbt = new CompoundTag();
                    nbt.putList(new ListTag<>("Pos")
                            .add(new DoubleTag("", blockLocation.getX()))
                            .add(new DoubleTag("", blockLocation.getY()))
                            .add(new DoubleTag("", blockLocation.getZ())));
                    nbt.putList(new ListTag<DoubleTag>("Motion")
                            .add(new DoubleTag("", 0))
                            .add(new DoubleTag("", 0))
                            .add(new DoubleTag("", 0)));
                    nbt.putList(new ListTag<FloatTag>("Rotation")
                            .add(new FloatTag("", 0))
                            .add(new FloatTag("", 0)));
                    nbt.putInt("TileID", BlockID.ANVIL);

                    Entity fallingBlock = Entity.createEntity(EntityFallingBlock.NETWORK_ID, blockLocation.getChunk(), nbt);
                    fallingBlock.spawnToAll();
                }
            }
            event.setCancelled(true);
        }
    }
}
