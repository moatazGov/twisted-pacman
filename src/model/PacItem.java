package model;

import constant.FileName;
 import java.util.concurrent.ThreadLocalRandom;
import model.Map;
import model.QuestionGrid;
import java.util.Timer;
import java.util.TimerTask;


 /**
 *
 *
 * <h1>Cookie</h1>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class PacItem extends Grid {

    /** The value of {@link Score} of this cookie. */
    private int value;

    /**
     * a timer to make the cookies reappear after 30 seconds
     */
    Timer timer = new Timer();

    /**
     */
    public PacItem(Map map, double row, double column, int value) { // TODO
        super(map, row, column);
        this.setImage(FileName.IMAGE_COOKIE_BIG);
        this.value = 1;
//        double rowNumber = ThreadLocalRandom.current().nextDouble(0, row);
//        double columnNumber = ThreadLocalRandom.current().nextDouble(0, column);
//        randChange(rowNumber,columnNumber,this);
    }


//        public void randChange(double Row,double Column,PacItem pacItem ){ //TODO
//
//        pacItem.setVisible(false);
//
//       // QuestionGrid questiuonGrid= new QuestionGrid(double row, double column, Question question);
//
//        }
    /**
     */
    public int getValue() {
        return value;
    }

    /**
     * Makes this {@link } eaten and disappear in the screen.
     *
     * <p>The principle of this method is just make it invisible.
     */
    public void eat() {
        setVisible(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisible(true);
            }
        }, 30*1000);
    }

    /**
     * Tests if this {@link } still exists (i.e. not eaten yet) in the screen.
     *
     * @return {@code true} if this {@link } still exists; {@code false} otherwise.
     */
    public boolean isExisting() {
        return isVisible();
    }
}
