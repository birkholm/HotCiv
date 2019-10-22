package common;

/**
 * Position on the world map.
 * <p>
 * Responsibilities:
 * 1) Know a specific location (row,column).
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
public class Position {

    /**
     * create a position.
     *
     * @param r the row
     * @param c the column
     */
    public Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    protected int r;
    protected int c;

    /**
     * get the row represented by this position.
     *
     * @return the row.
     */
    public int getRow() {
        return r;
    }

    /**
     * get the column represented by this position.
     *
     * @return the column.
     */
    public int getColumn() {
        return c;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != Position.class) {
            return false;
        }
        Position other = (Position) o;
        return r == other.r && c == other.c;
    }

    public int hashCode() {
        // works ok for positions up to columns == 479
        return 479 * r + c;
    }

    public String toString() {
        return "[" + r + "," + c + "]";
    }
}
