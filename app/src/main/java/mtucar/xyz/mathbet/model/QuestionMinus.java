package mtucar.xyz.mathbet.model;

import java.util.Random;

public class QuestionMinus extends Question {


    public QuestionMinus(boolean isHard){
        this.setHard(isHard);
        Random random = new Random();
        if(isHard){
            setFirstNumber(random.nextInt(100)+10);
            setSecondNumber(random.nextInt(100)+10);
        }else{
            setFirstNumber(random.nextInt(10)+1);
            setSecondNumber(random.nextInt(10)+1);
            if(getFirstNumber()< getSecondNumber())
                swapNumbers();
        }
        calculateAnswers();
    }

    @Override
    public void calculateAnswers() {
        setAnswer(getFirstNumber()-getSecondNumber());
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
