package com.ibrahemgamal.retromvvm.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahemgamal.retromvvm.R;
import com.ibrahemgamal.retromvvm.data.model.User;
import com.ibrahemgamal.retromvvm.databinding.ShowUserItemTemplateBinding;

import java.util.ArrayList;
import java.util.List;

public class ShowUsersAdapter extends RecyclerView.Adapter<ShowUsersAdapter.ShowUsersViewHolder> implements Filterable {
    private List<User> userList;
    private List<User> filteredUserList;
    private onItemClickListen onItemClickListen;

    public ShowUsersAdapter(List<User> userList,onItemClickListen onItemClickListen) {
        this.filteredUserList = new ArrayList<>();
        this.userList = userList;
        this.onItemClickListen= onItemClickListen;
    }

    @NonNull
    @Override
    public ShowUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ShowUserItemTemplateBinding showUserItemTemplateBinding = DataBindingUtil
                .inflate(inflater, R.layout.show_user_item_template, parent, false);
        return new ShowUsersViewHolder(showUserItemTemplateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowUsersViewHolder holder, int position) {
        final User user = filteredUserList.get(position);
        holder.showUserItemTemplateBinding.setUser(user);
        holder.showUserItemTemplateBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<User> filteredList = new ArrayList<>();
            filteredList.clear();
            final FilterResults filterResults = new FilterResults();
            final String searchKey = charSequence.toString().trim().toLowerCase();
            if (searchKey.equals("") || searchKey.length() == 0) {
                filteredList.addAll(userList);
            } else {
                for (User user : userList) {
                    if ((user.getName().trim().toLowerCase()).contains(searchKey)) {
                        filteredList.add(user);
                    }
                }
            }
            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredUserList.clear();
            filteredUserList = (List<User>) filterResults.values;
            notifyDataSetChanged();
        }
    };

    public class ShowUsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ShowUserItemTemplateBinding showUserItemTemplateBinding;

        public ShowUsersViewHolder(@NonNull ShowUserItemTemplateBinding showUserItemTemplateBinding) {
            super(showUserItemTemplateBinding.getRoot());
            this.showUserItemTemplateBinding = showUserItemTemplateBinding;
            showUserItemTemplateBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListen.onItemClick(filteredUserList.get(getAdapterPosition()));
        }
    }
    public interface onItemClickListen{
        void onItemClick(User user);
    }
}
