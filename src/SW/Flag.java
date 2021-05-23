package SW;

class Flag //Класс верхнего слоя( открытая клетка/закрытая клетка/флаг )
{
    private Matrix flagMap;
    private int countOfClossedBoxes;
    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClossedBoxes = Ranges.GetSize().x * Ranges.GetSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void toogleFlagedToBox (Coord coord) // поставить флаг/убрать флаг
    {
        switch (flagMap.get(coord))
        {
            case FLAGED: setClossedToBox (coord); break;
            case CLOSED: setFlagedToBox (coord); break;
        }
    }

    void setOpenedToBox(Coord coord)
    {
        flagMap.set(coord, Box.OPENED);
        countOfClossedBoxes --;
    }

    void setClossedToBox(Coord coord)
    {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedToBox(Coord coord)
    {
        flagMap.set(coord, Box.FLAGED);
    }

    int getCountOfClosedBoxes() //
    {
        return  countOfClossedBoxes;
    }

     public void setBombedtoBox (Coord coord)
    {
         flagMap.set(coord, Box.BOMBED);

    }

    public void setOpenedToClosed(Coord coord) // открывает закрытые ячейки вокруг числа
    {
        if (flagMap.get (coord) == Box.CLOSED)
            flagMap.set (coord, Box.OPENED);
    }

    public void setNoBombToSafeBox(Coord coord)
    {
        if (flagMap.get (coord) == Box.FLAGED)
            flagMap.set (coord, Box.NOBOMB);
    }

     int getCountOfFlageBoxesAround(Coord coord) {
        int count =0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count ++ ;
            return count;
    }
}
