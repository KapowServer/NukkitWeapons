package me.allink.NukkitWeapons.modules.weapons;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.BlockID;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
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

public class WeaponArcher implements Listener {
    public static void click(final Item item, final String name, final PlayerInteractEvent event) {
        if (item.getId() == ItemID.ARROW
                && ("§r§fArcher".equals(name))) {
            final Player player = event.getPlayer();
            final Level level = player.getLevel();

            final int maxArrowCount = 20;

            for (int i = 0; i <= maxArrowCount; i++) {
                final double randomX = (Math.random() * ((15 + 15) + 1)) - 15;
                final double randomY = (Math.random() * ((15 + 15) + 1)) - 15;
                final double randomZ = (Math.random() * ((15 + 15) + 1)) - 15;

                CompoundTag nbt = new CompoundTag();
                nbt.putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", player.getX()))
                        .add(new DoubleTag("", player.getY()))
                        .add(new DoubleTag("", player.getZ())));
                nbt.putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", randomX))
                        .add(new DoubleTag("", randomY))
                        .add(new DoubleTag("", randomZ)));
                nbt.putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", 0))
                        .add(new FloatTag("", 0)));
                nbt.putInt("TileID", BlockID.ANVIL);

                Entity arrow = Entity.createEntity("Arrow", player.getChunk(), nbt);
                arrow.spawnToAll();
                arrow.setNameTag("WeaponArcherArrow|Owner" + event.getPlayer().getName());
            }

            final Location eyeLocation = player.getTargetBlock(1000).getLocation();
            final float volume = 1.0F;
            final float pitch = 1.5F;

            level.addSound(
                    eyeLocation,
                    Sound.DAMAGE_FALLBIG,
                    volume,
                    pitch
            );
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof EntityArrow) {
            if(event.getDamager().hasCustomName()) {
                String customName = event.getDamager().getNameTag();

                if(customName.startsWith("WeaponArcherArrow|Owner")) {
                    String owner = customName.split("WeaponArcherArrow\\|Owner")[1];
                    if(event.getEntity() instanceof EntityHuman) {
                        Player ownerPlayer = Server.getInstance().getPlayer(owner);
                        try {
                            Player entityPlayer = (Player) event.getEntity();
                            if(ownerPlayer != null && entityPlayer.getName() == owner) {
                                event.setCancelled(true);
                            }
                        } catch (ClassCastException e) {
                            //ignored
                        }
                    }
                }
            }
        }
    }
}
