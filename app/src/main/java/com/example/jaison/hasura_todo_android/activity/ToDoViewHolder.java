package com.example.jaison.hasura_todo_android.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jaison.hasura_todo_android.R;

/**
 * Created by jaison on 11/01/17.
 */

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    CheckBox checkbox;
    TextView description;

    public ToDoViewHolder(View itemView) {
        super(itemView);

        description = (TextView) itemView.findViewById(R.id.description);
        checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }
}
