/*
This class is derived from the Character class.
A Wizard is a Character that fights by casting spells.
Spells inflict damage and require mana to be cast.

The Wizard class will have:

Variable called mana of data type int, random between 10-50,
representing a resource the wizard consumes to cast spells (Private member)
Variable called intelligence of data type int, random between 1-50,
measuring how strong the wizard spells are (Private member)
Public Getter functions to access these variables
Public Setter functions to change these variables
A parameterized constructor that takes name, hp, mana, and intelligence
A public function that overloads attack() implemented in the Attacker interface,
that will take a character as a parameter and reduce that character’s health
based on the intelligence of the spell
 */

public class Wizard extends Character {

    private int mana; // random between 10-50
    private int intelligence; // random between 1-50
    protected int[] initialState = new int[2];
    // A parameterized constructor that takes name, hp, mana, and intelligence

    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        setMana(mana);
        setIntelligence(intelligence);
        race = Race.WIZARD;
        setInitialState(new int[]{hp, mana});
    }

    // getters
    public int getMana() {
        return mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    // setters
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence =  intelligence;
    }

    public int fireball(){
        int damage = getIntelligence();
        setMana(getMana() -5);
        System.out.println(getName() + " casts a huge fireball for " + damage + " damage and loses 5 mana");
        return damage;
    }
    public int staffHit(){
        int damage = getIntelligence()/2;
        setMana(getMana() + 1);
        System.out.println(getName() + " does a staff hit for " + damage + " damage and restores 1 mana");
        return damage;
    }

    int calculateDamage() {
        if (Game.rand.nextInt(101) >= 50 && getMana()>=5){
            return fireball();
        }
        else if (getMana()==0){
            setMana(getMana() + 2);
            System.out.println(getName() + "does nothing due to lack of mana and restores 2 mana");
            return 0;
        }
        else{
            return staffHit();
        }
    }

    public int[] getInitialState() {
        return initialState;
    }

    public void setInitialState(int[] initialState) {
        this.initialState = initialState;
    }

    @Override
    public void attack(Character character) {
        int damage = calculateDamage();
        character.setHp(character.getHp()-damage);
    }

    @Override
    public String toString() {
        return getName() + "(" + this.getClass().getName() + ")" +
                "[HP: " + getHp() + "][MANA: " + mana + "][INT: " + intelligence +
                "]";
    }

}
