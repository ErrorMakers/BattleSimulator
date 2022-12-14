public abstract class Character implements Attacker {

    private String id;
    private String name;
    private int hp;
    private boolean isAlive;
    private static Integer counter = 0;
    protected Race race;


    public abstract void attack(Character character);


    public Character(String name, int hp) {
        setId();
        this.name = name;
        this.hp = hp;
        this.isAlive = true;

    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = counter.toString();
        counter ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {

        this.hp = hp;
        if(hp <= 0) isAlive = false;
        else if (hp > 0) isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


}
