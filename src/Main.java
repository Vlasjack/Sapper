import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;


import SW.Box;
import SW.Coord;
import SW.Game;
import SW.Ranges;
import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

public class Main extends JFrame
{

    private Game game;
    private JPanel panel;
    private JLabel label;

    private int STOLB ;
    private int STROCH ;
    private int BOMBS ;
    private final int ImgSize = 50;


    public static void main(String[] args) // Экземпляр программы
    {
        new Main().setVisible(true);
    }

    private Main()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число столбцов");
        STOLB = scanner.nextInt();

        System.out.println("Введите число строчек");
        STROCH = scanner.nextInt();

        System.out.println("Введите число бомб");
        BOMBS = scanner.nextInt();

        game = new Game(STOLB, STROCH, BOMBS);
        game.start();
        setImg();
        initLabel();
        initPanel();
        initFrame();
    }


    private void initLabel()
    {
        label = new JLabel("Начинай игру", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
    }

    private void initFrame()// визуальзация окна
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Сапёр");
        setResizable(false);
        setVisible(true);
        pack();
        setIconImage(getImg("icon"));
        setLocationRelativeTo(null);
    }

    private void initPanel() // Панель
    {

        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) // функция для отрисовки
            {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                {

                    g.drawImage((Image) game.getBox(coord).img,
                            coord.x * ImgSize, coord.y * ImgSize, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) //мышечный адаптер
            {
                int x = e.getX() / ImgSize;
                int y = e.getY() / ImgSize;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();

                label.setText(getMessege());

                panel.repaint();

            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.GetSize().x * ImgSize,
                Ranges.GetSize().y * ImgSize));


        add(panel);
    }

    private String getMessege()
    {
        switch (game.getState())
        {
            case BOMBED:
                return "Поражение";
            case WINNER:
                return "Победа";
            case PLAYED:
                return "Игра уже идёт";
            default: return  "";
        }
    }

    private void setImg () // функция установки изображений
    {
        for (Box box : Box.values())
        box.img = getImg(box.name().toLowerCase());
    }

    private Image getImg (String name) //функция  получения изображений
    {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); // создание объекта
        return icon.getImage();

    }
}

