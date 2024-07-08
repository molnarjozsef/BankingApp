package features.pin

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

    fun tryToAuth(
        title: String,
        description: String,
        cancelButtonText: String,
    ) = viewModelScope.launch {
        try {
            val isSuccess = biometryAuthenticator.checkBiometryAuthentication(
                requestTitle = title.desc(),
                requestReason = description.desc(),
                failureButtonText = cancelButtonText.desc(),
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
