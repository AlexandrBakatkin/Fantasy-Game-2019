package sample;

class Doctor extends Hero {

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    void hit(Hero hero) {
        System.out.println("Доктор не может бить!");
    }

    @Override
    void healing(Hero hero) {
        if(hero.health <= 0){
            System.out.println(hero.name + " погиб, вылечить невозможно");
            return;
        }
        hero.addHealth(addHeal);
        System.out.println(this.name + " прибавил " + addHeal + " очков здоровья " + hero.name);
    }
}
