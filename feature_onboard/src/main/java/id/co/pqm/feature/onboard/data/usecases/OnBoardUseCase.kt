package id.co.pqm.feature.onboard.data.usecases

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
interface OnBoardUseCase {

    fun isOnBoardAvailable(): Boolean

    fun finishOnBoard(): Boolean

}