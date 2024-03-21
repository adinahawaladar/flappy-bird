import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private int birdY = 250;
    private int velocity = 0;
    private int gravity = 1;
    private int gap = 200;
    private int pipeWidth = 50;
    private int pipeHeight = 400;
    private int pipeX = 600;
    private int pipeY = 0;
    private int score = 0;
    private boolean gameRunning = false;
    private boolean spacePressed = false;

    public FlappyBird() {
        Timer timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.GREEN);
        g.fillRect(pipeX, pipeY, pipeWidth, pipeHeight);
        g.fillRect(pipeX, pipeY + pipeHeight + gap, pipeWidth, 600 - pipeHeight - gap);
        g.setColor(Color.RED);
        g.fillRect(100, birdY, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Score: " + score, 200, 100);
        if (!gameRunning) {
            g.drawString("Press SPACE to Start", 150, 300);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            birdY += velocity;
            velocity += gravity;
            pipeX -= 3;
            if (pipeX < -pipeWidth) {
                pipeX = 600;
                pipeHeight = (int) (Math.random() * 300) + 100;
                score++;
            }
            if (birdY > 550 || (birdY < pipeHeight && (100 > pipeX && 100 < pipeX + pipeWidth))) {
                gameRunning = false;
            }
            if (birdY + 50 > pipeHeight + gap && (100 > pipeX && 100 < pipeX + pipeWidth)) {
                gameRunning = false;
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameRunning) {
                startGame();
            } else {
                if (!spacePressed) {
                    velocity = -10;
                }
            }
            spacePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    private void startGame() {
        gameRunning = true;
        birdY = 250;
        velocity = 0;
        score = 0;
        pipeX = 600;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
