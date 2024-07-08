package features.pin

import Strings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PinViewModel(
    val biometryAuthenticator: BiometryAuthenticator,
) : ViewModel() {
    private val _biometricResult = MutableStateFlow<BiometricResult?>(null)
    val biometricResult = _biometricResult.asStateFlow()

    fun tryToAuth() = viewModelScope.launch {
        try {
            val isSuccess = biometryAuthenticator.checkBiometryAuthentication(
                requestTitle = Strings.Pin.BiometricTitle.desc(),
                requestReason = Strings.Pin.BiometricDescription.desc(),
                failureButtonText = Strings.Pin.BiometricCancel.desc(),
                allowDeviceCredentials = false
            )

            if (isSuccess) {
                _biometricResult.value = BiometricResult.Success
            }
        } catch (throwable: Throwable) {
            _biometricResult.value = BiometricResult.Failure
        }
    }
}

enum class BiometricResult {
    Success,
    Failure
}
