package org.techtown.forestshopping.utill

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.DecimalFormat

//로그 찍는 확장함수
fun String.debugLog(tag: String = "ShopDebug"){
    Log.d(tag, this)
}

// 돈의 단위로 보여주는 확장함수
fun Int.formatMoney () : String {
    val formatter = DecimalFormat("###,###")

    return formatter.format(this)
}

// 토스트 메세지 확장함수

fun Context.showToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}


