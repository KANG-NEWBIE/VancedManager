package com.vanced.manager.ui.fragments

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.vanced.manager.R
import com.vanced.manager.utils.InternetTools
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MicrogChangelogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_microg_changelog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking {
            launch {
                val changelogTxt = view.findViewById<TextView>(R.id.microg_changelog)

                var baseUrl = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString("install_url", InternetTools.baseUrl)
                baseUrl = baseUrl?.trimEnd('/')

                var changelog = InternetTools.getObjectFromJson("$baseUrl/microg.json", "changelog");
                changelogTxt.text = changelog
            }
        }
    }
}
