package sample;

class Doctor extends Hero {

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        return ("Доктор не может бить!");
    }

    @Override
    String healing(Hero hero) {
        if(hero.health <= 0){
            return (hero.name + " погиб, вылечить невозможно");
        }
        hero.addHealth(addHeal);
        return (this.name + " прибавил " + addHeal + " очков здоровья " + hero.name);
    }
}
