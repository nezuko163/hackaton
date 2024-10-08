package com.nezuko.hackaton.di

import com.nezuko.data.repository.PermissionRepositoryImpl
import com.nezuko.domain.repository.PermissionRepository
import org.koin.dsl.module

val domainModule = module {
    single<PermissionRepository> {
        PermissionRepositoryImpl(
            context = get()
        )
    }
}