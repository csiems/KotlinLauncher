package com.bignerdranch.android.nerdlauncher


import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NerdLauncherFragment : Fragment() {
    var mRecyclerView: RecyclerView? = null

    companion object  {
        val TAG = "NerdLauncherFragment"
        fun newInstance() : Fragment {
            return NerdLauncherFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_nerd_launcher, container, false)
        mRecyclerView = v.findViewById(R.id.app_recycler_view) as RecyclerView
        mRecyclerView?.setLayoutManager(LinearLayoutManager(activity))

        setUpAdapter()
        // Inflate the layout for this fragment
        return v
    }

    private fun setUpAdapter() {
        val startUpIntent = Intent(Intent.ACTION_MAIN)
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val pm = activity.packageManager
        val activities: List<ResolveInfo> = pm.queryIntentActivities(startUpIntent, 0)
        activities.sortedBy { label: ResolveInfo ->
            label.loadLabel(pm).toString()
        }

        Log.i(TAG, "Found " + activities.size + " activities.")
        mRecyclerView?.setAdapter(ActivityAdapter(activities))
    }

    //A class may be marked as inner to be able to access members of outer class
    private inner class ActivityHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val mNameTextView = itemView as TextView

        fun bindActivity(resolveInfo: ResolveInfo)  {
            val mResolveInfo = resolveInfo
            val pm = activity.packageManager
            val appName = mResolveInfo.loadLabel(pm).toString()
            mNameTextView.setText(appName)
        }
    }

    private inner class ActivityAdapter (activities : List<ResolveInfo>): RecyclerView.Adapter<ActivityHolder>() {
        val mActivities = activities

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActivityHolder {
            val layoutInflater = LayoutInflater.from(activity)
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return ActivityHolder(view)
        }

        override fun onBindViewHolder(holder: ActivityHolder?, position: Int) {
            val resolveInfo = mActivities.get(position)
            holder?.bindActivity(resolveInfo)
        }

        override fun getItemCount(): Int {
            return mActivities.size
        }
    }

}


