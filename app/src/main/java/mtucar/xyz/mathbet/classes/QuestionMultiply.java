package mtucar.xyz.mathbet.classes;

import java.util.Random;

public class QuestionMultiply extends Question {
    private QuestionType qType;
    private QuestionStatus qStatus;

    public QuestionMultiply(boolean isHard){
        qType = QuestionType.MULTIPLY;
        qStatus = QuestionStatus.ONGOING;
        this.setHard(isHard);
        Random random = new Random();
        if(isHard){
            setFirstNumber(random.nextInt(100)+10);
            setSecondNumber(random.nextInt(10)+1);
        }else{
            setFirstNumber(random.nextInt(10)+1);
            setSecondNumber(random.nextInt(10)+1);
        }
        calculateAnswers();
    }

    @Override
    public void calculateAnswers() {
        setAnswer(getFirstNumber()* getSecondNumber());
        Random random = new Random();
        int[] temp = new int[6];
        temp[0] = getAnswer() + random.nextInt(5)+1;
        temp[1] = temp[0] + random.nextInt(4)+1;
        temp[2] = getAnswer() - (random.nextInt(5)+1);
        temp[3] = temp[2] - (random.nextInt(4)+1);
        temp[4] = getAnswer() + (random.nextInt(4)+1)*10;
        temp[5] = getAnswer();
        setOtherChoices(temp);
        shuffleArray();
    }
}
