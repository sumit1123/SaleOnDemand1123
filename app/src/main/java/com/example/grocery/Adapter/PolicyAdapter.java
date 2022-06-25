package com.example.grocery.Adapter;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.example.grocery.Activities.PolicyActivity;
import com.example.grocery.Model.PolicyModel;
import com.example.grocery.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mohd Afzal on 9/29/2017.
 */

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.InfoHolder> {
    private final PolicyActivity context;
    private final List<PolicyModel> list;
    private PolicyModel.PolicyTranslationBean policyTranslation;

    public PolicyAdapter(PolicyActivity policyActivity, List<PolicyModel> list) {
        this.context = policyActivity;
        this.list = list;


    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.policyadapter, parent, false);
        return new InfoHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        final Gson gson = new Gson();
        final PolicyModel policyModel = list.get(position);
        String jsonString = gson.toJson(policyModel.getPolicy_translation());
        System.out.println("zdnfjc" + jsonString);

        if (jsonString.equals("null")) {

            jsonString = gson.toJson(policyModel.getDefault_policy_translation());
            System.out.println("zdnfjc" + jsonString);

        }

        policyTranslation = gson.fromJson(jsonString,
                PolicyModel.PolicyTranslationBean.class);
        if (policyTranslation.getPolicy_description() != null) {
            if (!policyTranslation.getPolicy_description().matches("")) {
                holder.policy.setText(Html.fromHtml(policyTranslation.getPolicy_description()));
            } else {
                holder.policy.setVisibility(View.GONE);
            }
        } else {
            holder.policy.setVisibility(View.GONE);

        }
        if (position == list.size() - 1) {
            holder.customLineVisibility.setVisibility(View.GONE);
        } else {
            holder.customLineVisibility.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoHolder extends RecyclerView.ViewHolder {
        private final LinearLayout customLineVisibility;
        TextView policy;

        public InfoHolder(View itemView) {
            super(itemView);
            policy = (TextView) itemView.findViewById(R.id.textpolicy);
            customLineVisibility = (LinearLayout) itemView.findViewById(R.id.linhider);

        }
    }
}
