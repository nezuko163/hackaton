package com.nezuko.data.repository

import android.net.Uri
import com.nezuko.domain.model.MLResponse
import com.nezuko.domain.model.ResultModel
import com.nezuko.domain.repository.FaceAnalyse
import kotlinx.coroutines.flow.Flow

class FaceAnalyseImpl : FaceAnalyse {
    override fun analyzeFace(uri: Uri): MLResponse {

        return if (Math.random() < 0.5) MLResponse.NORM else MLResponse.NE_NORM
    }

}