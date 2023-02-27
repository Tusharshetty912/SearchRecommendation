package com.example.edittext_search_recommend

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class SearchRecommend{
    companion object{
        private var height = 0
        private var count = 0
        private lateinit var context: Context
        private lateinit var recommendList: Array<String>
        private lateinit var editText: EditText
        private var DELAY_MILLI_SEC: Long = 3000
        private var hint = "Search your need here..."
        fun setInitialEditTextHint(editTextHint: String){
            hint = editTextHint
        }
        fun setDelayForRecommendationInMilliSecs(delayForRecommendationInSecs: Long){
            DELAY_MILLI_SEC = delayForRecommendationInSecs
        }
        fun searchRecommend(cont: Context, eT: EditText, recList: Array<String>){
            editText = eT
            context = cont
            recommendList = recList

            editText.hint = hint
            editText.isCursorVisible = false
            editText.viewTreeObserver.addOnGlobalLayoutListener {
                val imm by lazy { context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
                val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
                height = windowHeightMethod.invoke(imm) as Int
                if (height == 0){
                    if ( editText.text.toString().isEmpty()){
                        animateSearchRecommendations()
                    }
                }else{
                    editText.hint = hint
                    editText.clearAnimation()
                }
            }

            editText.addTextChangedListener {
                if (it.toString().isEmpty()){
                    editText.hint = hint
                }
            }
        }
        private fun animateSearchRecommendations(){
            Handler(Looper.getMainLooper()).postDelayed({
                if (height == 0){
                    val anim1 = AnimationUtils.loadAnimation(context,com.google.android.material.R.anim.abc_slide_out_top)
                    val anim2 = AnimationUtils.loadAnimation(context,com.google.android.material.R.anim.abc_slide_in_bottom)
                    editText.startAnimation(anim1)
                    anim1.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            //
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            editText.hint = "Search \"${recommendList[count]}\""
                            editText.startAnimation(anim2)
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                            //
                        }
                    })

                    anim2.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            //
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            animateSearchRecommendations()
                            count++
                            if (count > 3){
                                count = 0
                            }
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                            //
                        }
                    })
                }
            }, DELAY_MILLI_SEC)
        }
    }
}