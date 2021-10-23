package acedo.sergio.digimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import acedo.sergio.digimind.R
import acedo.sergio.digimind.databinding.FragmentDashboardBinding
import acedo.sergio.digimind.ui.Task
import acedo.sergio.digimind.ui.home.HomeFragment
import android.app.TimePickerDialog
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener{
            val cal  = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE), true).show()
        }

        val btn_save: Button = root.findViewById(R.id.btn_save)


            btn_save.setOnClickListener{
                var days =ArrayList<String>()
                var title = et_titulo.text.toString()
                var time = btn_time.text.toString()

                if(checkMonday.isChecked)
                    days.add("Monday")
                if(checkTuesday.isChecked)
                    days.add("Tuesday")
                if(checkWednesday.isChecked)
                    days.add("Wednesday")
                if(checkThursday.isChecked)
                    days.add("Thursday")
                if(checkFriday.isChecked)
                    days.add("Friday")
                if(checkSaturday.isChecked)
                    days.add("Saturday")
                if(checkSunday.isChecked)
                    days.add("Sunday")

                var task = Task(title,days,time)
                HomeFragment.tasks.add(task)
                Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()

            }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}