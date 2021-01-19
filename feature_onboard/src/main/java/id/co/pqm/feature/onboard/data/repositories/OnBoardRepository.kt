package id.co.pqm.feature.onboard.data.repositories

import id.co.pqm.lib.data.model.Payload

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
interface OnBoardRepository {

    fun isOnBoardAvailable(): Payload<Boolean>

    fun finishOnBoard(): Payload<Boolean>

}