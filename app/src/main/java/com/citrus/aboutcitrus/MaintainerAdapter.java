package com.citrus.aboutcitrus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MaintainerAdapter extends RecyclerView.Adapter<MaintainerAdapter.ViewHolder> {

    private String[] maintainerName, maintainerDesc, uriArray;
    private Context context;
    private static LayoutInflater inflater = null;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView maintainerName, maintainerDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            maintainerName = itemView.findViewById(R.id.maintainerName);
            maintainerDesc = itemView.findViewById(R.id.maintainerDesc);
        }
    }

    public MaintainerAdapter(FragmentActivity Activity, String[] maintainerName, String[] maintainerDesc, String[] uriArray) {
        this.maintainerName = maintainerName;
        this.maintainerDesc = maintainerDesc;
        this.uriArray = uriArray;
        context = Activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.maintainercard_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.maintainerName.setText(maintainerName[position]);
        holder.maintainerDesc.setText(maintainerDesc[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!uriArray[position].equals(""))
                    context.startActivity(new Intent(android.content.Intent.ACTION_VIEW)
                            .setData(Uri.parse(uriArray[position])));
                else
                    Toast.makeText(context.getApplicationContext(), "Nae Nae. Not Yet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return maintainerName.length;
    }
}
