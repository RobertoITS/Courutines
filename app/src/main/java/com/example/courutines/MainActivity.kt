package com.example.courutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.courutines.base.BaseActivity
import com.example.courutines.data.network.RepoImplement
import com.example.courutines.domain.UseCaseImpl
import com.example.courutines.presentation.viewmodel.MainViewModel
import com.example.courutines.presentation.viewmodel.MainViewModelFactory
import com.example.courutines.vo.Resource

//Hacemos que la actividad principal (y algunas que se comporten igual) se extiendan de la
//BaseActivity (que extiende de la AppCompatActivity) y las funciones principales se
//creen ahi. getViewID() ya retorna las vistas
class MainActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this,
            MainViewModelFactory(UseCaseImpl(RepoImplement())))[MainViewModel::class.java]
    }

    override fun getViewID(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Instanciamos el viewmodel para usar sus metodos
        //Busca la informacion:
        observeData()
    }

    private fun observeData(){
        //Observamos los datos
        //El owner, en caso de actividades es this, en caso de fragment es viewLyfecycleOwner, sino, el observer no muere hasta que muera la actividad contenedora
        viewModel.fetchVersionCode.observe(this@MainActivity, Observer { result ->
            when (result){
                //Manejamos el comportamiento de la busqueda
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    //Instanciamos el text view
                    val text = findViewById<TextView>(R.id.textVersion)
                    //Le pasamos los datos obtenidos
                    text.text = result.data.toString()
                    //Escondemos el progressBar
                    hideProgressBar()
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Error ${result.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}