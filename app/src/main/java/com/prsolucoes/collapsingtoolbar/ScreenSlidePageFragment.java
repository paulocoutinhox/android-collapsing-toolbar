package com.prsolucoes.collapsingtoolbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ScreenSlidePageFragment extends Fragment {

    private RecyclerView list;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataset;

    public int orientation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        layoutManager = new LinearLayoutManager(getContext(), orientation, false);

        dataset = new ArrayList<>();

        for (int x = 0; x < 100; x++) {
            dataset.add(String.format(Locale.getDefault(), "Item: %d", x + 1));
        }

        adapter = new MyAdapter(dataset);

        list = rootView.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(layoutManager);
        list.setNestedScrollingEnabled(false);
        list.setAdapter(adapter);

        return rootView;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private ArrayList<String> dataset;

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvTitle;

            MyViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
            }

        }

        MyAdapter(ArrayList<String> dataset) {
            this.dataset = dataset;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            int layout = orientation == LinearLayoutManager.HORIZONTAL ? R.layout.list_item_horizontal : R.layout.list_item_vertical;

            View vh = inflater.inflate(layout, parent, false);
            return new MyAdapter.MyViewHolder(vh);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tvTitle.setText(dataset.get(position));
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

    }

}
