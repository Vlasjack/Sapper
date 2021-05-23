package SW;

public enum Box // Класс хранения изображений
{
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object img;

    Box getNextNumber () // изменение изображения
    {
        return  Box.values()[this.ordinal()+1];
    }

    int getNumber()
    {
        return  this.ordinal();
    }


}
