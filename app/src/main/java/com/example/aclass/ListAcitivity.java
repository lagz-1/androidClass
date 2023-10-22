package com.example.aclass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ListAcitivity extends AppCompatActivity {

    RadioGroup rg1;
    CheckBox cb1;
    Spinner sp1;
    ListView lv1, lv2;
    String[] str_arr = {"ko", "jo", "james"};
    String[] age_arr = {"68", "85", "27"};
    Button btn1;
    SharedPreferences sp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_acitivity);

        rg1 = findViewById(R.id.rg1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                Toast.makeText(ListAcitivity.this, rb.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });


        cb1 = findViewById(R.id.cb1);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String txt = b ? "checked RED" : "unchecked RED";
                //getResources()
                Toast.makeText(ListAcitivity.this, txt, Toast.LENGTH_SHORT).show();
            }
        });

        sp1 = findViewById(R.id.sp1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] myarry = getResources().getStringArray(R.array.myarry);
                Toast.makeText(ListAcitivity.this, myarry[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        lv1 = findViewById(R.id.lv1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] myarry = getResources().getStringArray(R.array.myarry);
                Toast.makeText(ListAcitivity.this, myarry[i], Toast.LENGTH_SHORT).show();
            }
        });

        //lv2 = findViewById(R.id.lv2);
        //ArrayAdapter<String> arr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,str_arr);
        lv2 = findViewById(R.id.lv2);
//        lv2.setAdapter(new myAdapter(getApplicationContext()));

//        btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sp = getSharedPreferences("config",MODE_PRIVATE);
//                int count = sp.getInt("count",0);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putInt("count",count+1);
//                editor.commit();
//                Toast.makeText(ListAcitivity.this,"",Toast.LENGTH_LONG).show();
//
//            }
//        });


        btn1 = findViewById(R.id.btn1);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        int count = sp.getInt("count", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count", count + 1);
        editor.commit();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ListAcitivity.this,
                        String.valueOf(sp.getInt("count", 0))
                        , Toast.LENGTH_LONG).show();

            }
        });


    }


//    class myAdapter extends BaseAdapter{
//
//        Context context;
//        public myAdapter(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public int getCount() {//items 一共有多少个item
//            return age_arr.length;
//        }
//
//        @Override
//        public Object getItem(int i) {//i其实是position
//            return i;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            if(i%2 == 0){
//                //根据模的结果，加载到不同的item.xml中
//                view = LayoutInflater.from(context).inflate(R.layout.activity_item,null);
//            }else{
//                view = LayoutInflater.from(context).inflate(R.layout.activity_item2,null);
//            }
//            TextView tv1 = view.findViewById(R.id.lv1);
//            TextView tv2 = view.findViewById(R.id.lv2);//如果没有声明就使用，是非常危险的错误，编译不出来，但是会闪退
//            tv1.setText(str_arr[i]);
//            tv2.setText(age_arr[i]);
//            return view;
//        }
//    }
}