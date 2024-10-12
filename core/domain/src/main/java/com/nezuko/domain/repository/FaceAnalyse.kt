package com.nezuko.domain.repository

import android.net.Uri
import com.nezuko.domain.model.MLResponse
import com.nezuko.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface FaceAnalyse {
    fun analyzeFace(uri: Uri): MLResponse
}