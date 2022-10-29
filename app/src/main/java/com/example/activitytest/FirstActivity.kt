package com.example.activitytest

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.activitytest.ui.login.LoginActivity

@Suppress("DEPRECATION")
class FirstActivity : AppCompatActivity() {
    // 要申请的权限
    private val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS,
        android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
    )


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 321) {
            for (i in permissions.indices) {
                if (grantResults[i] !=PackageManager.PERMISSION_GRANTED) {
                    Log.d("zzp", "没有获取权限" + permissions[i])
                } else {
                    Log.d("zzp", "获取权限成功" + permissions[i])
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val i = ContextCompat.checkSelfPermission(applicationContext, permissions[0])
        val j = ContextCompat.checkSelfPermission(applicationContext, permissions[1])
        val k = ContextCompat.checkSelfPermission(applicationContext, permissions[2])
        val l = ContextCompat.checkSelfPermission(applicationContext, permissions[3])
        val z = ContextCompat.checkSelfPermission(applicationContext, permissions[4])


        if (i != PackageManager.PERMISSION_GRANTED || j != PackageManager.PERMISSION_GRANTED ||
            k != PackageManager.PERMISSION_GRANTED || l != PackageManager.PERMISSION_GRANTED ||
            z!=  PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 321)
        }

        val button1:Button = findViewById(R.id.button1)
        val baidu:Button = findViewById(R.id.baidu)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        val button4:Button = findViewById(R.id.button4)

        button3.setOnClickListener {
            val intent = Intent(this, CountActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        /*
        button1.setOnClickListener {    //修改，因为需要双项匹配
            val intent = Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            startActivity(intent)
        }
        */
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
        baidu.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
            R.id.login -> Toast.makeText(this, "Trying to login", Toast.LENGTH_LONG).show()
            R.id.quit -> finish()
        }
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is $returnedData")
            }
        }
    }
}