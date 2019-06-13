package sample;

abstract class Hero {

    protected int health;
    protected String name;
    protected int damage;
    protected int addHeal;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    // метод для удара
    abstract void hit(Hero hero);

    // метод для лечения
    abstract void healing(Hero hero);

    // метод для нанесения урона
    void causeDamage(int damage) {
        if(health < 0) {
            System.out.println(this.name + " уже мертвый!");
        } else {
            health -= damage;
        }

    }

    public int getHealth() {
        return health;
    }

    void addHealth(int health) {
        this.health += health;
    }

    public String info() {
        return (name + ": HP - " + (health < 0 ? "Герой мертв" : health) + "; Урон - " + damage + "\n");
    }
}
