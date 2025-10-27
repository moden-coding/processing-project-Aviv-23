import processing.core.*;

public class App extends PApplet {

    float leftY = 200;
    float rightY = 200;
    int paddlespeed = 4;
    int ballX = 300;
    int ballY = 200;
    double xspeed = 5;
    int yspeed = 2;
    int paddle1X = 20;
    int paddle2X = 560;
    int X = 600;
    int Y = 400;
    int score1 = 0;
    int score2 = 0;
    int scene = 1;
    boolean UPleft, UPright, DOWNleft, DOWNright;
    float R=0;
    float G=0;
    float B=0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        UPleft = false;
        UPright = false;
        DOWNleft = false;
        DOWNright = false;

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
            rect(paddle1X, leftY, 20, 100);
            rect(paddle2X, rightY, 20, 100);
            fill(R,G,B);
            circle(ballX, ballY, 25);
            fill(0);
            textSize(32);
            textAlign(CENTER);
            text(score1, 50, 50);
            text(score2, 550, 50);
            ballX += xspeed;
            ballY += yspeed;
            if (UPright == true&& rightY>= 0) {
                rightY -= paddlespeed;
            }
            if (UPleft == true&& leftY >= 0) {
                leftY -= paddlespeed;
            }
            if (DOWNright == true&& rightY <= 300) {
                rightY += paddlespeed;
            }
            if (DOWNleft == true&& leftY <= 300) {
                leftY += paddlespeed;
            }
            int currentColor = get((int) ballX, (int) (ballY));
            currentColor = get((int) (ballX - 25 / 2), (int) ballY);
            if (red(currentColor) == 0 && green(currentColor) == 150 && blue(currentColor) == 255) {
                xspeed = -1.02*xspeed;
                R=random(0,255);
                G=random(0,255);
                B=random(0,255);
            }
            currentColor = get((int) (ballX + 25 / 2), (int) ballY);
            if (red(currentColor) == 0 && green(currentColor) == 150 && blue(currentColor) == 255) {
                xspeed = -1.02*xspeed;
                R=random(0,255);
                G=random(0,255);
                B=random(0,255);
            }

            if (ballY >= Y - 12.5 || ballY <= 12.5) {
                yspeed = -yspeed;
            }
        }
        if (ballX < 0) {
            score2++;
            ballX = 300;
            ballY = 200;
            xspeed = 5;
        }
        if (ballX > 600) {
            score1++;
            ballX = 300;
            ballY = 200;
            xspeed = 5;
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
            text("Press 'r' to play again", 300, 300);
        }
        if (scene == 3 && score1 < score2) {
            score1 = 0;
            score2 = 0;
            background(255);
            textSize(32);
            textAlign(CENTER);
            text("Right Player Victory", 300, 100);
            text("Press 'r' to play again", 300, 300);
        }

    }

    public void keyPressed() {

        if (keyCode == UP) {
            UPright = true;
           
        } else if (keyCode == DOWN) {
            DOWNright = true;
        }

        if (key == 'w') {
            UPleft = true;
        } else if (key == 's') {
            DOWNleft = true;
        }
        if (key == ' ' && scene == 1) {
            scene++;
        }
        if (key == 'r' && scene >= 2) {
            scene = 1;
        }
    }
    public void keyReleased(){
           if (keyCode == UP ) {
            UPright = false;
        } else if (keyCode == DOWN ) {
            DOWNright = false;
        }

        if (key == 'w' ) {
            UPleft = false;
        } else if (key == 's') {
            DOWNleft = false;
        }
    }
}
