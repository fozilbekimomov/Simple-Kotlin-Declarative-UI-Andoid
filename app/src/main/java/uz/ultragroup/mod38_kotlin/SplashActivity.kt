package uz.ultragroup.mod38_kotlin

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.animation.doOnEnd
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)


        setContentView(createRootView())

        createLoadingText()

        rootLayout?.addView(loadingText)

        createProgressBar()

        rootLayout?.addView(progressBar)

        startAnimator()

        hideSystemUI()


    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, rootLayout!!).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


    }


    var rootLayout: ConstraintLayout? = null
    fun createRootView(): ConstraintLayout? {

        val rootParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        rootLayout = ConstraintLayout(this)
        rootLayout?.layoutParams = rootParam

        rootLayout?.id = splashRootID

        val backImageView = AppCompatImageView(this)
        backImageView.layoutParams = rootParam
        backImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        backImageView.setImageResource(R.drawable.start_bg)

        rootLayout?.addView(backImageView)


        return rootLayout
    }

    var loadingText: AppCompatTextView? = null
    fun createLoadingText(): AppCompatTextView? {

        val textParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        val textSize = resources.getDimension(R.dimen.progress_text_size)

        loadingText = AppCompatTextView(ContextThemeWrapper(this, R.style.LibreFranklinBlack900))
        loadingText?.id = splashPogressTextView
        loadingText?.layoutParams = textParam
        loadingText?.text = "LOADING"
        loadingText?.textSize = textSize
        loadingText?.setTextColor(Color.WHITE)

        textParam.topToTop = ConstraintSet.PARENT_ID
        textParam.bottomToBottom = ConstraintSet.PARENT_ID
        textParam.startToStart = ConstraintSet.PARENT_ID
        textParam.endToEnd = ConstraintSet.PARENT_ID

        textParam.verticalBias = 0.2f

        return loadingText
    }

    var progressBar: ProgressBar? = null
    fun createProgressBar(): ProgressBar? {

        val height = resources.getDimension(R.dimen.progress_height_size)

        val progressParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        progressBar = ProgressBar(
            this,
            null,
            android.R.attr.progressBarStyleHorizontal
        )
        progressBar?.id = splashProgressID

//        progressParam.height = height.toInt()
        progressParam.height = 0

        val topMargin = resources.getDimension(R.dimen.progress_top_margin)
        val startMargin = resources.getDimension(R.dimen.progress_start_margin)
        val endMargin = resources.getDimension(R.dimen.progress_end_margin)


        progressParam.topMargin = topMargin.toInt()
        progressParam.marginStart = startMargin.toInt()
        progressParam.marginEnd = endMargin.toInt()

        progressParam.topToBottom = splashPogressTextView

        val progressColor = Color.parseColor("#FC36F6")

        val roundedCorners = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        val pgDrawable = ShapeDrawable(RoundRectShape(roundedCorners, null, null))

        pgDrawable.paint.color = progressColor

        val progress = ClipDrawable(pgDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL)

        progressBar?.progressDrawable = progress

        progressBar?.setBackgroundColor(Color.parseColor("#105705"))


        progressParam.dimensionRatio = "138:4"

        progressBar?.layoutParams = progressParam

        return progressBar
    }

    private var animator: ValueAnimator? = null

    private val timeProgress = 3_000L

    fun startAnimator() {
        animator?.cancel()
        animator = null
        animator = ValueAnimator.ofInt(1, 101).apply {
            this.duration = timeProgress

            addUpdateListener {
                val persentage = (it.animatedFraction * 100).toInt() % 100
                progressBar?.progress = persentage
            }

            doOnEnd {
                progressBar?.progress = 100
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                startActivity(Intent(this@SplashActivity, MainActivity1::class.java))
                finish()
            }
            start()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        animator?.cancel()
        animator = null
    }

}