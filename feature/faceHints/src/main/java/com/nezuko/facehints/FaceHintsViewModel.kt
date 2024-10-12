package com.nezuko.facehints

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.nezuko.domain.repository.FaceAnalyse

class FaceHintsViewModel(
    private val faceAnalyse: FaceAnalyse
) : ViewModel() {
    fun analyse(uri: Uri) = faceAnalyse.analyzeFace(uri)
}