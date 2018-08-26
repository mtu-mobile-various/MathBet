package mtucar.xyz.mathbet.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class QuestionSet implements Parcelable {
    private int numberOfQuestions;
    private double percentage;
    private boolean isHard;
    private int time;

    public QuestionSet(){
        Random random = new Random();
        numberOfQuestions = random.nextInt(16)+5;
        int singleTime = random.nextInt(7)+3;
        time = singleTime*numberOfQuestions;
        isHard = random.nextBoolean();
        setPercentage();
    }

    private void setPercentage(){
        double factor;
        if(isHard){
            factor = 0.35;
        }else{
            factor = 0.05;
        }
        percentage = (numberOfQuestions*factor)/time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isHard() {
        return isHard;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "QuestionSet{" +
                "numberOfQuestions=" + numberOfQuestions +
                ", percentage=" + percentage +
                ", isHard=" + isHard +
                ", time=" + time +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.numberOfQuestions);
        dest.writeDouble(this.percentage);
        dest.writeByte(this.isHard ? (byte) 1 : (byte) 0);
        dest.writeInt(this.time);
    }

    protected QuestionSet(Parcel in) {
        this.numberOfQuestions = in.readInt();
        this.percentage = in.readDouble();
        this.isHard = in.readByte() != 0;
        this.time = in.readInt();
    }

    public static final Parcelable.Creator<QuestionSet> CREATOR = new Parcelable.Creator<QuestionSet>() {
        @Override
        public QuestionSet createFromParcel(Parcel source) {
            return new QuestionSet(source);
        }

        @Override
        public QuestionSet[] newArray(int size) {
            return new QuestionSet[size];
        }
    };
}
