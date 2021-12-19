package controller;

import model.Level;
import model.Map;
import model.Question;
import model.QuestionGrid;

public class FactoryQuestionGrid {
    public QuestionGrid getQuestionGrid(Map parentMap, double row, double column, Question question) {
        if (question.getLevel() == null) {
            return null;
        }
        if (question.getLevel()== Level.EASY) {
            return new QuestionGrid(parentMap,row,column,question);
        } else if (question.getLevel()== Level.MEDIUM) {
            return new QuestionGrid(parentMap,row,column,question);
        } else if (question.getLevel()== Level.HARD) {
            return new QuestionGrid(parentMap,row,column,question);
        }
        return null;
    }
}
