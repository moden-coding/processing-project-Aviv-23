import processing.core.*;

public class App extends PApplet {

    // Rectangle position
    float rectY1 = 200;
    float rectY2 = 200;
    int paddlespeed = 15;
    int ballX = 300;
    int ballY = 200;
    int xspeed = 3;
    int yspeed = 1;
    int paddle1X = 20;
    int paddle2X = 560;
    int X = 600;
    int Y = 400;
    int score1 = 0;
    int score2 = 0;
    int scene=1;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {

    }

    public void settings() {
        size(X, Y);
    }

    public void draw() {
        if(scene==1){
             fill(0);
        textSize(32);
        textAlign(CENTER);
        text("Left Player Use w&s keys to move paddle", 300,200);
         text("Right Player Use UP&DOWN keys to move paddle", 300,300);
       background(255);
        }else if(scene==2){
        background(255); 
        fill(0, 150, 255);
        noStroke();
        rect(paddle1X, rectY1, 20, 100); 
        rect(paddle2X, rectY2, 20, 100);
        fill(0);
        circle(ballX, ballY, 25);

        ballX += xspeed;
        ballY += yspeed;

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
        if (ballX > 600) {
            score1++;
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
        fill(0);
        textSize(32);
        textAlign(CENTER);
        text(score1, 50, 50);
        text(score2, 550, 50);
        if(score1==11||score2==11){
            scene++;
        }
        if(scene==3&&score1>score2){
        background(255);
        

        }
    }
    }
    public void keyPressed() {

        if (keyCode == UP && rectY2 > 0) {
            rectY2 -= paddlespeed;
        } else if (keyCode == DOWN && rectY2 < 300) {
            rectY2 += paddlespeed;
        }

        if (key == 'w' && rectY1 > 0) {
            rectY1 -= paddlespeed;
        } else if (key == 's' && rectY2 < 300) {
            rectY1 += paddlespeed;
        }
        if(key==' '&&scene==1){
scene++;}
 if(key=='r'&&scene>=2){
scene=1;}
    }
}