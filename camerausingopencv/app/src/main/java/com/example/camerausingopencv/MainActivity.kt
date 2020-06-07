package com.example.camerausingopencv

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.JavaCameraView
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc


class MainActivity : AppCompatActivity(),CameraBridgeViewBase.CvCameraViewListener2 {
    var TAG:String = "message"
    lateinit var cameraBridgeViewBase: CameraBridgeViewBase
    lateinit var baseLoaderCallback: BaseLoaderCallback
    var startedge = false
    var startgray = false
    var startcolour = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraBridgeViewBase = findViewById<JavaCameraView>(R.id.cameraview)
        cameraBridgeViewBase.visibility = SurfaceView.VISIBLE
        cameraBridgeViewBase.setCvCameraViewListener(this)
        baseLoaderCallback = object : BaseLoaderCallback(this) {
            override fun onManagerConnected(status: Int) {
                super.onManagerConnected(status)
                when (status) {
                    SUCCESS -> cameraBridgeViewBase.enableView()
                    else -> super.onManagerConnected(status)
                }
            }
        }
        gray_button.setOnClickListener {
            gray()
            Snackbar.make(it, "Lets check out Gray Camera ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        edge_button.setOnClickListener {
            edge()
            Snackbar.make(it, "Lets check out Edge detection ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        colour_button.setOnClickListener {
            colour()
            Snackbar.make(it, "Lets check out Red to Blue", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



    }


    override fun onCameraViewStarted(width: Int, height: Int) {
    }

    override fun onCameraViewStopped() {
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        val frame = inputFrame!!.rgba()
        // to make a gray video
        if (startgray == true)
        {
            Imgproc.cvtColor(frame,frame,Imgproc.COLOR_BGR2GRAY)
        }
        else if (startedge == true)
        {
            Imgproc.cvtColor(frame,frame,Imgproc.COLOR_BGR2GRAY)
            Imgproc.Canny(frame,frame,100.0,80.0)

        }
        else if (startcolour == true){
            Imgproc.cvtColor(frame,frame,Imgproc.COLOR_BGR2RGB)
        }

        return frame;
    }

    override fun onResume() {
        super.onResume()
        if (OpenCVLoader.initDebug()) {
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
            Log.e(TAG, "yes")

        } else {
            Log.e(TAG, "no")
        }
    }

    override fun onPause() {
        super.onPause()
        if(cameraBridgeViewBase!=null){

            cameraBridgeViewBase.disableView();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (cameraBridgeViewBase!=null){
            cameraBridgeViewBase.disableView();
        }
    }
    fun colour()
    {
        if(startcolour == false)
        {
            startcolour = true
            startgray = false
            startedge = false
        }
        else
            false
    }
    fun gray()
    {
        if(startgray == false)
        {
            startgray = true
            startedge = false
            startcolour = false

        }
        else
            false

    }
    fun edge(){
        if(startedge == false)
        {
            startedge = true
            startgray = false
            startcolour = false
        }
        else
            false
    }
}
