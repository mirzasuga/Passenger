package com.stickearn.stickpass.view.edit_profile

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.ProfileResponseMdl

/**
 * Created by macos-vanya on 09/02/18.
 */
interface EditProfileView:BaseView {
    fun onEditProfileSuccess(result: ProfileResponseMdl)
}