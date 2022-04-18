package com.example.courutines.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.courutines.domain.IUseCase

//Esta clase se genera para pasar las instancias al viewmodel
class MainViewModelFactory(val useCase: IUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IUseCase::class.java).newInstance(useCase)
    }
}