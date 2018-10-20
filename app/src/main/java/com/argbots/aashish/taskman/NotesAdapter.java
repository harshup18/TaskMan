package com.argbots.aashish.taskman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<Note> notesList;
    private Context mCtx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            
            date = (TextView) view.findViewById(R.id.date);
        }
    }


    public NotesAdapter(List<Note> notesList, Context mCtx) {
        this.notesList = notesList;
        this.mCtx=mCtx;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.date.setText(note.getdate());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(mCtx, v);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(mCtx,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public void removeItem(int position) {
        notesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Note item, int position) {
        notesList.add(position, item);
        notifyItemInserted(position);
    }
    public ArrayList<Note> getData() {
        return (ArrayList<Note>) notesList;
    }
}