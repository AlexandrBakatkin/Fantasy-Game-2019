package sample;

class Warrior extends Hero {

    public Warrior(int health, String name, int damage, int addHeal) {
        super(health, name, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        if (hero != this) {
            if (health < 0) {
                return (this.name + " погиб и бить не может!");
            } else {
                hero.causeDamage(damage);
            }
            return (this.name + " нанес урон " + hero.name);
        } return (this.name + " промахнулся");
    }

    @Override
    String healing(Hero hero) {
        return ("Войны не умеют лечить!");
    }

}
