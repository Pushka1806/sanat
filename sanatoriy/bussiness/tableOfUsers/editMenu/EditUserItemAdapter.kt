package com.example.sanatoriy.bussiness.tableOfUsers.editMenu

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatoriy.InternetConnection.model.Dishes
import com.example.sanatoriy.R

class EditUserItemAdapter(private val bludes: List<Dishes>,private val viewModel: EditMenuViewModel) : RecyclerView
.Adapter<EditUserItemAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var meal_id: String? = "0"
        val bt_rad1: RadioButton = itemView.findViewById(R.id.bt_radio1)
        val bt_rad2: RadioButton = itemView.findViewById(R.id.bt_radio2)
        val bt_rad3: RadioButton = itemView.findViewById(R.id.bt_radio3)
        val bt_rad4: RadioButton = itemView.findViewById(R.id.bt_radio4)

        val nameMeal: TextView = itemView.findViewById(R.id.name_item)
        val massMeal: TextView = itemView.findViewById(R.id.mass_item)
        val kbju: TextView = itemView.findViewById(R.id.kbju_item)
        val diet1: TextView = itemView.findViewById(R.id.diet1)
        val diet2: TextView = itemView.findViewById(R.id.diet2)
        val diet3: TextView = itemView.findViewById(R.id.diet3)
        val diet4: TextView = itemView.findViewById(R.id.diet4)
        val diet5: TextView = itemView.findViewById(R.id.diet5)
        val diet6: TextView = itemView.findViewById(R.id.diet6)
        val dietsMeal = arrayListOf<TextView>(diet1, diet2, diet3, diet4, diet5, diet6)
        val image: ImageView = itemView.findViewById(R.id.image_item)
        val kids: TextView = itemView.findViewById(R.id.kids_item)
        val dejurnoe_bludo: TextView = itemView.findViewById(R.id.dejurnoe_bludo_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Log.i("TAG", "onCreate")
        val itemView: View

        itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item3, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Log.i("TAG", "OnBind")
        holder.meal_id = bludes[position].id
        holder.nameMeal.text = bludes[position].name
        holder.massMeal.text = bludes[position].weight
        holder.kbju.text = readKbju(holder, position)
        var diets = bludes[position].dieta
        for (index in diets!!.indices) {
            holder.dietsMeal[index].text = diets[index]
        }
        holder.image.setImageResource(R.drawable.grecha)
        if (bludes[position].isForKids!!) {
            holder.kids.visibility = View.VISIBLE
        }
        if (bludes[position].isDefaultBludo!!) {
            holder.dejurnoe_bludo.visibility = View.VISIBLE
        }
//        if(!viewModel.disableListofUsers.contains(1)){ // если пользователя нет в списке юзеров выводим radiobutton
//            holder.bt_rad1.isChecked = bludes[position].user1
//            holder.bt_rad1.setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//                    findItemList(p0,)
//                }
//            })
//        }
//        else{
//            holder.bt_rad1.isEnabled = false
//        }
//
//        if(!viewModel.disableListofUsers.contains(2)){
//            holder.bt_rad2.isChecked = bludes[position].user2
//            holder.bt_rad2.setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//                    findItemList(p0)
//                }
//            })
//        }
//        else{
//            holder.bt_rad2.isEnabled = false
//        }
//
//        if(!viewModel.disableListofUsers.contains(3)){
//            holder.bt_rad3.isChecked = bludes[position].user3
//            holder.bt_rad3.setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//                    findItemList(p0)
//                }
//            })
//        }
//        else{
//            holder.bt_rad3.isEnabled = false
//        }
//
//        if(viewModel.disableListofUsers.contains(4)){
//            holder.bt_rad4.isChecked = bludes[position].user4
//            holder.bt_rad4.setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//                    findItemList(p0)
//                }
//            })
//        }
//        else{
//            holder.bt_rad4.isEnabled = false
//        }
    }

    override fun getItemCount() = bludes.size

    override fun getItemViewType(position: Int): Int {
        return if (position == bludes.size) R.layout.button else R.layout.menu_item3
    }


    fun readKbju(holder: MyViewHolder, position: Int): String {
        var kbju_item: String = " "
        kbju_item += bludes[position].calories
        kbju_item += "/"
        kbju_item += bludes[position].protein
        kbju_item += "/"
        kbju_item += bludes[position].fats
        kbju_item += "/"
        kbju_item += bludes[position].carbohydrates
        return kbju_item
    }

    fun findItemList(p0: View?) {
        val parent_row = p0!!.parent.parent.parent.parent.parent as View
        val lv: RecyclerView = parent_row.parent as RecyclerView
        val position: Int = lv.getChildLayoutPosition(parent_row)
       // filter_item(position, p0.id)

    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun filter_item(position: Int, radBt: Int) {
//        when (radBt) {
//            R.id.bt_radio1 -> {
//
//                for (item in bludes.indices) {
//                    bludes[item].user1 = item == position
//                    if(item == position){
//                        viewModel.addBludo(bludes[item], 0)
//                    }
//
//                }
//            }
//            R.id.bt_radio2 -> {
//
//                for (item in bludes.indices) {
//                    bludes[item].user2 = item == position
//                    viewModel.addBludo(bludes[item], 1)
//                }
//            }
//            R.id.bt_radio3 ->{
//                for (item in bludes.indices) {
//                    bludes[item].user3 = item == position
//                    viewModel.addBludo(bludes[item], 2)
//                }
//            }
//            R.id.bt_radio4 -> {
//
//                for (item in bludes.indices) {
//                    bludes[item].user4 = item == position
//                    viewModel.addBludo(bludes[item], 3)
//                }
//            }
//        }
//        notifyDataSetChanged()
//    }





}