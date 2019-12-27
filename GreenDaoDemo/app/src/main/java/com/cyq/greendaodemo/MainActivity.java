package com.cyq.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.greendaodemo.bean.DaoMaster;
import com.cyq.greendaodemo.bean.DaoSession;
import com.cyq.greendaodemo.bean.Student;
import com.cyq.greendaodemo.bean.StudentDao;
import com.cyq.greendaodemo.bean.Test;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnInsert;
    private TextView tvResult;
    private Button btnQuery;
    private StudentDao studentDao;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnQuery = findViewById(R.id.btn_query);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btn_insert);
        tvResult = findViewById(R.id.tv_result);
        btnQuery = findViewById(R.id.btn_query);
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        initDb();
    }

    private void initDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "student-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        studentDao = daoSession.getStudentDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_query:
                query();
                break;
        }
    }


    /**
     * 插入数据
     */
    private void insert() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setId(-i * 3 + 100);
            student.setAge(random.nextInt(80));
            student.setTime(1577152151646L);
            student.setName("赵淑芳" + i);
            daoSession.insertOrReplace(student);
        }
    }

    /**
     * 查询所有数据
     */
    private void query() {
        List<Student> mList = daoSession.loadAll(Student.class);
        for (Student student : mList) {
            Log.i("test", student.toString());
        }
        List<Student> maxPostIdRow = studentDao.queryBuilder().where(StudentDao.Properties.Id.isNotNull()).orderDesc(StudentDao.Properties.Id).limit(1).list();

        Long maxPostId = maxPostIdRow.get(0).getId();
        Log.i("test", "ID最大值：" + maxPostId);
    }
}
