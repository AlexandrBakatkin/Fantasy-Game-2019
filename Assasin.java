package sample;

import java.util.Random;

class Assasin extends Hero {
    // критический урон
    int cricitalHit;
    Random random = new Random();

    public Assasin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.cricitalHit = random.nextInt(20);
    }

    @Override
    String hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                return (this.name + " погиб и бить не может!");
            } else {
                hero.causeDamage(damage + cricitalHit);
            }
            return (this.name + " нанес урон " + hero.name);
        } return (this.name + " промахнулся");
    }

    @Override
    String healing(Hero hero) {
        return ("Убийцы не умеют лечить!");
    }
}
