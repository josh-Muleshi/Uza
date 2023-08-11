package cd.wayupdotdev.uza.ui.screen.onboarding.business

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingviewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    fun isOnboardingShow(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("is-show", value)
        }.apply()
    }
}