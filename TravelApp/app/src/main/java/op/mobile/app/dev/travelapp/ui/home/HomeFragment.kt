package op.mobile.app.dev.travelapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import op.mobile.app.dev.travelapp.R


class HomeFragment : Fragment() {

    lateinit var bumap: Button
    lateinit var butranslator: Button
    lateinit var buquiz: Button
    lateinit var buphrases: Button
    lateinit var bupolicy: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        bumap = view.findViewById(R.id.but_map)
        butranslator = view.findViewById(R.id.but_translator)
        buquiz = view.findViewById(R.id.but_quiz)
        buphrases = view.findViewById(R.id.but_phrases)
        bupolicy = view.findViewById(R.id.but_policy)


        return view
    }


    }


