package org.techtown.forestshopping

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import org.techtown.forestshopping.data.ShoppingData
import org.techtown.forestshopping.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var mDetailInfo : ShoppingData.Data
    private lateinit var mBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        mBinding.detatilActivity = this

        // key : 'data'
        val intent = intent
        mDetailInfo = intent.getSerializableExtra("data") as ShoppingData.Data
        mBinding.detail = mDetailInfo


    }

    fun goToLink(@Suppress("UNUSED_PARAMETER") view : View){
        val linkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mDetailInfo.link))
        startActivity(linkIntent)

    }
}
