package gg.qteam.aesthetikshot.listeners;

import com.comphenix.protocol.events.PacketContainer;

public class UncheckedPacketException extends RuntimeException{

    public UncheckedPacketException(PacketContainer container, Throwable throwable) {
        super("Cannot send container " + container,throwable);
    }

}
