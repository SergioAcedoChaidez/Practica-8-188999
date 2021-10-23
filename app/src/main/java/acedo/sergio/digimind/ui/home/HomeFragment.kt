package acedo.sergio.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import acedo.sergio.digimind.R
import acedo.sergio.digimind.databinding.FragmentHomeBinding
import acedo.sergio.digimind.ui.Task
import android.content.Context
import android.content.Intent
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.GridView

class HomeFragment : Fragment() {
    private var adaptador : adaptadorTasks? = null
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


        companion object{

            var tasks  = ArrayList<Task>()
            var first = true
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        if(first) {

            fillTasks()
            first  = false
        }

        adaptador  = adaptadorTasks(root.context, tasks)
        val grid :GridView = root.findViewById(R.id.gridview)
        grid.adapter  = adaptador
        return root
    }
    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Monday","Sunday"),"17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Tuesday","Thursday"),"16:30"))
        tasks.add(Task("Practice 3", arrayListOf("Wedenesday","Sunday"),"15:30"))
        tasks.add(Task("Practice 4", arrayListOf("Thursday","Sunday"),"14:30"))
        tasks.add(Task("Practice 5", arrayListOf("Friday","Sunday"),"13:30"))
        tasks.add(Task("Practice 6", arrayListOf("Sunday","Sunday"),"12:30"))


    }
    private class adaptadorTasks: BaseAdapter{
        var tasks  = ArrayList<Task>()
        var contexto : Context? = null

    constructor(contexto:Context , tasks: ArrayList<Task>){
        this.contexto= contexto
        this.tasks = tasks
    }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.task_view, null)

            var tv_title: TextView= vista.findViewById(R.id.tv_title)
            var tv_time: TextView= vista.findViewById(R.id.tv_time)
            var tv_days: TextView= vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}