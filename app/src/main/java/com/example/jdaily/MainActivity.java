package com.example.jdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mObject.DB;
import mObject.Itinerary;
import mObject.ListItem;
import mObject.Member;
import mObject.MemberDao;

public class MainActivity extends AppCompatActivity {
    TextView txtDate;
    LstAdapter lstAdp;
    MemberDao memberDao;

//    public static final int RESULT_MAINACTIVITY = 1;
    public static final int REQ_FROM_MAINACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//資料庫測試 //目前仍有錯誤
//        Member m=new Member("A01","aaa","A","男",Calendar.getInstance().getTime(),"A01@example.com");
//        Member m=new Member();
//        m.setLoginId("A01");
//        memberDao=DB.getInstance(this).getMemberDao();
//        memberDao.insert(m);

        //透明狀態欄
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        setTxtDate();
        setBtnMenu();
        setBtnAdd();
        setBtnAddItinerary();
        setLstViewItienrary();
        setBtnRegister();
        setBtnLogin();
        setBtnToDoList();
        setBtnDiary();
        setBtnAddToDoList();
    }

    private Context getParentContext(){
        return this;
    }

    private void setTxtDate(){
        txtDate=findViewById(R.id.txtDate);
        Calendar cal = Calendar.getInstance();
        CharSequence toDay = DateFormat.format("yyyy MM dd", cal.getTime()); //取得當天日期
        txtDate.setText(toDay);

        TextView.OnClickListener txtDateOnCkickListener=new TextView.OnClickListener(){
//            int count;
            @Override
            public void onClick(View v) {
                //執行Activity
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Calender.class);
                startActivityForResult(intent,REQ_FROM_MAINACTIVITY);

//                if(++count==1){
//                    findViewById(R.id.conLay).setVisibility(View.INVISIBLE); //隱藏佈局
//                    DrawerLayout draLay=findViewById(R.id.draLay);
//                    CalendarView calView=new CalendarView(getParentContext());
//                    ViewGroup.LayoutParams layParcalView=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                    //LayoutParams(int width, int height) : 使用指定的寬度和高度創建一組新的佈局參數
//                    draLay.addView(calView,layParcalView);
//                    //public void addView (View child, ViewGroup.LayoutParams params) : 添加具有指定佈局參數的子視圖
//
//                    ImageButton imgbtn=new ImageButton(getParentContext());
//                    Resources res = getResources();
//                    Drawable drawable = ContextCompat.getDrawable(getParentContext(),R.drawable.ic_done_white_24dp); //返回與特定資源ID關聯的可繪製對象
//                    imgbtn.setImageDrawable(drawable); //設置一個drawable作為此ImageView的內容
//                    ViewGroup.LayoutParams layParImgBtn=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                    draLay.addView(imgbtn,layParImgBtn);
//
//                    Button button=new Button(getParentContext());
//                    conLay.addView(button);
//                    ConstraintSet conSet=new ConstraintSet(); //約束集
//                    conSet.clone(conLay); //復制一份父佈局的約束
//                    conSet.connect(button.getId(),ConstraintSet.RIGHT,conLay.getId(),ConstraintSet.LEFT);
//                    conSet.connect(button.getId(),ConstraintSet.BOTTOM,conLay.getId(),ConstraintSet.BOTTOM);
//                    //public void connect (int startID, int startSide, int endID,  int endSide) :
//                    // 修改一個id為startID的元件的約束,相依賴的約束組件為一個id為endID的元件,修改為元件1的startSide和元件2的endSide對齊
//                    conSet.applyTo(conLay); //將約束應用​​於ConstraintLayout
//                }
            }
        };

        txtDate.setOnClickListener(txtDateOnCkickListener);

    }

    private void setBtnMenu(){
        ImageButton btnMenu=findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                DrawerLayout draLay=findViewById(R.id.draLay); //側滑選單
                draLay.openDrawer(GravityCompat.START); //通過使指定的動畫不在視線範圍內來打開它
            }
        });
    }

    private void setBtnAdd(){
        ImageButton btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new ImageButton.OnClickListener(){
            int count=0;
            ConstraintLayout conLayAdd = findViewById(R.id.conLayAdd);
            @Override
            public void onClick(View v) {
                if(count++%2 == 0) { //顯示Add選單
                    conLayAdd.setVisibility(View.VISIBLE);
                    darkenBackground(0.5f);
                }else { //隱藏Add選單
                    conLayAdd.setVisibility(View.INVISIBLE);
                    darkenBackground(1f);
                }
            }
        });
    }

    private void setBtnAddToDoList(){
        FloatingActionButton btnAddToDoList=findViewById(R.id.btnAddToDoList);
        btnAddToDoList.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("ToDoList");
                alertDialog.setMessage("請輸入要加入的項目");

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setNegativeButton("ADD",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent();
                            intent.setClass(MainActivity.this,ToDoList.class);
                            Bundle bun=new Bundle();

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            Date choiceDate = null;
                            try {
                                choiceDate=sdf.parse(txtDate.getText().toString());
                            } catch (ParseException e) {e.printStackTrace();}

                            ListItem thisLstItem=new ListItem(choiceDate,"test",input.getText().toString());
                            bun.putSerializable("lstItem",thisLstItem); //將String型別資料放入Bundle
                            intent.putExtras(bun);
                            startActivity(intent);
                        }
                    });
                alertDialog.setPositiveButton("EXIT",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                alertDialog.show();
            }
        });
    }

    private void setBtnAddItinerary(){
        FloatingActionButton btnAddItinerary=findViewById(R.id.btnAddItinerary);
        btnAddItinerary.setOnClickListener(new FloatingActionButton.OnClickListener(){
            ConstraintLayout conLayAdd = findViewById(R.id.conLayAdd);
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ItineraryAdd.class);
                Bundle bun=new Bundle();
                bun.putString("mainDate",txtDate.getText().toString()); //將String型別資料放入Bundle
                intent.putExtras(bun);
                startActivityForResult(intent,REQ_FROM_MAINACTIVITY);
            }
        });
    }

    private void setBtnToDoList(){
        ImageButton btnToDoList=findViewById(R.id.btnToDoList);
        btnToDoList.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,ToDoList.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnDiary(){
        ImageButton btnDiary=findViewById(R.id.btnDiary);
        btnDiary.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Diary.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnRegister(){
        Button btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnLogin(){
        Button btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }

    private void setLstViewItienrary(){
        ArrayList<Itinerary> arrLstItn=new ArrayList<>();
        lstAdp=new LstAdapter(MainActivity.this,arrLstItn);
        ((ListView)findViewById(R.id.lstViewItienrary)).setAdapter(lstAdp);
    }

    //加入行程項目
    private void addLstItem(Itinerary newIti){
        lstAdp.add(newIti);
        lstAdp.notifyDataSetChanged();
    }

    //移除行程項目
    private void delLstItem(int index){
        lstAdp.del(index);
        lstAdp.notifyDataSetChanged();
    }

    //List的適配器
    private class LstAdapter extends BaseAdapter {
        private Context context;
        private List<Itinerary> list;
        public LstAdapter(Context context, List list) {
            this.context = context;
            this.list = list;
        }

        public void add(Itinerary iti){
            list.add(iti);
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=LayoutInflater.from(context).inflate(R.layout.schedule, null);
            Itinerary thisIti=list.get(position);

            SimpleDateFormat sdf = new SimpleDateFormat("HH : mm");
            String timeStart=sdf.format(thisIti.getDateStart());
            String timeEnd=sdf.format(thisIti.getDateEnd());

            ((TextView)convertView.findViewById(R.id.txtTimeStart)).setText(timeStart);
            ((TextView)convertView.findViewById(R.id.txtTimeEnd)).setText(timeEnd);
            ((TextView)convertView.findViewById(R.id.txtItiName)).setText(thisIti.getName());
            return convertView;
        }
    }

    //改變背景颜色
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp); //指定自定義窗口屬性
    }

    //取得選擇的日期
    private String getChoiceDate(){
        String choiceDate=getIntent().getStringExtra("choiceDate");
        return choiceDate;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Calender.RESULT_CALENDER) //確認是否從CANCELED回傳
        {
//            if(requestCode == REQ_FROM_MAINACTIVITY) //確認所要執行的動作
//            {
                String choiceDate = data.getExtras().getString("choiceDate"); //取得選擇的日期
                txtDate.setText(choiceDate);
//            }
        }
        else if(resultCode == ItineraryAdd.RESULT_ITINERARYADD){
            Itinerary newIti = (Itinerary)data.getExtras().getSerializable("newIti"); //取得行程物件
            addLstItem(newIti);
        }
    }
}
