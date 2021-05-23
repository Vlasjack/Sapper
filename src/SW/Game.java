package SW;

public class Game //Класс ,в котором проходит обращение к методам, инициализация, работа с мышью.
{
    private Bomb bomb;
    private Flag flag;
    private State state;

    public Game(int stolb, int stroch,int bombs)
    {

        Ranges.SetSize(new Coord( stolb, stroch));
        bomb =new Bomb(bombs);
        flag = new Flag();
    }

    public void start ()
    {
        bomb.start();
        flag.start();
        state = State.PLAYED;
    }

    private boolean gameOver()
    {
        if (state == State.PLAYED)
            return false;
        start();
        return true;
    }

    public Box getBox (Coord coord)
    {
        if (flag.get(coord) == Box.OPENED)
            return  bomb.get(coord);
        else
            return flag.get(coord);
    }

    public State getState()
    {
        return state;
    }
    private void checkWinner () // функция проверки победы
    {
        if (state == State.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = State.WINNER;

    }

     private void openBox(Coord coord)
     {
         switch (flag.get(coord))
         {
             case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
             case FLAGED: return;
             case CLOSED:
                 switch (bomb.get(coord))
                 {
                     case ZERO: openBoxesAround(coord); return;
                     case BOMB: openBombs(coord); return;
                     default: flag.setOpenedToBox(coord); return;
                 }

         }

     }
     private void setOpenedToClosedBoxesAroundNumber(Coord coord) // открывет клетки вокруг числа, если флажки вокруг были раставлены
        {
            if (bomb.get (coord) != Box.BOMB)
                if (flag.getCountOfFlageBoxesAround(coord) == bomb.get(coord).getNumber())
                    for (Coord around : Ranges.getCoordsAround(coord))
                        if (flag.get (around) ==Box.CLOSED)
                            openBox(around);


        }

    private void openBombs(Coord bombed)
    {
        state=State.BOMBED;
        flag.setBombedtoBox(bombed);
        for (Coord coord : Ranges.getAllCoords())
            if (bomb.get(coord) == Box.BOMB)
                flag.setOpenedToClosed(coord);
            else
                flag.setNoBombToSafeBox(coord);

    }
    private void openBoxesAround(Coord coord)
    {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
    }

    public void pressRightButton(Coord coord)
    {
        if (gameOver ()) return;
        flag.toogleFlagedToBox (coord);
    }

    public void pressLeftButton(Coord coord)
    {

        if (gameOver ()) return;
        openBox(coord);
        checkWinner ();
    }
}
