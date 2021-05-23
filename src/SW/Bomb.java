package SW;

class Bomb // Класс нижнего слоя (бомбы)
{
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs) // конструктор
    {
        this.totalBombs = totalBombs;
        fixCount();
    }

    void start ()
    {
        bombMap =new Matrix(Box.ZERO);
        for( int j=0; j<totalBombs; j++)
            placeBomb ();

    }

    Box get (Coord coord)
    {
        return bombMap.get(coord);
    }

    int getTotalBombs()
    {
        return totalBombs;
    }

    private void placeBomb() // установка бомб в случайных местах
    {
        while (true) // вечный цикл проверки
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set (coord, Box.BOMB);
            incNumbers(coord);
            break;
        }
    }

    private  void fixCount () // проверка числа бомб, если бомб указано больше, чем может поместиться на 1/4 поля, то число бомб приравнивается этому числу
    {
        int maxBombs = Ranges.GetSize().x * Ranges.GetSize().y/4;
        if (totalBombs>maxBombs)
            totalBombs=maxBombs;
    }


    private void incNumbers (Coord coord) // увеличение значения цифр около бомб
    {
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get (around))
            bombMap.set (around, bombMap.get(around).getNextNumber());
    }


}
