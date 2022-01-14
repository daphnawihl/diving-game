import javadraw.*;
import java.util.*;
public class Shark extends OceanObject {
    private int body_x = 30;
    private int body_y = 100;
    private int eye_x_neg = 32;
    private int eye_x_pos = 62;
    private int eye_y = 103;
    private int tail_y = 105;
    private int tail_x_pos = 20;
    private int tail_x_neg = 70;
    private int fin_y = 90;
    private int fin_x_pos = 40;
    private int fin_x_neg = 50;
    private Screen screen;

    public Shark(Screen screen_in, int x_in, int y_in, int hspeed_in, int vspeed_in, int hdirection_in, int vdirection_in){
        this.x = x_in;
        this.y = y_in;
        this.hspeed = hspeed_in;
        this.hdirection = hdirection_in;
        this.vspeed = vspeed_in;
        this.vdirection = vdirection_in;
        this.screen = screen_in;
        creature = new ArrayList<Renderable>();
        newShark();
    }   
    public void move(){
        if(creature.get(0).x() + creature.get(0).width() >= 800 || 
           creature.get(0).x() <= 0) {
            hdirection = hdirection * -1;
            this.x = (int) creature.get(0).x() - (int) creature.get(0).width() - body_x;
            this.y = (int) creature.get(0).y() - body_y;
            for(Renderable r: creature) {
                r.remove();
            }
            creature.clear();
            creature = new ArrayList<Renderable>();
            newShark();
        }
        if(creature.get(0).y() + creature.get(0).height() >= 600 || 
        creature.get(0).y() <= 0) {
            vdirection = vdirection * -1;
        }
        for(Renderable r: creature) {
            r.move(hdirection*hspeed, vdirection*vspeed);
        }
    }  
    private void newShark() {
        Oval body, eye;
        Triangle tail, fin;
        if (hdirection < 0) {
            creature.clear();
            // Shark is moving to the left
            body = new Oval(screen, x+body_x, y+body_y, 50, 25, Color.lightGray);
            eye = new Oval(screen, x+eye_x_neg, y+eye_y, 5, 5, Color.black) ;
            tail = new Triangle(screen, x+tail_x_neg, y+tail_y, 20, 10, Color.lightGray);
            fin = new Triangle (screen, x+fin_x_neg, y+fin_y, 10, 10, Color.GRAY);
            tail.rotate(-90);
            
        }
        else {
            // Shark is moving to the right
            body = new Oval(screen, x+body_x, y+body_y, 40, 20, Color.lightGray);
            eye = new Oval(screen, x+eye_x_pos, y+eye_y, 5, 5, Color.black) ;
            tail = new Triangle(screen, x+tail_x_pos, y+tail_y, 20, 10, Color.lightGray);
            fin = new Triangle (screen, x+fin_x_pos, y+fin_y, 10, 10, Color.GRAY);
            tail.rotate(90);
            
        }
        //eye.center(body.center().x(), body.center().y() + 20);
        //tail.center(body.center().x(), body.center().y() );
        creature.add(eye);
        creature.add(body);
        creature.add(tail);
        creature.add(fin);
    }

 
}

