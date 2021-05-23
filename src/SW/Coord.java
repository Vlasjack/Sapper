package SW;

import java.util.Objects;

public class Coord // класс координат
{

    public int x;
    public int y;

    public Coord(int x, int y) // конструктор
    {
        this.x =x;
        this.y =y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Coord)
        {
            Coord to =(Coord)obj;
            return to.x == x && to.y == y;
        }
        return super.equals(obj);
    }

}
