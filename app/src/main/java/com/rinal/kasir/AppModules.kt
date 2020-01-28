package com.rinal.kasir

import com.rinal.kasir.db.Databases
import com.rinal.kasir.model.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Databases.getInstance(androidContext()) }
    viewModel { MainViewModel(get()) }
}