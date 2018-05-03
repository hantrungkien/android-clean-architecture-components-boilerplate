package com.kienht.androidcleanarchitectureboilerplate.features.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.model.employee.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Note:
 * Created by kienht on 5/3/18.
 */
public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder> {

    private List<EmployeeViewModel> employeeViewModelList = new ArrayList<>();

    @Inject
    public EmployeesAdapter() {
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {
        holder.bindData(employeeViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeViewModelList.size();
    }

    public void swapData(List<EmployeeViewModel> data) {
        this.employeeViewModelList.clear();
        this.employeeViewModelList.addAll(data);
        notifyDataSetChanged();
    }

    class EmployeesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView mTextName;

        public EmployeesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(EmployeeViewModel employeeViewModel) {
            mTextName.setText(employeeViewModel.getName());
        }
    }
}
