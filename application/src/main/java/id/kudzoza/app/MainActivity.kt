package id.kudzoza.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.kudzoza.feature.dummy.screen.onboard.OnBoardActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = GlobalScope.launch {
            delay(800)
            startActivity(Intent(this@MainActivity, OnBoardActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
