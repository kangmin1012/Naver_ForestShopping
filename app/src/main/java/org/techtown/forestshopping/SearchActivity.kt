package org.techtown.forestshopping

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.techtown.forestshopping.adapter.ShoppingAdapter
import org.techtown.forestshopping.api.NaverServiceImpl
import org.techtown.forestshopping.data.ShoppingData
import org.techtown.forestshopping.databinding.ActivitySearchBinding
import org.techtown.forestshopping.utill.debugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    private lateinit var shoppingAdapter : ShoppingAdapter
    private lateinit var bottomSheetDialog : BottomSheetDialog
    private var LAYOUT_CODE : Int = 0
    private var items : List<ShoppingData.Data> = listOf()
    private var mSort : String = "sim"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.activity = this

        initRcv(LAYOUT_CODE)
        initRadio()
    }

    // 검색 버튼 클릭 리스너
    fun searchClick(@Suppress("UNUSED_PARAMETER") view : View) {
        //검색 버튼을 누르면 스레드를 통해 네이버 api 연동 시작

        val call : Call<ShoppingData> = NaverServiceImpl.service.getSearchShopping(search_edt.text.toString(), mSort)
        call.enqueue(
            object : Callback<ShoppingData>{
                override fun onFailure(call: Call<ShoppingData>, t: Throwable) {
                    "$t".debugLog("CallbackFail in Search")
                }

                override fun onResponse(
                    call: Call<ShoppingData>,
                    response: Response<ShoppingData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let{
                            "${it.items}".debugLog("Callback Success")
                            shoppingAdapter.data = it.items
                            items = it.items
                            shoppingAdapter.notifyDataSetChanged()
                        } ?: "nothing".debugLog("Callback Nothing")
                }
            }
        )

    }

    private fun initRcv(LAYOUT_CODE : Int){
        shoppingAdapter = ShoppingAdapter(this, LAYOUT_CODE)
        if (LAYOUT_CODE == 0 ){
            binding.searchrcv.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false)
        }
        else {
            binding.searchrcv.layoutManager = LinearLayoutManager(this)
        }
        binding.searchrcv.adapter = shoppingAdapter
        if(items.isNotEmpty()) {
            shoppingAdapter.data = items
            shoppingAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("ResourceAsColor")
    fun initRadio(){
        rg_list.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.rb_linear -> {
                    rb_linear.setTextColor(Color.parseColor("#ffffff"))
                    rb_grid.setTextColor(R.color.naverColor)
                    LAYOUT_CODE = 1
                    initRcv(LAYOUT_CODE)
                }
                R.id.rb_grid ->{
                    rb_linear.setTextColor(R.color.naverColor)
                    rb_grid.setTextColor(Color.parseColor("#ffffff"))
                    LAYOUT_CODE = 0
                    initRcv(LAYOUT_CODE)
                }

            }
        }
    }


    @SuppressLint("ResourceAsColor")
    fun order(view : View){
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet)
        bottomSheetDialog.show()

        bottomSheetDialog.bottom_correct_tv.setOnClickListener {
            mSort = "sim"
            searchClick(view)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.bottom_day_tv.setOnClickListener {
            mSort = "date"
            searchClick(view)
            bottomSheetDialog.dismiss()


        }

        bottomSheetDialog.bottom_price_tv.setOnClickListener {
            mSort = "asc"
            searchClick(view)
            bottomSheetDialog.dismiss()

       }

        when(mSort){
            "sim" -> {
                bottomSheetDialog.bottom_correct_tv.setTextColor(Color.parseColor("#1EC800"))
                bottomSheetDialog.bottom_correct_tv.typeface = Typeface.DEFAULT_BOLD
            }
            "date" -> {
                bottomSheetDialog.bottom_day_tv.setTextColor(Color.parseColor("#1EC800"))
                bottomSheetDialog.bottom_day_tv.typeface = Typeface.DEFAULT_BOLD

            }
            "asc" -> {
                bottomSheetDialog.bottom_price_tv.setTextColor(Color.parseColor("#1EC800"))
                bottomSheetDialog.bottom_price_tv.typeface = Typeface.DEFAULT_BOLD

            }
        }
    }


}
