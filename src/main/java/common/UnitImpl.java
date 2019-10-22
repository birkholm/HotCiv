package common;

public class UnitImpl implements Unit {

    String type;
    Player owner;

    int defensiveStrength;


    boolean fortify;


    public UnitImpl(String type, Player owner) {
        this.type = type;
        this.owner = owner;
        this.defensiveStrength = 3;
        this.fortify = false;
    }

    @Override
    public boolean isFortify() {
        return fortify;
    }

    @Override
    public void setFortify(boolean b) {
        fortify = !fortify;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public void setDefensiveStrength(int defensiveStrength) {
        this.defensiveStrength = defensiveStrength;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
