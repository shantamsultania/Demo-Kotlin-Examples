package com.example.camerausingopencv

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import com.example.camerausingopencv.webview
import kotlinx.android.synthetic.main.activity_webview2.*

class webview2 : AppCompatActivity() {
    private val url = "https://github.com/shantamsultania/"
    private var isAlreadyCreated = false
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview2)
        startLoaderAnimation()

        webview.settings.javaScriptEnabled = true
        webview.settings.setSupportZoom(false)

        webview.webViewClient =  object : WebViewClient()
        {
            override fun onPageFinished(view: WebView?, url: String?) {
                endLoaderAnimatetion()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                endLoaderAnimatetion()
                showError("Error","please check your internet connection",this@webview2)
            }
        }
        webview.loadUrl(url)
    }

    override fun onResume() {
        super.onResume()

        if (isAlreadyCreated && !isNetworkAvailable()) {
            isAlreadyCreated = false
            showError("Error", "No internet connection. Please check your connection.",
                this)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectionManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }



    private fun showError(s: String, msg: String, context : Context) {

        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(s)
        dialog.setMessage(msg)
        dialog.setNegativeButton("Cancel", { _, _ ->
            this.finish()
        })
        dialog.setNeutralButton("Settings", {_, _ ->
            startActivity(Intent(Settings.ACTION_SETTINGS))
        })
        dialog.setPositiveButton("Retry", { _, _ ->
            this.recreate()
        })
        dialog.create().show()

    }

    private fun startLoaderAnimation()
    {
        val objectAnimation = object : Animation()
        {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val startheight = 170
                val newheight = (startheight * (startheight + 40) * interpolatedTime).toInt()
                imageview.layoutParams.height = newheight
                imageview.requestLayout()
            }

            override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
                super.initialize(width, height, parentWidth, parentHeight)
            }

            override fun willChangeBounds(): Boolean {
                return true
            }

        }
        objectAnimation.repeatCount = -1
        objectAnimation.repeatMode = ValueAnimator.REVERSE
        objectAnimation.duration = 1000
        imageview.startAnimation(objectAnimation)
    }

    private fun endLoaderAnimatetion()
    {
        imageview.clearAnimation()
        imageview.visibility = View.GONE
    }
}



