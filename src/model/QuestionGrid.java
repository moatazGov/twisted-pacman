package model;
import constant.FileName;

public class QuestionGrid extends Grid {
        public static int i=0 ;
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
       QuestionLevel(question);
    }
        public void QuestionLevel(Question question) {
                if (question.getLevel() == Level.HARD) {
                    this.setImage(FileName.IMAGE_HARD_QUESTION);
                }if (question.getLevel() == Level.EASY) {
                    this.setImage(FileName.IMAGE_EASY_QUESTION);
                }if (question.getLevel() == Level.MEDIUM) {
                    this.setImage(FileName.IMAGE_MED_QUESTION);
                }
//                else {
//                    this.setImage(FileName.IMAGE_EASY_QUESTION);
//                    i++;
//                }

            }


    public void eat() {
        setVisible(false);
    } //TODO

    /**
     * Tests if this {@link } still exists (i.e. not eaten yet) in the screen.
     *
     * @return {@code true} if this {@link } still exists; {@code false} otherwise.
     */
    public boolean isExisting() {
        return isVisible();
    } //TODO
//    private boolean isQuestionGrid(String grid) {
   //         return grid.equals("?");
    //}

}
