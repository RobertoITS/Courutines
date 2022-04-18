package com.example.courutines.domain

import com.example.courutines.data.network.IRepo
import com.example.courutines.vo.Resource

class UseCaseImpl(private val repo: IRepo): IUseCase {
    override suspend fun getVersionCode(): Resource<Int> = repo.getVersionCodeRepo()
}