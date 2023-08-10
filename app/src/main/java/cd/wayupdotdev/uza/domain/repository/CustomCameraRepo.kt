package cd.wayupdotdev.uza.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CustomCameraRepo {

    suspend fun captureAndSaveImage(context:Context,onCapture : (Uri) -> Unit)
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )
    fun getImageUri(): Uri
}