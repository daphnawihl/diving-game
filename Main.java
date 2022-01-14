
import javadraw.*;
import java.util.*;

public class Main extends Window {
    ArrayList<Fish> fishes;
    Shark shark;
    Rectangle background;
    Rectangle lowbackground; 
    Triangle plant1; 
    Triangle plant2;
    Triangle plant3; 
    Triangle plant4; 
    Diver diver;
    Location mouse;
    Text end;
    Boolean eaten = false;
    int score = 0;
    public static void main(String[] args) {
        Window.open(); 
           
    }

    public void start(){
        background = new Rectangle (screen, 0, 0, 800, 600, Color.CYAN);
        lowbackground = new Rectangle (screen, 0, 525, 800, 600, Color.yellow);
        plant1 = new Triangle (screen, 20, 450, 15, 100, Color.GREEN);
        plant2 = new Triangle (screen, 33, 450, 30, 100, Color.green);
        plant3 = new Triangle (screen, 700, 475, 22, 75, Color.green);
        plant4 = new Triangle (screen, 713, 475, 33, 100, Color.green);
        fishes = new ArrayList<>();
        fishes.add(new Fish(screen, 30, 100, 3, 1, 1, -1));
        fishes.add(new Fish(screen, 500, 400, 2, 2, 1, -1));
        fishes.add(new Fish(screen, 200, 50, 4, 1, 1, -1));
        fishes.add(new Fish(screen, 300, 300, 3, 1, 1, -1));
        fishes.add(new Fish(screen, 500, 200, 4, 1, 1, -1));
        shark = new Shark(screen, 500, 300, 3, 1 , 1, -1);
        mouse = new Location (300., 300.);
        diver = new Diver (screen, 300, 300, 3, 3, 1, -1);

        while (! eaten && fishes.size() > 0) {
            for(Fish f: fishes) {
                f.move();
                if (diver.check_eaten(f)) {
                    f.kill();
                    fishes.remove(f);
                    System.out.println("test");
                    score += 1;
                    break;
                }
                
            }
            shark.move();
            diver.move();
            eaten = diver.check_eaten(shark);
            screen.update();
            screen.sleep(1/30.0);


        }
        if (eaten){
            end = new Text (screen, "You died! Score = " + String.valueOf(score), 350, 300);
        }
        else {
            end = new Text (screen, "You won! Score = " + String.valueOf(score), 350, 300);
        }
     }
    public void mouseMove(Location loc){
        if (loc.x() < mouse.x()) {
            diver.move_x(-1);
        }
        else {
            diver.move_x(1);
        }
        if (loc.y() < mouse.y()) {
            diver.move_y(-1);
        }
        else {
            diver.move_y(1);
        }
       
    }
}
