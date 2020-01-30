package com.rinal.kasir.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.rinal.kasir.MainActivity
import com.rinal.kasir.R
import com.rinal.kasir.model.MainViewModel
import com.rinal.kasir.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edt_password
import kotlinx.android.synthetic.main.activity_login.edt_username
import kotlinx.android.synthetic.main.activity_login.tv_register
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_login)

        img_back.setOnClickListener {
            onBackPressed()
        }

        tv_register.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin(){
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()

        var usernameDb : String?
        var passwordDB : String?

        var isSuccess = true

        mainViewModel.getByName(username).observe(this, Observer{
            if (it.isEmpty()){
                usernameDb = "null"
                passwordDB = "null"
            } else {
                usernameDb = it?.get(0)?.nameUser
                passwordDB = it?.get(0)?.password
            }

            if (username != usernameDb) {
                isSuccess = false
                edt_password.error = "Password salah atau belum terdaftar"
                edt_username.error = "Username salah atau belum terdaftar"
            }

            if (password != passwordDB) {
                isSuccess = false
                edt_username.error = "Username salah atau belum terdaftar"
                edt_password.error = "Password salah atau belum terdaftar"
            }

            if (isSuccess){
                Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("USERS", it[0])
                intent.putExtra("userBundle", bundle)
                startActivity(intent)
                finish()
            }
        })
    }
}
