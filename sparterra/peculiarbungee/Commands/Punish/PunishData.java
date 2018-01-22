package plugin.sparterra.peculiarbungee.Commands.Punish;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class PunishData {
    private int id;
    private String player;
    private String type;
    private String reason;
    private String expire;
    private String punisher;

    public PunishData(int id, String player, String type, String reason, String expire, String punisher)
    {
        this.player = player;
        this.type = type;
        this.reason = reason;
        this.expire = expire;
        this.punisher = punisher;
    }

    public String getPlayer()
    {
        return this.player;
    }

    public String getType()
    {
        return this.type;
    }

    public String getReason()
    {
        return this.reason;
    }

    public String getExpire()
    {
        return this.expire;
    }

    public String getPunisher()
    {
        return this.punisher;
    }

    public int getId()
    {
        return this.id;
    }
}
