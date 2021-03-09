package me.aurium.aesthetikshot.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.shampaggon.crackshot.events.WeaponHitBlockEvent;
import me.aurium.aesthetikshot.AesthetikShot;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.net.http.WebSocket;
import java.util.Random;


public class BulletHitBlockListener implements Listener{
    private final ProtocolManager manager;
    private final AesthetikShot shot;

    public BulletHitBlockListener(AesthetikShot shot, ProtocolManager manager) {
        this.manager = manager;
        this.shot = shot;
    }

    @EventHandler
    public void onBlockHitEvent(final WeaponHitBlockEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        location.getWorld().playSound(location, Sound.BLOCK_METAL_BREAK, 1.0f, 1.0f);

        //hacky packet shit idc
        Random random = new Random();

        PacketContainer container = new PacketContainer(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        container.getIntegers().write(0, random.nextInt());
        container.getBlockPositionModifier().write(0,new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        container.getBytes().write(0, (byte) 5);

        shot.getServer().getOnlinePlayers().forEach(player1 -> {
            try {
                manager.sendServerPacket(player1,container);
            } catch (InvocationTargetException e) {
                throw new UncheckedPacketException(container,e);
            }
        });


    }
}
