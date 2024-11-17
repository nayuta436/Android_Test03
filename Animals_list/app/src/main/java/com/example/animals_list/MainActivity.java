package com.example.animals_list;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] imageIds = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant};
    private View lastClickedView = null; // 用于跟踪上一个被点击的视图

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        //创建list集合，list集合的元素是map
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Name", names[i]);
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item, new String[]{"Name", "image"},
                new int[]{R.id.name, R.id.image});
        ListView list = findViewById(R.id.main);
        //为listview设置Adapter
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取点击项的数据
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String personName = (String) item.get("Name");

                // 显示 Toast 消息
                Toast.makeText(MainActivity.this, personName, Toast.LENGTH_SHORT).show();
                // 改变背景颜色
                if (lastClickedView != null) {
                    lastClickedView.setBackgroundColor(Color.WHITE); // 将上一个点击的视图恢复成白色
                }
                view.setBackgroundColor(Color.RED); // 当前点击的视图变成红色
                lastClickedView = view; // 更新上一个点击的视图
            }
        });
    }
}
