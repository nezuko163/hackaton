package com.nezuko.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.RectF
import android.net.Uri
import android.util.Log
import androidx.compose.ui.geometry.Rect
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

val TAG = "FILES_UTILS"

fun tempFile(context: Context): File {
    return File(context.cacheDir, "temp_image_${System.currentTimeMillis() / 1000}.jpg")
}

fun processImage(
    bitmap: Bitmap,
    shouldMirror: Boolean = true,
    left: Float,
    top: Float,
    right: Float,
    bottom: Float
): Bitmap {
    val ovalRect = Rect(
        left, top, right, bottom
    )

    Log.i(TAG, "processImage: height = ${bitmap.height}")
    Log.i(TAG, "processImage: width = ${bitmap.width}")
    Log.i(TAG, "processImage: config ${bitmap.config}")

    val _bitmap =
        if (shouldMirror) {
            mirrorBitmap(bitmap)
        } else {
            bitmap
        }

    return Bitmap.createBitmap(
        _bitmap,
        ovalRect.left.toInt(),
        ovalRect.top.toInt(),
        ovalRect.width.toInt(),
        ovalRect.height.toInt()
    )
}

fun mirrorBitmap(original: Bitmap): Bitmap {
    // Создаем матрицу для отражения
    val matrix = Matrix().apply {
        // Отражаем по оси X
        preScale(-1f, 1f)
    }

    // Создаем новый Bitmap с использованием матрицы
    return Bitmap.createBitmap(original, 0, 0, original.width, original.height, matrix, false)
}

fun saveBitmapToTempFile(context: Context, bitmap: Bitmap): Uri {
    val tempFile = tempFile(context)
    val outputStream = FileOutputStream(tempFile)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
    return Uri.fromFile(tempFile)
}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}