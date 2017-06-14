package com.bignerdranch.android.nerdlauncher


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NerdLauncherFragment : Fragment() {

    companion object  {
        fun newInstance() : Fragment {
            return NerdLauncherFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_nerd_launcher, container, false)
        val mRecyclerView = v.findViewById(R.id.app_recycler_view) as RecyclerView
        mRecyclerView.setLayoutManager(LinearLayoutManager(activity))

        // Inflate the layout for this fragment
        return v
    }

}
