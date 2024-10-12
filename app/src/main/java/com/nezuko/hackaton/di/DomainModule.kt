package com.nezuko.hackaton.di

import com.nezuko.data.repository.FaceAnalyseImpl
import com.nezuko.data.repository.LocalStoreRepositoryImpl
import com.nezuko.data.repository.PermissionRepositoryImpl
import com.nezuko.domain.repository.FaceAnalyse
import com.nezuko.domain.repository.LocalStoreRepository
import com.nezuko.domain.repository.PermissionRepository
import org.koin.dsl.module

val domainModule = module {
    single<PermissionRepository> {
        PermissionRepositoryImpl(
            context = get()
        )
    }

    single<LocalStoreRepository> {
        LocalStoreRepositoryImpl(
            context = get()
        )
    }

    single<FaceAnalyse> {
        FaceAnalyseImpl()
    }

}