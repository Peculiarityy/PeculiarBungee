package plugin.sparterra.peculiarbungee.Rank;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/16/2017
 * All work belongs to ShadyCarpet
 */

    public enum Rank
    {
        DEFAULT("§8[§7Peculiar§8]", "§7", 24),
        MAGPIE("§e[§aPeculiar§e]", "§a", 23),
        CARDINAL("§c[§4Peculiar§c]", "§4", 22),
        GOLDEN_EAGLE("§e[§6Peculiar§e]", "§6", 21),
        PEREGRINE("§8[§9Peculiar§8]", "§1", 20),
        INTERMEDIATE("§b[§3Peculiar§b]", "§3", 19),
        ADVANCED("§6[§ePeculiar§6]", "§6", 18),
        VIP("§2[§aVIP§2]", "§a", 17),
        DESIGNER("§c[§4Designer§c]", "§4", 16),
        DIMEN_EDITOR("§6[§eEditor§6]", "§e", 15),
        DIRECTOR("§4[§cDirector§4]", "§c", 14),
        ARCHITECT("§2[§aArchitect§2]", "§a", 13),
        DEVELOPER("§1[§9Developer§1]", "§9", 12),
        MOD("§a[§2Mod§a]", "§a", 11),
        SR_MOD("§a[§2Sr§8.§2 Mod§a]", "§2", 10),
        HOST("§5[§dHost§5]", "§d", 9),
        ASSISTANT_DIMEN("§8[§6Assistant§8]", "§6", 8),
        ASSISTANT_ARCH("§8[§2Assistant§8]", "§3", 7),
        ASSISTANT_DEV("§8[§bAssistant§8]", "§b", 6),
        MANAGER_DIMEN("§8[§6Manager§8]", "§6", 5),
        MANAGER_ARCH("§8[§aManager§8]", "§3", 4),
        ADMIN("§4[§cAdmin§4]", "§c", 3),
        MANAGER_DEV("§8[§bManager§8]", "§b", 2),
        CO_OWNER("§8[§5Co-Owner§8]", "§5", 1),
        OWNER("§5[§dOwner§5]", "§d", 0);

        private String prefix;
        private String color;
        private int ladder;

        private Rank(String prefix, String color, int ladder)
        {
            this.prefix = prefix;
            this.color = color;
            this.ladder = ladder;
        }

        public String getPrefix()
        {
            return this.prefix;
        }

        public String getColor()
        {
            return this.color;
        }

        public int getLadder()
        {
            return this.ladder;
        }
}
