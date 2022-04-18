package com.example.courutines.domain

import com.example.courutines.vo.Resource

interface IUseCase {
    //Es un metodo que no se sabe cuando va a terminar.
    //Va a ir a la BD a buscar la info.
    //cuando retorne, va a continuar con lo otro
    suspend fun getVersionCode(): Resource<Int>
}