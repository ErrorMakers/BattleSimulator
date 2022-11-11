public class Warrior extends Character implements Attacker{
    private int stamina; //random 10-50
    private int strength; //random 1-10

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    //constructor name, hp, stamina, strength
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        this.stamina = stamina;
        this.strength = strength;
    }

    public int setRandomHp(){
        int random = (int) (Math.random()*100+100);
        this.setHp(random);
        return random;
    }

    public int setRandomStamina(){
        int random = (int) (Math.random()*40+10);
        this.setStamina(random);
        return random;
    }

    public int setRandomStrength(){
        int random = (int) (Math.random()*10+0);
        this.setStrength(random);
        return random;
    }

    public int attack(){
        if (getStamina()>=5){
            return heavyAttack();
        }
        if (getStamina()<5){
            return weakAttack();
        }
        if (getStamina()==0){
            setStamina(getStamina() + 2);
        }
    }



}
