package mtucar.xyz.mathbet.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.zip.Inflater;

import mtucar.xyz.mathbet.R;
import mtucar.xyz.mathbet.model.QuestionSet;

public class QSetAdapter extends ArrayAdapter {
    QuestionSet[] mQSet;
    LayoutInflater mLInflater;

    public QSetAdapter(@NonNull Context context, @NonNull Object[] objects) {
        super(context, R.layout.qset_list_item, objects);

        mQSet = (QuestionSet[]) objects;
        mLInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mLInflater.inflate(R.layout.qset_list_item, parent, false);
        }

        TextView tvRatio = convertView.findViewById(R.id.tvRatio);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        TextView tvHard = convertView.findViewById(R.id.tvLevel);

        QuestionSet qSet = mQSet[position];

        tvRatio.setText(String.format("%.3f", qSet.getPercentage()));
        tvTime.setText(String.valueOf(qSet.getTime()));
        if(qSet.isHard()){
            tvHard.setText("Hard");}
            else tvHard.setText("Easy");

        return convertView;
    }
}
