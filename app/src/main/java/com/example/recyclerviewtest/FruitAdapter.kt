package com.example.recyclerviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 适配器 (Adapter)
 *
 * === 适配器的作用 ===
 * 1. 数据与界面的桥梁：将数据（如 fruitList）转换成用户可以看到的界面
 * 2. 回收复用机制：管理列表项的创建和复用，提高性能
 * 3. 事件处理：处理用户的点击、长按等交互事件
 *
 * === 什么时候需要适配器 ===
 * 只要使用 RecyclerView、ListView、GridView 等列表控件，就必须使用适配器！
     *
     * === ViewHolder 的作用 ===
     * 1. 缓存控件引用：只在创建时调用一次 findViewById，避免重复查找
     * 2. 提高性能：滚动时不需要反复查找控件，速度更快
     *
     * === 为什么需要 ViewHolder ===
     * 假设列表有 1000 条数据：
     * - 不使用 ViewHolder：每次滚动都要调用 findViewById，非常慢！
     * - 使用 ViewHolder：只创建屏幕可见的几个视图（如 10 个），然后复用
     *
     * === 工作原理 ===
     * 当用户向下滚动时，顶部滑出屏幕的列表项会被回收，
     * 然后复用到底部新出现的位置，只需要更新数据即可。
 * === 适配器的三个核心方法 ===
 * 1. onCreateViewHolder(): 创建列表项视图（只在需要新视图时调用）
 * 2. onBindViewHolder(): 绑定数据到视图（每次显示列表项时调用）
 * 3. getItemCount(): 告诉 RecyclerView 有多少条数据
 * === 工作流程 ===
 * RecyclerView 滚动时，会询问 Adapter：
 * - "我需要显示第 5 个项目，你有数据吗？" → getItemCount() 回答
 * - "我没有可用的视图了，给我创建一个新的" → onCreateViewHolder() 创建
 * - "把第 5 个数据显示到这个视图上" → onBindViewHolder() 绑定
 *
 * @param fruitList 要显示的水果数据列表
 */
class FruitAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    /**
     * 创建 ViewHolder
     * 当 RecyclerView 需要新的 ViewHolder 时会调用此方法
     * @param parent 父视图组
     * @param viewType 视图类型（用于多种布局类型的场景）
     * @return 创建好的 ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 使用 LayoutInflater 将布局文件转换为 View 对象
        // LayoutInflater.inflate() 能够将 XML 转为 View 对象是因为：
        //    XML 解析：读取并解析布局文件的结构
        //
        //    反射机制：根据标签名创建对应的 Java 类实例
        //
        //    属性设置：将 XML 属性设置到 View 对象中
        //
        //    层次构建：递归创建子 View 并建立父子关系
        //
        //    返回结果：返回完整的 View 对象树
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fruit, parent, false)

        val viewHolder = ViewHolder(view)

        // 为整个列表项设置点击事件
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition // 获取点击项的位置
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "你点击了: ${fruit.name}", Toast.LENGTH_SHORT).show()
        }

        // 为图片设置点击事件（演示单独控件的点击）
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "你点击了 ${fruit.name} 的图片", Toast.LENGTH_SHORT).show()
        }

        return viewHolder
    }

    /**
     * 绑定数据到 ViewHolder
     * 当 RecyclerView 需要显示数据时会调用此方法
     * @param holder ViewHolder 实例
     * @param position 当前项的位置
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position] // 获取当前位置的数据
        holder.fruitImage.setImageResource(fruit.imageId) // 设置图片
        holder.fruitName.text = fruit.name // 设置文字
    }

    /**
     * 返回列表项的总数
     * RecyclerView 通过此方法知道要显示多少项
     */
    override fun getItemCount() = fruitList.size
}

