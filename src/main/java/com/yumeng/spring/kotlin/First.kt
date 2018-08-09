package com.yumeng.spring.kotlin


fun main(args: Array<String>) {

    println("Hello Kotlin World!")
    //类型后面加?表示可为空
    var age: String? = null
    var a = 11
    //a=null;

//抛出空指针异常
   // val ages = age!!.toInt()
//不做处理返回 null
    val ages1 = age?.toInt()
//age为空返回-1
    val ages2 = age?.toInt() ?: -1




}
fun a(){
    print("hahaha")
}