package model;
import java.util.*;
import constant.FileName;
import  java.util.*;
public class QuestionGrid extends Grid {

        private Question question;
    /**
     * Allocates a new {@link Grid} object.
     *
     * <p>This constructor sets the {@link Grid} in the given position of the given {@link Map}.
     *
     * @param parentMap the {@link Map} where this {@link Grid} stays
     * @param row       the row index in the {@link Map} where this {@link Grid} stays, starting from 0
     * @param column    the column index in the {@link Map} where this {@link Grid} stays, starting from
     */
    public QuestionGrid(Map parentMap, double row, double column, Question question) {
        super(parentMap, row, column);
        this.setImage(FileName.IMAGE_QUESTION);


    }

//    private boolean isQuestionGrid(String grid) {
//            return grid.equals("?");
//    }

}
