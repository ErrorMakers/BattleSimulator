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
    int upperMana = 10;
    int lowerMana = 50;

    private int intelligence; // random between 1-50
    int upperIntelligence = 1;
    int lowerIntelligence = 50;

    // A parameterized constructor that takes name, hp, mana, and intelligence


    public Wizard(String name, int hp, int mana, int intelligence) {
        super(String name, int hp);
        setMana();
        setIntelligence();
    }

    // getters
    public int getMana() {
        return mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    // setters
    public void setMana() {
        this.mana = (int) (Math.random() * (upperMana - lowerMana)) + lowerMana;
    }

    public void setIntelligence() {
        this.intelligence =  (int) (Math.random() * (upperIntelligence - lowerIntelligence)) + lowerIntelligence;
    }

    public void attack(Character enemy) {
        enemy.setHp = enemy.getHp - getIntelligence;

    }
}
