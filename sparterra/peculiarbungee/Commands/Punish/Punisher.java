package plugin.sparterra.peculiarbungee.Commands.Punish;

import plugin.sparterra.peculiarbungee.Main;

import java.util.Random;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class Punisher {
    public static int generateRandomID()
    {
        Random r = new Random();
        int i = r.nextInt(999999999);
        if (!checkInt(i)) {
            return generateRandomID();
        }
        return i;
    }
    public static boolean checkInt(int i)
    {
        try
        {
            if (Main.config.getBoolean("i")) {
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
