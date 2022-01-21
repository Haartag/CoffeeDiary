package com.example.coffeed

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import com.example.coffeed.Constants.FILE_NAME_FORMAT
import com.example.coffeed.Constants.REQUIRED_PERMISSIONS
import com.example.coffeed.Constants.TAG
import com.example.coffeed.databinding.FragmentCoffeePhotoBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CoffeePhotoFragment : Fragment(R.layout.fragment_coffee_photo) {

    private lateinit var binding: FragmentCoffeePhotoBinding
    private var fragmentCoffeePhotoBinding: FragmentCoffeePhotoBinding? = null

    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoffeePhotoBinding.bind(view)
        fragmentCoffeePhotoBinding = binding

        //Default Uri, cause SafeArgs problems with defaults.
        val defaultUri = Uri.parse("android.resource://com.example.coffeed/drawable/coffee_photo")

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()

        requestPermission.launch(REQUIRED_PERMISSIONS)
        startCamera()

        binding.makePhotoButton.setOnClickListener {
            takePhoto()
        }
        binding.skipText.setOnClickListener {
            safeArgsToInputDescriptionFragment(defaultUri)
        }
    }
    //request permission.
    //ToDo: Change it
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            if (isGranted) { // Do something if permission granted
                Log.d("LOG_TAG", "permission granted by the user")

            } else { // Do something as the permission is not granted
                Log.d("LOG_TAG", "permission denied by the user")
            }
        }
    //Get directory to store photos
    private fun getOutputDirectory(): File {
        val mediaDir = activity?.externalMediaDirs?.firstOrNull()?.let { mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else activity?.filesDir!!
    }

    //Take photo, place it in external storage, make Uri and send it via Safe Args
    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILE_NAME_FORMAT,
                Locale.getDefault()
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(outputOption, ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    Toast.makeText(requireContext(), "photo saved in: $savedUri", Toast.LENGTH_LONG).show()
                    safeArgsToInputDescriptionFragment(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.d(TAG, "onError: ${exception.message}", exception)
                }

            })
    }
    //Default CameraX camera start
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d(TAG, "startCamera: Fail because ", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    //pass placeholder Uri to next Fragment
    private fun safeArgsToInputDescriptionFragment(destination: Uri){
        val action = CoffeePhotoFragmentDirections.actionCoffeePhotoFragmentToInputDescriptionFragment(destination)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        fragmentCoffeePhotoBinding = null
        super.onDestroyView()
    }


}