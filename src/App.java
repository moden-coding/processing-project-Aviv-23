import processing.core.*;

public class App extends PApplet {

    float leftY = 200;//initial Y of paddles
    float rightY = 200;
    int paddlespeed = 4;//speed of paddles
    int ballX = 300;//ball initial position
    int ballY = 200;
    double xspeed = 5;//left right speed of ball
    int yspeed = 2;//up down speed of the ball
    int paddle1X = 20;//X of the paddles
    int paddle2X = 560;
    int X = 600;//screen X
    int Y = 400;// screen Y
    int score1 = 0;//score for detrmining winner
    int score2 = 0;
    int scene = 1; //scene to change scene
    boolean UPleft, UPright, DOWNleft, DOWNright; //smooth movement
    float R=0;
    float G=0;
    float B=0;//ball color fpr changing everytime it hits the paddle
    float r=0;
    float g=100;
    float b=200; //paddle color for detecting paddles

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        UPleft = false;//smooth movement bolean starts false
        UPright = false;
        DOWNleft = false;
        DOWNright = false;

    }

    public void settings() {
        size(X, Y);//background
    }

    public void draw() {
        if (scene == 1) {//beofre game begins expalining how to play
            background(255);//white backgorund
            fill(0);
            textSize(25);
            textAlign(CENTER);
            text("Left Player Use w&s keys to move paddle", 300, 100);
            text("Right Player Use UP&DOWN keys to move paddle", 300, 200);

        } else if (scene == 2) {
            background(255);
            fill(r, g, b);
            noStroke();
            rect(paddle1X, leftY, 20, 100);
            rect(paddle2X, rightY, 20, 100); //drawing left and right paddles
            fill(R,G,B);//ball color in orded to change it
            circle(ballX, ballY, 25);
            fill(0);
            textSize(32);
            textAlign(CENTER);
            text(score1, 50, 50);
            text(score2, 550, 50);//tracking score
            ballX += xspeed;//ball movement left and right
            ballY += yspeed;//ball movement up down
            if (UPright == true&& rightY>= 0) {//smooth movement with contstraint
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
            if (red(currentColor) == r && green(currentColor) == g && blue(currentColor) == b) { //sensing paddle based off color
                xspeed = -1.05*xspeed;//bouncing left and right and speeding up by 5%
                R=random(0,255);//chsnging ball color
                G=random(0,255);
                B=random(0,255);
            }
            currentColor = get((int) (ballX + 25 / 2), (int) ballY);
            if (red(currentColor) == r && green(currentColor) == g && blue(currentColor) == b) {
                xspeed = -1.05*xspeed;
                R=random(0,255);
                G=random(0,255);
                B=random(0,255);
            }

            if (ballY >= Y - 12.5 || ballY <= 12.5) {
                yspeed = -yspeed;//bouncing off top walls
            }
        }
        if (ballX < 0) {
            score2++;//score increase
            ballX = 300;//reset ball
            ballY = 200;
            xspeed = 5;//reset X speed 
        }
        if (ballX > 600) {
            score1++;
            ballX = 300;
            ballY = 200;
            xspeed = 5;
        }
        if (score1 == 11 || score2 == 11) {
            scene++;//when a player reaches 11 changes scene

        }
        if (scene == 3 && score1 > score2) {//declaring winner based off score
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

        if (keyCode == UP) {//smooth movement bolean true when key is pressed
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
           if (keyCode == UP ) {//smooth movement bolean is false when key released
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
