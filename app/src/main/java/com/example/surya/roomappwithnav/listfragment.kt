package com.example.surya.roomappwithnav


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.*
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.surya.roomappwithnav.Adapters.RoomAdap
import com.example.surya.roomappwithnav.DatabaseFile.RoomDatabases
import com.example.surya.roomappwithnav.Modal.Note
import com.example.surya.roomappwithnav.Viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_listfragment.*

class listfragment : Fragment() {

    var Db: RoomDatabases? = null
    lateinit var adapterObj: RoomAdap

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Db = Room.databaseBuilder(this.requireContext(),RoomDatabases::class.java,"note.db").allowMainThreadQueries().build()
        ViewModelCalling(view)
        addNote.setOnClickListener {
            var mArgs =Bundle()
            mArgs.putInt("Edit", 0)

            val options = NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    //.setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                   // .setPopExitAnim(R.anim.slide_out_right)
                    .build()
            Navigation.findNavController(view).navigate(R.id.createnote_fragment,mArgs,options)
        }
    }

    private fun ViewModelCalling(view:View)
    {
        viewModel.fetchAllData().observe(this, object : Observer<MutableList<Note>> {
            override fun onChanged(notelist: MutableList<Note>?) {
                if (notelist != null) {
                        adapterObj = RoomAdap(activity as MainActivity, notelist!!,view)
                        rvRoom?.layoutManager = StaggeredGridLayoutManager(2, 1)
                        rvRoom?.itemAnimator = DefaultItemAnimator()
                        rvRoom?.adapter = adapterObj
                        adapterObj.notifyDataSetChanged()
                }
            }
        })
    }

    fun changeUi()
    {
        adapterObj.notifyDataSetChanged()
    }
}
