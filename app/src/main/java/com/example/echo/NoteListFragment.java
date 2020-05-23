package com.example.echo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Author:Ruibo Liu(ruibo.liu.gr@dartmoputh.edu)
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class NoteListFragment extends Fragment {
    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        mNoteRecyclerView = view.findViewById(R.id.note_recycler_view);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        NoteFactory noteFac = NoteFactory.get(getActivity());
        List<Note> notes = noteFac.getNotes();

        mAdapter = new NoteAdapter(notes);
        mNoteRecyclerView.setAdapter(mAdapter);
    }

    private class NoteHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Note mNote;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_note, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.note_title);
            mDateTextView = itemView.findViewById(R.id.note_date);
        }

        public void bind(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mDateTextView.setText(mNote.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mNote.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new NoteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note crime = mNotes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }


}