package com.luc.common.models

import android.net.Uri
import com.luc.common.ProviderType

data class UserProfile(val uId: String = "", val email: String = "", val photoUri: Uri?, val providerType: ProviderType = ProviderType.BASIC)