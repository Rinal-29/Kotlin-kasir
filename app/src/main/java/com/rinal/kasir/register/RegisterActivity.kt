package com.rinal.kasir.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import com.rinal.kasir.R
import com.rinal.kasir.entity.Users
import com.rinal.kasir.model.MainViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_register)

        img_back_register.setOnClickListener {
            onBackPressed()
            finish()
        }

        btn_create_account.setOnClickListener {
            if (userRegister()){
                Snackbar.make(register_layout, "Berhasil Daftar, Silahkan Login", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(register_layout, "Gagal Daftar!!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun userRegister() : Boolean {

        val email = edt_email.text.toString()
        val username = edt_username.text.toString()
        val password = edt_password.text.toString()
        val fullname = edt_full_name.text.toString()
        val outlet = edt_outlet.text.toString()

        var isEmptyFields = false
        var isInvalidPassword = true
        var isRegister = false

        if (TextUtils.isEmpty(email)){
            isEmptyFields = true
            edt_email.error = "Field tidak boleh kosong"
        }

        if (TextUtils.isEmpty(username)){
            isEmptyFields = true
            edt_username.error = "Field tidak boleh kosong"
        }

        if (password.length <= 4){
            isInvalidPassword = false
            edt_password.error = "password minimal 5 karakter"
        }

        if (TextUtils.isEmpty(fullname)){
            isEmptyFields = true
            edt_full_name.error = "Field tidak boleh kosong"
        }

        if (TextUtils.isEmpty(outlet)){
            isEmptyFields = true
            edt_outlet.error = "Field tidak boleh kosong"
        }

        if (!isEmptyFields && isInvalidPassword){
            val userRegister =
                Users(email = email,
                username = username,
                password = password,
                fullName = fullname,
                outlet = outlet)

            mainViewModel.insertUser(userRegister)

            isRegister = true
        }

        return isRegister
    }
}
