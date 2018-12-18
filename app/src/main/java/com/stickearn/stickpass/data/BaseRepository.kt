package com.stickearn.stickpass.data

import com.stickearn.stickpass.StickPassAPP
import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.helper.Constant
import com.stickearn.stickpass.helper.StringHelper


/**
 * Created by oohyugi on 1/15/18.
 */
open class BaseRepository{
    var mApiClient: ApiClient = ApiClient(Constant.BASE_URL)
}
