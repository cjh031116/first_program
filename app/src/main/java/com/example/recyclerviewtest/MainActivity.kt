package com.example.recyclerviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 主活动 - RecyclerView 学习示例
 * 演示如何使用 RecyclerView 显示列表数据
 */
class MainActivity : AppCompatActivity() {

    // 存储水果数据的列表
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化水果数据
        initFruits()

        // 获取 RecyclerView 控件
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // 设置布局管理器 - 决定 RecyclerView 如何排列列表项
        // LinearLayoutManager: 线性布局（垂直或水平列表）
        // GridLayoutManager: 网格布局
        // StaggeredGridLayoutManager: 瀑布流布局；StaggeredGridLayoutManager的构造函数接收两个参数：第一个参数用于指定布局的列数，传入3表示会把布局分为3列；第二个参数用于指定布局的排列方向，传入StaggeredGridLayoutManager.VERTICAL表示会让布局纵向排列。

        val layoutManager = LinearLayoutManager(this)
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL 水平方向，不设置的话默认是竖直方向
        recyclerView.layoutManager = layoutManager

        // 创建适配器并设置给 RecyclerView
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

    /**
     * 初始化水果数据
     * 使用 Android 系统自带的图标作为示例图片
     */
    private fun initFruits() {
        // 重复添加数据以便测试滚动效果
        repeat(3) {
            fruitList.add(Fruit("苹果", android.R.drawable.ic_menu_add))
            fruitList.add(Fruit("香蕉", android.R.drawable.ic_menu_agenda))
            fruitList.add(Fruit("橙子", android.R.drawable.ic_menu_call))
            fruitList.add(Fruit("西瓜", android.R.drawable.ic_menu_camera))
            fruitList.add(Fruit("梨", android.R.drawable.ic_menu_compass))
            fruitList.add(Fruit("葡萄", android.R.drawable.ic_menu_delete))
            fruitList.add(Fruit("菠萝", android.R.drawable.ic_menu_edit))
            fruitList.add(Fruit("草莓", android.R.drawable.ic_menu_gallery))
            fruitList.add(Fruit("樱桃", android.R.drawable.ic_menu_info_details))
            fruitList.add(Fruit("芒果", android.R.drawable.ic_menu_manage))
        }
    }
}