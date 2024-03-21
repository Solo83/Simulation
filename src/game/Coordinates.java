package game;
import java.util.Objects;

public class Coordinates {
    private final Integer row;
    private final Integer column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }
    public Integer getColumn() {
        return column;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(this.row + shift.rowShift, this.column + shift.columnShift);
    }

    public boolean canShift(CoordinatesShift shift,WorldMap worldMap) {
        int row = getRow() + shift.rowShift;
        int column = getColumn() + shift.columnShift;

        if ((row < 0) || (row > worldMap.getMapRow()-1)) return false;
        if ((column < 0) || (column > worldMap.getMapColumn()-1)) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "{" +
                 row + column +
                '}';
    }
}
