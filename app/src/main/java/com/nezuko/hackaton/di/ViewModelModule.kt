package com.nezuko.hackaton.di

import com.nezuko.biometry.BiometryViewModel
import com.nezuko.domain.repository.LocalStoreRepository
import com.nezuko.domain.repository.PermissionRepository
import com.nezuko.face.FaceViewModel
import com.nezuko.facehints.FaceHintsViewModel
import com.nezuko.home.HomeViewModel
import com.nezuko.voice.VoiceViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(
        get<LocalStoreRepository>()
    ) }
    viewModel<BiometryViewModel> { BiometryViewModel() }
    viewModel<FaceHintsViewModel> { FaceHintsViewModel(
        faceAnalyse = get()
    ) }
    viewModel<FaceViewModel> {
        FaceViewModel(
            get<PermissionRepository>()
        )
    }

    viewModel<VoiceViewModel> {
        VoiceViewModel(
            get<PermissionRepository>()
        )
    }

}