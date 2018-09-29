package mtucar.xyz.mathbet.model;

import java.util.Random;

public class QuestionDivide extends Question {

    public QuestionDivide(boolean isHard){
        this.setHard(isHard);
        Random random = new Random();
        if(isHard){
            setAnswer(random.nextInt(100)+10);
            setSecondNumber(random.nextInt(10)+1);
            setFirstNumber(getAnswer()*getSecondNumber());
        }else{
            setAnswer(random.nextInt(10)+1);
            setSecondNumber(random.nextInt(10)+1);
            setFirstNumber(getAnswer()*getSecondNumber());
        }
        calculateAnswers();
    }

    @Override
    public void calculateAnswers() {
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
