package common;

public class CityImpl implements City {

    private Player owner;
    private String production;

    GameConstants constants = new GameConstants();

    private int treasure = 0;
    private int size = 0;

    public CityImpl(Player owner) {
        this.owner = owner;
        this.production = "none";
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String getWorkforceFocus() {
        return GameConstants.productionFocus;
    }

    @Override
    public int getTreasure() {
        return treasure;
    }

    @Override
    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }
}
