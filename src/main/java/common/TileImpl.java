package common;


public class TileImpl implements Tile {

    City city;
    Unit unit;
    String tile;

    public TileImpl(String tile) {
        this.tile = tile;
    }

    @Override
    public String getTypeString() {
        return tile;
    }

    public City getCity() {
        return city;
    }

    public Unit getUnit() {
        return unit;
    }

}
