package com.example.courutines.data.network

import com.example.courutines.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RepoImplement: IRepo {
    override suspend fun getVersionCodeRepo(): Resource<Int> {
        val resultData = FirebaseFirestore.getInstance().collection("params")
            .document("app").get().await() //Este metodo se aplica sobre un Task, hasta que no traiga los datos, no pasa a la linea siguiente
        val versionCode = resultData.getLong("version")
        //FIREBASE
        //Aca se retorna la informacion
        return  Resource.Success(versionCode!!.toInt())
    }
}