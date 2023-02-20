import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Tavern {
    private static int potionsOnSale;
    static Scanner sc = new Scanner(System.in);


    public Tavern() {
        potionsOnSale = 35;
    }


    protected static void startTrading() {

        int buyingPotions = 0;

        System.out.println("Количество лечебных зелий торговца на продажу: " + potionsOnSale + ". Цена 1 зелья - 20 монет.");
        System.out.println("У героя " + Main.player.getName() + " есть " + Main.player.getGold() + " монет. Сколько зелий вы хотите купить? Введите число и нажмите ENTER");
        String enteredValue = sc.nextLine();

        try {
            if ((parseInt(enteredValue) >= 0)) {
                buyingPotions = parseInt(enteredValue);
            } else {
                System.out.println("Введите число большее, или равное нулю");
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод. Введите число");
        }


        if (potionsOnSale - buyingPotions >= 0 && (Main.player.getGold() - (buyingPotions * 20)) >= 0) {
            Hero.setCountOfPoisons(Hero.getCountOfPoisons() + buyingPotions);
            potionsOnSale = potionsOnSale - buyingPotions;
            Main.player.setGold(Main.player.getGold() - buyingPotions * 20);
            System.out.println("Ваш запас зелий пополнен на " + buyingPotions + "\n");
        } else {
            System.out.println("У вас не хватает золота или у торговца нет столько зелий лечения\n");
        }
        Main.printMenu();
    }


    protected static void goToSleep() {
        if (Main.player.getGold() > 300) {
            Main.player.setGold(Main.player.getGold() - 200);
            Main.player.setHealthPoints(Hero.getMaxHp());
            System.out.println("Вы хорошо отдохнули и полностью восстановили здоровье. Ваш текущий уровень здоровья: " + Main.player.getHealthPoints() + "\n");
        } else {
            System.out.println("Аренда комнаты в таверне стоит 300 монет. Вам не хватает денег для аренды комнаты\n");
        }
        Main.printMenu();
    }


    protected static void makeLevelUp() {
        if (Main.player.getXp() >= 500) {
            Hero.setMaxHp(Hero.getMaxHp() + 5);
            Main.player.setStrength(Main.player.getStrength() + 5);
            Main.player.setDexterity(Main.player.getDexterity() + 5);
            Main.player.setXp(Main.player.getXp() - 500);
            Hero.setHeroLevel(Hero.getHeroLevel() + 1);
            System.out.println("Мудрец открыл вам тайное знание. Ваша сила теперь равняется " + Main.player.getStrength() + ", ловкость - " + Main.player.getDexterity() + ", максимальный запас здоровья - " + Hero.getMaxHp() + "\n");
        } else {
            System.out.println("Вы еще не набрались достаточно опыта. Сражайтесь больше! \n");
        }
        Main.printMenu();
    }
}