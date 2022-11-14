public class Warrior extends Character {
    private int stamina; //random 10-50
    private int strength; //random 1-10

    //constructor name, hp, stamina, strength
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
          setStamina(stamina);
          setStrength(strength);
    }
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

     public int heavyAttack(){
        int damage = getStrength();
        setStamina(getStamina() -5);
        return damage;
    }
    public int weakAttack(){
        int damage = getStrength()/2;
        setStamina(getStamina() + 1);
        return damage;
    }

    int calculateDamage() {
        if (getStamina()>=5){
            return heavyAttack();
        }
        if (getStamina()==0){
            setStamina(getStamina() + 2);
            return 0;
        }
        if (getStamina()<5){
            return weakAttack();
        }
        return 0;
         }

    @Override
    public void attack(Character character) {
        int damage = calculateDamage();
        character.setHp(character.getHp()-damage);
    }
}
