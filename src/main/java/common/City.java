package common;

/**
 * Represents a city in the game.
 * <p>
 * Responsibilities:
 * 1) Knows its owner.
 * 2) Knows its population size.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public interface City {
    /**
     * return the owner of this city.
     *
     * @return the player that controls this city.
     */
    Player getOwner();

    void setOwner(Player owner);

    /**
     * return the size of the population.
     *
     * @return population size.
     */
    int getSize();

    void setSize(int size);

    /**
     * return the type of unit this city is currently producing.
     *
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    String getProduction();

    /**
     * return the work force's focus in this city.
     *
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */

    void setProduction(String production);

    String getWorkforceFocus();

    /**
     * treasure size
     */
    int getTreasure();

    void setTreasure(int treasure);

}
