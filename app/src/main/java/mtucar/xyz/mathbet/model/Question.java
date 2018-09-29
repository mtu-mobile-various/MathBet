package mtucar.xyz.mathbet.model;

import java.util.Random;

public abstract class Question {

    private int firstNumber;
    private int secondNumber;
    private int answer;
    private int[] otherChoices;
    private boolean isHard;


    public abstract void calculateAnswers();

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public int[] getOtherChoices() {
        return otherChoices;
    }

    public boolean isHard() {
        return isHard;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setOtherChoices(int[] otherChoices) {
        this.otherChoices = otherChoices;
    }

    public void setHard(boolean hard) {
        isHard = hard;
    }

    public void shuffleArray()
    {
        int index, temp;
        Random random = new Random();
        for (int i = otherChoices.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = otherChoices[index];
            otherChoices[index] = otherChoices[i];
            otherChoices[i] = temp;
        }
    }

    public void swapNumbers(){
            int temp = getFirstNumber();
            setFirstNumber(getSecondNumber());
            setSecondNumber(temp);
    }
}
