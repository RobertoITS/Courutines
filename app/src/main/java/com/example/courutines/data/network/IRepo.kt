package com.example.courutines.data.network

import com.example.courutines.vo.Resource

interface IRepo {
    suspend fun getVersionCodeRepo(): Resource<Int>
}