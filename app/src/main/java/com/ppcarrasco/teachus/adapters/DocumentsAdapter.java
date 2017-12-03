package com.ppcarrasco.teachus.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.ppcarrasco.teachus.R;
import com.ppcarrasco.teachus.data.Nodes;
import com.ppcarrasco.teachus.models.Document;

/**
 * Created by pedro on 29-11-2017.
 */

public class DocumentsAdapter extends FirebaseRecyclerAdapter<Document, DocumentsAdapter.DocumentHolder>{

    private DocumentsListener listener;

    public DocumentsAdapter(DocumentsListener listener) {
        super(Document.class, R.layout.list_item_document, DocumentHolder.class, new Nodes().getDocuments());
        this.listener = listener;
    }


    @Override
    protected void populateViewHolder(DocumentHolder viewHolder, final Document model, int position) {
        viewHolder.documentTv.setText(model.getName());
        viewHolder.authorTv.setText(model.getAuthor());
        viewHolder.setImage(model.getThumbnailUrl());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPressed(model);
            }
        });
    }


    public static class DocumentHolder extends RecyclerView.ViewHolder{
        private ImageView documentIv;
        private TextView documentTv;
        private TextView authorTv;
        private LinearLayout layout;

        public DocumentHolder(View itemView) {
            super(itemView);
            documentIv = (ImageView) itemView.findViewById(R.id.documentIv);
            documentTv = (TextView) itemView.findViewById(R.id.documentTv);
            authorTv = (TextView) itemView.findViewById(R.id.authorTv);
            layout = (LinearLayout) itemView.findViewById(R.id.cardLl);
        }

        public void setImage(String url){
            Glide.with(itemView)
                    .load(url)
                    .into(documentIv);
        }
    }
}
