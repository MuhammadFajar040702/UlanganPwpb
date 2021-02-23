package com.example.Ulangan_Tugas_Mandiri;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;
    public class MainFragment extends Fragment implements View.OnClickListener,RecyclerviewAdapter.OnUserClickListener{
        RecyclerView recyclerView;
        EditText edtTitle,edtDesc;
        Button btnSubmit;
        RecyclerView.LayoutManager layoutManager;
    Context context;
    List<PersonBean> listPersonInfo;
    public MainFragment() {
// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        context=getActivity();
        recyclerView=view.findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        edtTitle=view.findViewById(R.id.edttitle);
        edtDesc=view.findViewById(R.id.edtdesc);
        btnSubmit=view.findViewById(R.id.btnsubmit);
        btnSubmit.setOnClickListener(this);
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        DatabaseHelper db=new DatabaseHelper(context);
        listPersonInfo=db.selectUserData();
        RecyclerviewAdapter adapter=new
                RecyclerviewAdapter(context,listPersonInfo,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsubmit){
            DatabaseHelper db = new DatabaseHelper(context);
            PersonBean currentPerson = new PersonBean();
            String btnStatus=btnSubmit.getText().toString();
            if(btnStatus.equals("Submit")) {
                currentPerson.setDate(new java.util.Date().getTime());
                currentPerson.setTitle(edtTitle.getText().toString());
                currentPerson.setDesc(edtDesc.getText().toString());
                db.insert(currentPerson);
            }
            if(btnStatus.equals("Update")){
                currentPerson.setDate(new java.util.Date().getTime());
                currentPerson.setTitle(edtTitle.getText().toString());
                currentPerson.setDesc(edtDesc.getText().toString());
                db.update(currentPerson);
            }
            setupRecyclerView();
            edtTitle.setText("");
            edtDesc.setText("");
            btnSubmit.setText("Submit");
        }
    }
    @Override
    public void onUserClick(PersonBean currentPerson, String action) {
        if(action.equals("Edit")){
            edtTitle.setText(currentPerson.getTitle());
            edtDesc.setText(currentPerson.getDesc());
            btnSubmit.setText("Update");
        }
        if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(context);
            db.delete(currentPerson.getTitle());
            setupRecyclerView();
        }
    }
}