public class Warrior extends Character {
    private int stamina; //random 10-50
    private int strength; //random 1-10
    protected int[] initialState = new int[2];
    //constructor name, hp, stamina, strength
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        setStamina(stamina);
        setStrength(strength);
        race = Race.WARRIOR;
        setInitialState(new int[]{hp, stamina});
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
        System.out.println(getName() + " does a heavy attack for " + damage + " damage and loses 5 stamina");
        return damage;
    }
    public int weakAttack(){
        int damage = getStrength()/2;
        setStamina(getStamina() + 1);
        System.out.println(getName() + " does a weak attack for " + damage + " damage and restores 1 stamina");
        return damage;
    }

    int calculateDamage() {
        if (Game.rand.nextInt(101) >= 50 && getStamina()>=5){
            return heavyAttack();
        }
        else if (getStamina()==0){
            setStamina(getStamina() + 2);
            System.out.println(getName() + " does nothing due to lack of stamina and restores 2 stamina");
            return 0;
        }
        else {
            return weakAttack();
        }
    }

    @Override
    public void attack(Character character) {
        int damage = calculateDamage();
        character.setHp(character.getHp()-damage);
    }

    public int[] getInitialState() {
        return initialState;
    }

    public void setInitialState(int[] initialState) {
        this.initialState = initialState;
    }

    @Override
    public String toString() {
        return getName() + "(" + this.getClass().getName() + ")" +
                "[HP: " + getHp() + "][STA: " + stamina + "][STR: " + strength +
                "]";
    }
}
