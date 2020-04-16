package training.androidkotlin.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTitle)
        setSupportActionBar(toolbar)

        val homeFragment: HomeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, homeFragment)
            .addToBackStack("Home")
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }
}
