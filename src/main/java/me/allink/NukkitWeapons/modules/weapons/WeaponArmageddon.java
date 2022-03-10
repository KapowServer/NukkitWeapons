package me.allink.NukkitWeapons.modules.weapons;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileHitEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import nukkitcoders.mobplugin.entities.projectile.EntityGhastFireBall;

public class WeaponArmageddon implements Listener {
    public static void click(final Item item, final String name, final PlayerInteractEvent event) {
        if (item.getId() == ItemID.FIRE_CHARGE
                && ("§r§fArmageddon".equals(name))) {
            final Player player = event.getPlayer();
            final Level level = player.getLevel();

            final int min = -1;
            final int max = 1;
            final int iteratorSpacing = 4;

            for (int i = min; i <= max; i += iteratorSpacing) {
                for(int x = min; x < max; x++) {
                    for(int z = min; z < max; z++) {
                        final Location eyeLocation = player.getLocation();

                        final double x1 = i * Math.cos(Math.toRadians(eyeLocation.getYaw()));
                        final double z1 = i * Math.sin(Math.toRadians(eyeLocation.getYaw()));

                        final int yield = 5;

                        final CompoundTag nbt = new CompoundTag();
                        nbt.putList(new ListTag<>("Pos")
                                .add(new DoubleTag("", player.getX() - x))
                                .add(new DoubleTag("", player.getY() + x + z))
                                .add(new DoubleTag("", player.getZ() - z)));
                        nbt.putList(new ListTag<DoubleTag>("Motion")
                                .add(new DoubleTag("", x1))
                                .add(new DoubleTag("", -5))
                                .add(new DoubleTag("", z1)));
                        nbt.putList(new ListTag<FloatTag>("Rotation")
                                .add(new FloatTag("", 0))
                                .add(new FloatTag("", 0)));

                        final EntityGhastFireBall fireball = (EntityGhastFireBall) Entity.createEntity(EntityGhastFireBall.NETWORK_ID, player.getChunk(), nbt);

                        fireball.spawnToAll();
                        fireball.setExplode(true);
                        fireball.setNameTagVisible(false);
                    }
                }

            }

            final Location eyeLocation = player.getTargetBlock(1000).getLocation();
            final float volume = 0.9F;
            final float pitch = 1.5F;

            level.addSound(
                    eyeLocation,
                    Sound.MOB_GHAST_FIREBALL,
                    volume,
                    pitch
            );
            event.setCancelled(true);
        }
    }

    @EventHandler
    final void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity().getNetworkId() == EntityGhastFireBall.NETWORK_ID) {
            event.setCancelled(true);
        }
    }
}
