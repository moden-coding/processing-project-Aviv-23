import processing.core.*;

public class App extends PApplet {

    float rectY1 = 200;
    float rectY2 = 200;
    int paddlespeed = 10;
    int ballX = 300;
    int ballY = 200;
    int xspeed = 5;
    int yspeed = 2;
    int paddle1X = 20;
    int paddle2X = 560;
    int X = 600;
    int Y = 400;
    int score1 = 0;
    int score2 = 0;
    int scene = 1;
    boolean UP1, UP2, DOWN1, DOWN2;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        UP1 = false;
        UP2 = false;
        DOWN1 = false;
        DOWN2 = false;

    }

    public void settings() {
        size(X, Y);
    }

    public void draw() {
        if (scene == 1) {
            background(255);
            fill(0);
            textSize(25);
            textAlign(CENTER);
            text("Left Player Use w&s keys to move paddle", 300, 200);
            text("Right Player Use UP&DOWN keys to move paddle", 300, 300);

        } else if (scene == 2) {
            background(255);
            fill(0, 150, 255);
            noStroke();
            rect(paddle1X, rectY1, 20, 100);
            rect(paddle2X, rectY2, 20, 100);
            fill(0);
            circle(ballX, ballY, 25);
            fill(0);
            textSize(32);
            textAlign(CENTER);
            text(score1, 50, 50);
            text(score2, 550, 50);
            ballX += xspeed;
            ballY += yspeed;
            if (UP2 == true) {
                rectY2 -= paddlespeed;
            }
            if (UP1 == true) {
                rectY1 -= paddlespeed;
            }
            if (DOWN2 == true) {
                rectY2 += paddlespeed;
            }
            if (DOWN1 == true) {
                rectY1 += paddlespeed;
            }
            int currentColor = get((int) ballX, (int) (ballY));
            currentColor = get((int) (ballX - 25 / 2), (int) ballY);
            if (red(currentColor) == 0 && green(currentColor) == 150 && blue(currentColor) == 255) {
                xspeed = -xspeed;
            }
            currentColor = get((int) (ballX + 25 / 2), (int) ballY);
            if (red(currentColor) == 0 && green(currentColor) == 150 && blue(currentColor) == 255) {
                xspeed = -xspeed;
            }

            if (ballY >= Y - 12.5 || ballY <= 12.5) {
                yspeed = -yspeed;
            }
        }
        if (ballX < 0) {
            score2++;
            ballX = 300;
            ballY = 200;
        }
        if (ballX > 600) {
            score1++;
            ballX = 300;
            ballY = 200;
        }
        if (score1 == 11 || score2 == 11) {
            scene++;

        }
        if (scene == 3 && score1 > score2) {
            score1 = 0;
            score2 = 0;
            background(255);
            textSize(32);
            textAlign(CENTER);
            text("Left Player Victory", 300, 100);
            text("Press r to play again", 300, 300);
        }
        if (scene == 3 && score1 < score2) {
            score1 = 0;
            score2 = 0;
            background(255);
            textSize(32);
            textAlign(CENTER);
            text("Right Player Victory", 300, 100);
            text("Press r to play again", 300, 300);
        }

    }

    public void keyPressed() {

        if (keyCode == UP && rectY2 > 0) {
            UP2 = true;
        } else if (keyCode == DOWN && rectY2 < 300) {
            DOWN2 = true;
        }

        if (key == 'w' && rectY1 > 0) {
            UP1 = true;
        } else if (key == 's' && rectY2 < 300) {
            DOWN1 = true;
        }
        if (key == ' ' && scene == 1) {
            scene++;
        }
        if (key == 'r' && scene >= 2) {
            scene = 1;
        }
    }
    public void keyReleased(){
           if (keyCode == UP && rectY2 > 0) {
            UP2 = false;
        } else if (keyCode == DOWN && rectY2 < 300) {
            DOWN2 = false;
        }

        if (key == 'w' && rectY1 > 0) {
            UP1 = false;
        } else if (key == 's' && rectY2 < 300) {
            DOWN1 = false;
        }
    }
}
