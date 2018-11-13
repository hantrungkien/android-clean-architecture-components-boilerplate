package com.kienht.androidcleanarchitectureboilerplate.features.employee_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.BaseViewHolder;
import com.kienht.androidcleanarchitectureboilerplate.features.base.GlideApp;
import com.kienht.androidcleanarchitectureboilerplate.features.base.GlideRequests;
import com.kienht.androidcleanarchitectureboilerplate.features.base.listener.OnClickEmployeeItemListener;
import com.kienht.presentation.model.EmployeeViewData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Note:
 * Created by kienht on 5/3/18.
 */

@PerActivity
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeesViewHolder> {

    public static final String TAG = "EmployeeAdapter.OnClickEmployeeItemListener";

    @Inject
    @Named(EmployeeAdapter.TAG)
    OnClickEmployeeItemListener onClickEmployeeItemListener;

    private List<EmployeeViewData> employeeList = new ArrayList<>();

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
        holder.bindData(employeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    @Override
    public void onViewRecycled(@NonNull EmployeesViewHolder holder) {
        super.onViewRecycled(holder);
        GlideApp.with(holder.itemView.getContext()).clear(holder.mImageAvatar);
    }

    public void swapData(List<EmployeeViewData> data) {
        this.employeeList.clear();
        this.employeeList.addAll(data);
        notifyDataSetChanged();
    }

    class EmployeesViewHolder extends BaseViewHolder<EmployeeViewData> {

        @BindView(R.id.text_name)
        TextView mTextName;

        @BindView(R.id.text_thanks)
        TextView mTextThanks;

        @BindView(R.id.image_avatar)
        CircleImageView mImageAvatar;

        private GlideRequests mGlide;

        public EmployeesViewHolder(View itemView) {
            super(itemView);
            mGlide = GlideApp.with(itemView.getContext());
        }

        @Override
        public void bindData(EmployeeViewData employee) {
            mTextThanks.setVisibility(getAdapterPosition() == 0 ? View.GONE : View.VISIBLE);
            mTextName.setText(employee.getName());

            mGlide.load(employee.getImgUrl())
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(mImageAvatar);
        }

        @OnClick(R.id.layout_root)
        void onClickItem() {
            onClickEmployeeItemListener.onClickEmployee(employeeList.get(getAdapterPosition()));
        }
    }
}
