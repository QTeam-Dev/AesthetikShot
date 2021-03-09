package gg.qteam.aesthetikshot.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import gg.qteam.aesthetikshot.AesthetikShot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class ShootHideBulletListener implements Listener {

    private final AesthetikShot shot;
    private final ProtocolManager manager;

    public ShootHideBulletListener(AesthetikShot shot, ProtocolManager manager) {
        this.shot = shot;
        this.manager = manager;
    }

    @EventHandler
    public void weaponShootEventDetec(WeaponShootEvent event) {
        int entityID = event.getProjectile().getEntityId();

        Random random = new Random();

        PacketContainer container = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);


        container.getIntegerArrays().write(0,new int[]{entityID});


        shot.getServer().getOnlinePlayers().forEach(player1 -> {
            try {
                manager.sendServerPacket(player1,container);
            } catch (InvocationTargetException e) {
                throw new UncheckedPacketException(container,e);
            }
        });

    }

}
