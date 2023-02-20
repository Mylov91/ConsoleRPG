public class Hero extends FantasyCharacter {
    private static int heroLevel;
    private static int countOfPoisons;
    private static int maxHp;


    public Hero(String name, int healthPoints, int strength, int dexterity, int xp, int gold, int heroLevel, int countOfPoisons, int maxHp) {
        super(name, healthPoints, strength, dexterity, xp, gold);
        this.heroLevel = heroLevel;
        this.countOfPoisons = countOfPoisons;
        this.maxHp = maxHp;
    }


    public static int getHeroLevel() {
        return heroLevel;
    }

    public static int getCountOfPoisons() {
        return countOfPoisons;
    }

    public static void setHeroLevel(int heroLevel) { Hero.heroLevel = heroLevel; }

    public static void setCountOfPoisons(int countOfPoisons) {
        Hero.countOfPoisons = countOfPoisons;
    }

    public static int getMaxHp() { return maxHp; }

    public static void setMaxHp(int maxHp) { Hero.maxHp = maxHp; }
}