package com.application.anufriev.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.anufriev.R
import com.application.anufriev.utils.AnimationHelper.AnimationHelper


import kotlinx.android.synthetic.main.fragment_watch_leter.*

class WatchLeterFragment : Fragment() {

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {

    return inflater.inflate(R.layout.fragment_watch_leter, container, false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(watch_leter_fragment_root, requireActivity(), 2)

    }
}