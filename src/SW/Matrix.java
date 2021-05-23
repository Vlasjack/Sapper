package SW;

 class Matrix //  Класс хранения объектов всего поля
 {
    private Box [] [] matrix;

    Matrix(Box def) // конструктор матрицы нужного размера , заполненный указанными элементами
    {
        matrix =new Box[Ranges.GetSize().x][Ranges.GetSize().y];
        for (Coord coord : Ranges.getAllCoords())
            matrix [coord.x] [coord.y] = def;
    }

    Box get (Coord coord)
    {
        if(Ranges.inRange (coord))
            return matrix[coord.x] [coord.y];
        return null;

    }

     void set (Coord coord, Box box)
     {  if(Ranges.inRange (coord))
            matrix [coord.x] [coord.y] = box;
     }
 }
