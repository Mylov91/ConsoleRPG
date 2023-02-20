import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static BufferedReader br;
    public static FantasyCharacter player = null;
    private static BattleScene battleScene = null;
    private static Tavern tavern = null;
    private static int drinkedPotions = 0;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        tavern = new Tavern();

        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0,
                    1,
                    0,
                    100
            );
            System.out.println(String.format("Добро пожаловать, %s!", player.getName()));
            printMenu();
        }

        switch (string) {
            case "1": {
                commitFight();
            }
            break;
            case "2": {
                Tavern.startTrading();
            }
            break;
            case "3": {
                Tavern.goToSleep();
            }
            break;
            case "4": {
                drinkPotion();
            }
            break;
            case "5": {
                Tavern.makeLevelUp();
            }
            break;
            case "6": {
                printHeroParameters();
            }
            break;
            case "7":
                System.exit(1);
                break;
            case "да":
                command("1");
                break;
            case "нет": {
                printMenu();
            }
            break;
        }
        command(br.readLine());
    }


    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.exit(1);
            }
        });
    }


    protected static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1. Пойти в темный лес");
        System.out.println("2. Пойти к торговцу");
        System.out.println("3. Арендовать комнату в таверне");
        System.out.println("4. Выпить лечебное зелье");
        System.out.println("5. Пойти к мудрецу для повышения уровня");
        System.out.println("6. Посмотреть параметры своего героя");
        System.out.println("7. Выйти из игры");
    }


    private static void printHeroParameters() throws IOException {
        System.out.println("Имя героя: " + player.getName());
        System.out.println("Уровень героя: " + Hero.getHeroLevel());
        System.out.println("Очков опыта: " + player.getXp());
        if ((500 - player.getXp()) <= 0) {
            System.out.println("Очков опыта до следующего уовня: 0. Вам достаточно опыта, чтобы повысить уровень у мудреца.");
        } else {
            System.out.println("Очков опыта до следующего уовня: " + (500 - player.getXp()));
        }
        System.out.println("Сила: " + player.getStrength());
        System.out.println("Ловкость: " + player.getDexterity());
        System.out.println("Очков здоровья: " + player.getHealthPoints() + " из " + Hero.getMaxHp());
        System.out.println("Монеты: " + player.getGold());
        System.out.println("Зелья здоровья: " + Hero.getCountOfPoisons() + "\n");
        printMenu();
        command(br.readLine());
    }


    private static void drinkPotion() {
        if (Hero.getCountOfPoisons() > 0) {
            Hero.setCountOfPoisons(Hero.getCountOfPoisons() - 1);
            if ((player.getHealthPoints() + 10) > Hero.getMaxHp()) {
                player.setHealthPoints(Hero.getMaxHp());
            } else {
                player.setHealthPoints(player.getHealthPoints() + 10);
            }
            System.out.println("Лечебное зелье восстановило вам 10 очков здоровья. Ваше здоровье теперь равняется " + player.getHealthPoints() + " из " + Hero.getMaxHp() + ". Ваш запас зелий - " + Hero.getCountOfPoisons() + "\n");
            drinkedPotions++;
        } else {
            System.out.println("У вас нет лечебного зелья. Купите его у торговца. \n");
        }

        if (drinkedPotions == 10) {
            Hero.setMaxHp(Hero.getMaxHp() + 10);
            System.out.println("Из-за частого употребления лечебных зелий ваш запас здоровья увеличен на 10 навсегда! \n");
            drinkedPotions = 0;
        }
        printMenu();
    }


    private static FantasyCharacter createMonster() {
        int random = (int) (Math.random() * 12);
        if (random >= 0 && random < 5) return new Goblin(
                "Гоблин",
                50,
                10,
                10,
                100,
                20
        );
        else if (random >= 5 && random < 10) return new Skeleton(
                "Скелет",
                30,
                20,
                20,
                100,
                10
        );
        else return new Troll(
                    "Свирепый сумрачный тролль",
                    150,
                    30,
                    5,
                    500,
                    350
            );
    }


    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}