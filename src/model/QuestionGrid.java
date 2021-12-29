package model;
import constant.FileName;

import java.util.Timer;
import java.util.TimerTask;

public class QuestionGrid extends Grid {
        private Question question;

    /**
     * Allocates a new {@link Grid} object.
     *
     * <p>This constructor sets the {@link Grid} in the given position of the given {@link Map}.
     *
     * @param parentMap the {@link Map} where this {@link Grid} stays
     * @param row       the row index igo the {@link Map} where this {@link Grid} stays, starting from 0
     * @param column    the column index in the {@link Map} where this {@link Grid} stays, starting from
     */
        public QuestionGrid(Map parentMap, double row, double column, Question question) {
        super(parentMap, row, column);
       QuestionLevel(question);
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void QuestionLevel(Question question) {
                if (question instanceof EasyQuestion) {
                    this.setImage(FileName.IMAGE_HARD_QUESTION);
                }if (question instanceof MediumQuestion) {
                    this.setImage(FileName.IMAGE_EASY_QUESTION);
                }if (question instanceof HardQuestion) {
                    this.setImage(FileName.IMAGE_MED_QUESTION);
                }

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
