package com.example.sanatoriy.InternetConnection.model.Table

class userTable(private val table_num: Int) {
    var tableNumber:Int = table_num
    var itemOfTable= mutableListOf<ItemOfTable>(
        ItemOfTable(0),
        ItemOfTable(1),
        ItemOfTable(2),
        ItemOfTable(3),
        ItemOfTable(4),
        ItemOfTable(5)
        )
}