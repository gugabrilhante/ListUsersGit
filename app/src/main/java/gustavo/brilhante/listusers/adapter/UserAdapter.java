package gustavo.brilhante.listusers.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import gustavo.brilhante.listusers.adapter.holders.UserHolder;
import gustavo.brilhante.listusers.listeners.UserAdapterListener;
import gustavo.brilhante.listusers.models.Data;

/**
 * Created by Gustavo on 22/05/17.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Data data;
    Context context;
    UserAdapterListener listener;

    UserHolder lastHolderSelected;
    int lastIndexSelected;

    boolean isSelectable = false;

    public UserAdapter(Data data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setListener(UserAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserHolder.build(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final UserHolder userHolder = (UserHolder) holder;
        userHolder.bind(data.results.get(position));
        if(position==0 && data.results.get(position).isSelected)lastHolderSelected = userHolder;
        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onUserClick(data.results.get(position));
                }
                if(isSelectable) {
                    if(!data.results.get(position).isSelected) {
                        data.results.get(position).isSelected = true;
                        userHolder.bind(data.results.get(position));
                        if (lastHolderSelected != null) {
                            data.results.get(lastIndexSelected).isSelected = false;
                            lastHolderSelected.result.isSelected = false;
                            lastHolderSelected.bind(data.results.get(lastIndexSelected));
                        }
                        lastHolderSelected = userHolder;
                        lastIndexSelected = position;
                    }
                }
            }
        });
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
        if(data!=null && data.results!=null && data.results.size()>0){
            data.results.get(0).isSelected = true;
            lastIndexSelected = 0;
            if(listener!=null){
                listener.onUserClick(data.results.get(0));
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.results.size();
    }
}
