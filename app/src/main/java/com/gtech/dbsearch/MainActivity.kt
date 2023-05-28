package com.gtech.dbsearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.gtech.dbsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mSearchBtn: Button
    lateinit var mInput: EditText
    lateinit var mOperator: TextView
    lateinit var mFullName: TextView
    lateinit var mAddress: TextView
    lateinit var mMobile: TextView
    lateinit var mActivationDate: TextView
lateinit var mFireStore: FirebaseFirestore
    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
mFireStore= FirebaseFirestore.getInstance()
//        setSupportActionBar(binding.toolbar)

        mSearchBtn = binding.searchBtn
        mInput = binding.mobileInput
        mOperator = binding.operator
        mFullName = binding.fullName
        mAddress = binding.address
        mActivationDate = binding.activationDate
        mMobile = binding.mobile

        mSearchBtn.setOnClickListener {
            Toast.makeText(this, mInput.text.toString(), Toast.LENGTH_SHORT).show()
  getData(mInput.text.toString())
        }
//
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }


    private fun getData(inputvalue: String) {
     mFireStore.collection("TestData").document(inputvalue)
         .get().addOnSuccessListener { doc->
         if(doc==null) Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
    if(doc!=null){
        val data = doc.toObject(ContactModel::class.java)
//        val data = doc
        Log.d(TAG, "getData: ${data?.fullName}")
        mFullName.setText(data?.fullName)
        mOperator.setText(data?.operator)
        mAddress.setText(data?.address)
        mMobile.setText(data?.mobile)
    }
     }
    }








    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}