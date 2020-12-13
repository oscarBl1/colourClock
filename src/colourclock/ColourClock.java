package colourclock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class ColourClock {

    static int hour, minute, second;
    static JFrame frame;
    static double coloursPerSecond;

    public static void time() {
        colourTime();
        JLabel label = new JLabel(" ", JLabel.CENTER);

        create();
        Timer timer;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("tick");
                String time = dtf.format(now);
                hour = Integer.parseInt(time.substring(0, 2));
                minute = Integer.parseInt(time.substring(3, 5));
                second = Integer.parseInt(time.substring(6, 8));
                double timeInSeconds = (hour * 60 * 60) + (minute * 60) + second;
                double colours = timeInSeconds / coloursPerSecond;
                System.out.println("time: " + timeInSeconds);
                System.out.println("colours: " + colours);
                String rgb = Integer.toHexString((int) colours);
                int red = Integer.parseInt(rgb.substring(0, 2), 16);
                int green = Integer.parseInt(rgb.substring(2, 4), 16);
                int blue = Integer.parseInt(rgb.substring(4, 6), 16);
                System.out.println("rgb: " + rgb);
                System.out.println("red: " + red);
                System.out.println("green: " + green);
                System.out.println("blue: " + blue);
                frame.getContentPane().setBackground(new Color(red, green, blue));
                System.out.println(hour + " " + minute + " " + second);
                label.setText(time);
                label.setSize(400, 400);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Serif", Font.BOLD, 40));

            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
        frame.add(label);

    }

    public static void create() {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void colourTime() {
        double secondsInDay = 24 * 60 * 60;

        double colours = 255 * 3;

        coloursPerSecond = colours / secondsInDay;

    }

    public static void main(String[] args) {
        time();

    }

}
