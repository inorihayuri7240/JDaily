package com.example.jdaily;

import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mObject.Itinerary;
import mObject.ListItem;

public class ToDoList extends AppCompatActivity {
    static private LstAdapter lstAdpBefore;
    static private LstAdapter lstAdpNow;
    static private LstAdapter lstAdpAfter;
    static private ArrayList<ListItem> arrLstItemBefore=new ArrayList<>();
    static private ArrayList<ListItem> arrLstItemNow=new ArrayList<>();
    static private ArrayList<ListItem> arrLstItemAfter=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);

//        //透明狀態欄
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }


        setLstViewItienrary();

        ListItem lstItem =(ListItem)getIntent().getExtras().getSerializable("lstItem"); //取得加入的清單項目
        if(null!=lstItem){
            LstAdapter thisLstAdp = null;
            if (lstItem.getLeftDay() < 0) thisLstAdp = lstAdpBefore; //如果為過去日期
            else if (lstItem.getLeftDay() == 0) thisLstAdp = lstAdpNow; //如果為當日日期
            else if (lstItem.getLeftDay() > 0) thisLstAdp = lstAdpAfter; //如果為未來日期
            addLstItem(thisLstAdp, lstItem); //將此項目加入thisLstAdp
        }
        setBtnEdit();
        setBtnHome();
    }

    private void setLstViewItienrary(){
        //初始化List相關物件
        lstAdpBefore=new ToDoList.LstAdapter(arrLstItemBefore); //過去項目
        lstAdpNow=new ToDoList.LstAdapter(arrLstItemNow); //當日項目
        lstAdpAfter=new ToDoList.LstAdapter(arrLstItemAfter); //未來項目
        //設置適配器
        ((ListView)findViewById(R.id.lstViewBefore)).setAdapter(lstAdpBefore);
        ((ListView)findViewById(R.id.lstViewNow)).setAdapter(lstAdpNow);
        ((ListView)findViewById(R.id.lstViewAfter)).setAdapter(lstAdpAfter);
    }

    //加入清單項目
    private void addLstItem(LstAdapter lstAdp, ListItem listItem){
        lstAdp.add(listItem);
        lstAdp.notifyDataSetChanged();
    }

    //移除行程項目
    private void delLstItem(LstAdapter lstAdp, int index){
        lstAdp.del(index);
        lstAdp.notifyDataSetChanged();
    }

    //List的適配器
    private class LstAdapter extends BaseAdapter {
        private List<ListItem> list;
//        private int thisPosition;
        private LstAdapter lstAdp=this;
        public LstAdapter(List list) {
            this.list = list;
        }

        public void add(ListItem listItem){
            list.add(listItem);
        }

        public void del(int index){ //index: 欲刪除之項目的索引值
            if (index>=0 && index<list.size())
                list.remove(index);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=LayoutInflater.from(ToDoList.this).inflate(R.layout.check_box_list, null);
            ListItem thislistItem=list.get(position); //取得索引為position的ListItem

            //設定該項目元件屬性
            ((TextView)convertView.findViewById(R.id.txtDate)).setText(new SimpleDateFormat("yyyy/MM/dd").format(thislistItem.getDate()));
            ((TextView)convertView.findViewById(R.id.txtLeftDay)).setText(String.valueOf(thislistItem.getLeftDay())+"Day");
            ((CheckBox)convertView.findViewById(R.id.checkBox)).setText(thislistItem.getContent());

            final Button btnDelete=findViewById(R.id.btnDelete);

//            btnDelete.setFocusable(false);
//            btnDelete.setOnClickListener(new Button.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    delLstItem(LstAdapter.this,position);
//                }
//            });

//            final Cursor cursor=getCursor();
//            btnDelete.setFocusable(false);
//            btnDelete.setTag(cursor.getPosition());
//            btnDelete.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    cursor.moveToPosition((int)btnDelete.getTag());
//                }
//            });

            return convertView;
        }
    }

    private void btnDeleteClick(){
//        delLstItem();
    }

    //設置編輯按鈕
    private void setBtnEdit(){
        ImageButton btnEdit=findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new ImageButton.OnClickListener(){
            int count=0;
                @Override
                public void onClick(View v) {
                    Button btnDelete=findViewById(R.id.btnDelete);
                    if(count++%2==0){
                        btnDelete.setVisibility(View.VISIBLE); //刪除紐顯示
                    }
                    else {
                        btnDelete.setVisibility(View.GONE); //刪除紐隱藏(不引響其他元件布局)
                    }
                }
        });
    }

    private void setBtnHome(){
        ImageButton btnHome=findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToDoList.this.finish();
            }
        });
    }
}
