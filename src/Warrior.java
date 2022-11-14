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
        this.stamina = (int) (Math.random()*40+10);
    }

    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = (int) (Math.random()*10+0);
    }
    public int setRandomHp(){
        int random=(int)(Math.random()*100+100);
        this.setHp(random);
        return random;
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


    @Override
    void Attack(Character character) {
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
