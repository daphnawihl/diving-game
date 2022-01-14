import javadraw.*;
import java.util.*;
public class Diver extends OceanObject {
    private int body_x = 30;
    private int body_y = 100;
    private int mask_x_neg = 32;
    private int mask_x_pos = 32;
    private int mask_y = 92;
    private int head_y = 90;
    private int head_x_pos = 30;
    private int head_x_neg = 30;
    private int leg1_y = 150;
    private int leg1_x_pos = 35;
    private int leg1_x_neg = 35;
    private int leg2_y = 150;
    private int leg2_x_pos = 20;
    private int leg2_x_neg = 20;
    private int arm1_y = 100;
    private int arm1_x_pos = 42;
    private int arm1_x_neg = 42;
    private int arm2_y = 100;
    private int arm2_x_pos = 22;
    private int arm2_x_neg = 22;
    private Screen screen;

    public Diver (Screen screen_in, int x_in, int y_in, int hspeed_in, int vspeed_in, int hdirection_in, int vdirection_in){
        this.x = x_in;
        this.y = y_in;
        this.hspeed = hspeed_in;
        this.hdirection = hdirection_in;
        this.vspeed = vspeed_in;
        this.vdirection = vdirection_in;
        this.screen = screen_in;
        creature = new ArrayList<Renderable>();
        newDiver();
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
            newDiver();
        }
        if(creature.get(0).y() + creature.get(0).height() >= 600 || 
        creature.get(0).y() <= 0) {
            vdirection = vdirection * -1;
        }
        for(Renderable r: creature) {
            r.move(hdirection*hspeed, vdirection*vspeed);
        }
    }  
    private void newDiver() {
        Oval body, head, mask;
        Rectangle leg1, leg2, arm1, arm2;
        if (hdirection < 0) {
            creature.clear();
            // Fish is moving to the left
            body = new Oval(screen, x+body_x, y+body_y, 15, 45, Color.BLUE);
            head = new Oval(screen, x+head_x_neg, y+head_y, 15, 15, Color.BLUE);
            mask = new Oval(screen, x+mask_x_neg, y+mask_y, 10, 5, Color.WHITE) ;
            leg1 = new Rectangle (screen, x+leg1_x_neg, y+leg1_y, 20, 6, Color.BLUE);
            leg2 = new Rectangle (screen, x+leg2_x_neg, y+leg2_y, 20, 6, Color.BLUE);
            arm1 = new Rectangle (screen, x+arm1_x_neg, y+arm1_y, 12, 6, Color.BLUE);
            arm2 = new Rectangle (screen, x+arm2_x_neg, y+arm2_y, 12, 6, Color.BLUE);
            leg1.rotate(60);
            leg2.rotate(-60);
            arm1.rotate(-30);
            arm2.rotate(30);
            mask.rotate(-180);
        }
        else {
            // Fish is moving to the right
            body = new Oval(screen, x+body_x, y+body_y, 15, 45, Color.BLUE);
            head = new Oval(screen, x+head_x_pos, y+head_y, 15, 15, Color.BLUE);
            mask = new Oval(screen, x+mask_x_pos, y+mask_y, 10, 5, Color.WHITE) ;
            leg1 = new Rectangle (screen, x+leg1_x_pos, y+leg1_y, 20, 6, Color.BLUE);
            leg2 = new Rectangle (screen, x+leg2_x_pos, y+leg2_y, 20, 6, Color.BLUE);
            arm1 = new Rectangle (screen, x+arm1_x_pos, y+arm1_y, 12, 6, Color.BLUE);
            arm2 = new Rectangle (screen, x+arm2_x_pos, y+arm1_y, 12, 6, Color.BLUE);
            leg1.rotate(60);
            leg2.rotate(-60);
            arm1.rotate(-30);
            arm2.rotate(30);
            mask.rotate(-180);
        }
        //eye.center(body.center().x(), body.center().y() + 20);
        //tail.center(body.center().x(), body.center().y() );
        creature.add(body);
        creature.add(head);
        creature.add(mask);
        creature.add(leg1);
        creature.add(leg2);
        creature.add(arm1);
        creature.add(arm2);
    }
    public void move_x(int hdirection_in){
        this.hdirection = hdirection_in;
    }
    public void move_y(int vdirection_in){
        this.vdirection = vdirection_in;

    }
    public Boolean check_eaten(OceanObject other_creature){
        Location creature_eye = other_creature.get_eye();
        if (creature.get(0).location().distance(creature_eye) < 20.){
            return true;
        }
        else {
            return false;
        }
    }
}

