package lucien.SpeedrunWithHunter.kits;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

public class Warper {
    public static ConcurrentHashMap<UUID, UUID> warpersAndPearls = new ConcurrentHashMap<UUID, UUID>();
    public static List<UUID> antiFallDamage = new ArrayList<UUID>();
    public static UUID nullUUID = UUID.randomUUID();

    public static void addWarp(Projectile pearl, Player warper) {
	if(!warpersAndPearls.containsKey(warper.getUniqueId()))
	    warpersAndPearls.put(warper.getUniqueId(), pearl.getUniqueId());
	if(!antiFallDamage.contains(warper.getUniqueId()))
	    antiFallDamage.add(warper.getUniqueId());
	warper.playSound(warper.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.5f, 1f);
	pearl.addPassenger(warper);
    }
}
