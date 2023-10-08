package com.example.sanatoriy.bussiness.СalendarOfMenu.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.example.sanatoriy.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val path = "http://31.129.103.95:8111/4de980d4-36e5-4eb4-9ebb-6fa19b4feefb.jpg"

class CalendarFragment : Fragment() {

    lateinit var calendar : CalendarView
    lateinit var createMenuBt: Button
    lateinit var checkMenuBt: Button
    lateinit var cli: calendar_interface
    var textCheckMenu : String = "aboba"

    override fun onAttach(context: Context) {
        cli = (context) as calendar_interface
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendar = view.findViewById<CalendarView>(R.id.calendarView)
        createMenuBt = view.findViewById<Button>(R.id.createMenuButton)
        checkMenuBt = view.findViewById<Button>(R.id.checkMenuButton)
        initDateZakazMenu()
        val dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        val tomorrow = LocalDate.now()
        val formattedTomorrow = tomorrow.format(dtf)
        initDateCheckMenu(calendar.date, formattedTomorrow)
        calendar.setOnDateChangeListener { calView, year, month, dayOfMonth ->
            textCheckMenu = "$dayOfMonth/${month + 1}/$year"
            //val timeZone = TimeZone.getTimeZone("Asia/Calcutta");
            var calender: Calendar = Calendar.getInstance()
            // Set attributes in calender object as per selected date.
            calender.set(year, month, dayOfMonth)
            // Now set calenderView with this calender object to highlight selected date on UI.
            calView.setDate(calender.timeInMillis, true, true)
            initDateCheckMenu(calendar.date, textCheckMenu)
        }
        view.findViewById<Button>(R.id.createMenuButton).setOnClickListener { view ->
           cli.setCreateMenuActivity()
        }

        view.findViewById<Button>(R.id.checkMenuButton).setOnClickListener { view ->
            cli.setListOfPlacesFragment()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun initDateZakazMenu(){
        val df: DateFormat = SimpleDateFormat("dd/MM/yyy")
        val dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
        val minDate: Long = df.parse("20/09/2023")!!.time
        val maxDate:Long = df.parse("30/12/2023")!!.time
        calendar.minDate = minDate
        calendar.maxDate = maxDate
        val tomorrow = LocalDate.now().plusDays(1)//функция возвращает дату а нам нужно на день вперёд       //  val tomorrow = LocalDate.now()
        val formattedTomorrow = tomorrow.format(dtf)
        val d1806 = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        createMenuBt.text = "Сделать заказ на $formattedTomorrow"
        val zakazMenuDate = tomorrow.format(d1806)
//        val zakazMenuDate = LocalDate.now().atStartOfDay().atOffset(ZoneOffset.UTC).format(d1806)
        cli.setZakazMenuDate(zakazMenuDate)

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun initDateCheckMenu(time: Long, dateText:String){
//        val dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH:mm:ssX");
//        var day = Date(time)
//        SimpleDateFormat(day.toString())
//        val dateString = "2011-08-12T20:17:46.384Z"
//        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        val date = dateFormat.parse(dateString)

        val formater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        formater.timeZone = TimeZone.getTimeZone("UTC");
        var date2 = formater.format(Date(time))
        cli.setCheckMenuDate(date2)
        checkMenuBt.text = "Просмотреть меню на $dateText"

//        formater.timeZone = TimeZone.getTimeZone("Europe/Moscow");
        //var date = formater.format(time)
//        var day2 = SimpleDateFormat(day.toString())
//        var data = day2.format(dtf)
//        val tomorrow = LocalDate.now().plusDays(1)//функция возвращает дату а нам нужно на день вперёд       //  val tomorrow = LocalDate.now()
//        val dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssX");
//        val data= tomorrow.atStartOfDay().atOffset(ZoneOffset.UTC).format(dtf);


    }


}