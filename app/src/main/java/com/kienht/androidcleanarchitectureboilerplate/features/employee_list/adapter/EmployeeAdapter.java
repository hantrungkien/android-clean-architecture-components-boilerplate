package com.kienht.androidcleanarchitectureboilerplate.features.employee_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.features.base.GlideApp;
import com.kienht.androidcleanarchitectureboilerplate.features.base.GlideRequests;
import com.kienht.androidcleanarchitectureboilerplate.model.employee.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Note:
 * Created by kienht on 5/3/18.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeesViewHolder> {

    private List<EmployeeViewModel> employeeList = new ArrayList<>();

    @Inject
    public EmployeeAdapter() {
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {
        holder.bindData(employeeList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void swapData(List<EmployeeViewModel> data) {
        this.employeeList.clear();
        this.employeeList.addAll(data);
        notifyDataSetChanged();
    }

    class EmployeesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView mTextName;

        @BindView(R.id.text_thanks)
        TextView mTextThanks;

        @BindView(R.id.image_avatar)
        CircleImageView mImageAvatar;

        private GlideRequests mGlide;

        public EmployeesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mGlide = GlideApp.with(itemView.getContext());
        }

        void bindData(EmployeeViewModel employee, int position) {
            mTextThanks.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            mTextName.setText(employee.getName());

            mGlide.load(employee.getImgUrl())
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(mImageAvatar);


        }
    }
}
