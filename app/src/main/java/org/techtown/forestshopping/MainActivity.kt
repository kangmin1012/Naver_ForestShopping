package org.techtown.forestshopping

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.forestshopping.data.Client
import org.techtown.forestshopping.utill.debugLog
import org.techtown.forestshopping.utill.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var mOAuthLoginModule : OAuthLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //인스턴스 초기화
        mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(this, Client.OAUTH_CLIENT_ID,
            Client.OAUTH_CLIENT_SECRET,
            Client.OAUTH_CLIENT_NAME)

        naver_btn.setOAuthLoginHandler(mOAuthLoginHandler)

        logout_btn.setOnClickListener {
//            mOAuthLoginModule.logout(this)
            val thread = LogOutThread()
            thread.start()

            showToast("로그아웃 됐습니다.")
        }
    }

    private val mOAuthLoginHandler = @SuppressLint("HandlerLeak")
        object : OAuthLoginHandler(){
            override fun run(p0: Boolean) {
                if(p0){
                    Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
                    startActivity(intent)
                 }
                else{
                    val errorCode = mOAuthLoginModule.getLastErrorCode(this@MainActivity).code
                    val errorDesc = mOAuthLoginModule.getLastErrorDesc(this@MainActivity)
                    showToast("로그인 오류")
                    "errorCode : $errorCode , errorDesc : $errorDesc".debugLog()
                }
            }
        }

    inner class LogOutThread : Thread() {
        override fun run() {
            super.run()
            mOAuthLoginModule.logoutAndDeleteToken(this@MainActivity)
        }
    }

}
