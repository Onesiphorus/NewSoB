package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.AttachmentViewModel;

import java.util.List;

public class AddAttachmentActivity extends AppCompatActivity {

    private AttachmentViewModel mAttachmentViewModel;
    private Attachment attachment;
    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachment);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AttachmentListAdapter adapter = new AttachmentListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAttachmentViewModel = ViewModelProviders.of(this).get(AttachmentViewModel.class);

        mAttachmentViewModel.getAllAttachment().observe(this, new Observer<List<Attachment>>() {
            @Override
            public void onChanged(@Nullable List<Attachment> attachment) {
                adapter.setAttachment(attachment);
            }
        });

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            /*
            if(attachment != null) {
                intent.putExtra("serializable_object", attachment);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if (attachment != null) {
                sobCharacter.addAttachment(attachment);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, attachment.getName() + "added to inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, FoundGearActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

    class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.AttachmentViewHolder> {

        private final LayoutInflater mInflater;
        private List<Attachment> mAttachment;
        private Context mContext;
        private Integer RESULT_CODE = 1;
        public AttachmentListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public AttachmentListAdapter.AttachmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new AttachmentListAdapter.AttachmentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AttachmentListAdapter.AttachmentViewHolder holder, int position) {
            if (null != mAttachment) {
                Attachment current = mAttachment.get(position);
                holder.attachmentItemView.setText(current.getName());
            } else {
                holder.attachmentItemView.setText("No Attachment");
            }

            holder.attachmentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attachment = mAttachment.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + attachment.getName() + " to inventory";
                    btn.setText(text);
                }
            });
        }

        public void setAttachment(List<Attachment> attachment) {
            mAttachment = attachment;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mAttachment) return mAttachment.size();
            else return 0;
        }

        class AttachmentViewHolder extends RecyclerView.ViewHolder {
            private final TextView attachmentItemView;

            private AttachmentViewHolder(View itemView) {
                super(itemView);
                attachmentItemView = itemView.findViewById(R.id.textView);

            }
        }
    }
}


