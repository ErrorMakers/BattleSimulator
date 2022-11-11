public abstract class Character {
    private String id;
    private String name;
    private int hp;
    private boolean isAlive;
    private static Integer counter = 0;
    abstract void Attack(Character character);

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
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
