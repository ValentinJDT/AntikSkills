package antikskills.players;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AntikPlayer {

    private String name;
    private int level = 1;
    private int exp = 0;
    private int rewardPoints = 0;
    private int boost = 0;

    public AntikPlayer() {}

    public AntikPlayer(String name) {
        this.name = name;
    }

    public int expForLevel() {
        // return (int) Math.exp(level) * 10;
        //return (int) Math.sqrt(level) * 65;
        //return (int) (Math.exp(level) * 15) / 2;
        //return (int) (Math.pow(level, 2.5d)*10)+50;
        return (int) (Math.pow(level, 2d)*Math.exp(3.7)) +250;
    }

    public int expNeededForLevel() {
        return expForLevel() - exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void removeRewardPoint() {
        rewardPoints--;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addExp(int exp) {

        Player player = Bukkit.getPlayer(this.name);

        int calc = exp*(1+(boost/100));

        if(calc != 0 && player.isOp())
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§b+ " + calc + " exp"));

        this.exp += calc;

        while(expNeededForLevel() <= 0) {
            this.exp = this.exp - expForLevel();
            this.level += 1;
            String pts = "";

            if(this.level%10 == 0) {
                rewardPoints++;
                pts = "\n§aPoint de récompense +1";
            }

            player.sendMessage("§7Vous venez de passer niveau §b" + this.level + pts);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f,1.0f);

        }

    }
}
