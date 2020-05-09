package com.freehindiofflineapps.sqliteloginsignup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> userList;
    DatabaseHelper databaseHelper;
    Context context;
    public UserAdapter(List<User> userList,Context context) {


        this.userList = userList;
        this.context=context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.textViewName.setText(user.getUsername());
        holder.textViewPassword.setText(user.getPassword());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewPassword;

        public ViewHolder(@NonNull View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewPassword = (TextView) view.findViewById(R.id.textViewPassword);
        }

    }
}
