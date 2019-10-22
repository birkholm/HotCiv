package variants.gammaCiv;

import common.MoveStrategy;
import common.Unit;

public class GammaMoveStrategy implements MoveStrategy {

    @Override
    public boolean moveUnit(Unit unit) {

        return !unit.isFortify();
    }


}
