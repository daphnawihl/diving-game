import javadraw.*;
import java.util.*;
public class OceanObject {
    protected int x;
    protected int y;
    protected int vspeed;
    protected int hspeed;
    protected int vdirection; 
    protected int hdirection; 
    protected ArrayList<Renderable> creature;


    public void move(){
        for(Renderable r: creature) {
            r.move(hdirection*hspeed, vdirection*vspeed);
        }
        if(creature.get(0).x() + creature.get(0).width() >= 800 || 
           creature.get(0).x() <= 0){
            hdirection = hdirection * -1;
        }
        if(creature.get(0).y() + creature.get(0).height() >= 600 || 
        creature.get(0).y() <= 0) {
            vdirection = vdirection * -1;
        }
    }
    public Location get_eye() {
        return creature.get(1).location();
    }
    public void kill() {
        for(Renderable r: creature) {
            r.remove();
        }
    }
}
