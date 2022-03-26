package com.kingfishapps.views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.kingfishapps.fx.MyApplication
import com.kingfishapps.fx.R
import com.kingfishapps.fx.databinding.FragmentCurrencyBinding
import com.kingfishapps.viewmodel.CurrencyViewModel
import retrofit2.Retrofit
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrencyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrencyFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var currencyValue="USDZAR"
    @Inject lateinit var retrofit: Retrofit;
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: CurrencyViewModel;
    private lateinit var binding: FragmentCurrencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        (activity!!.applicationContext as MyApplication).appComponent.inject(this)

        println("Retrofit Object: ${retrofit}")
        val background:Int=resources.getColor(R.color.white)
        viewModel.getCurrency(binding.getTheGraph,background,currencyValue,retrofit);
        viewModel.getCurrentExRate(currencyValue,binding.currentEx,retrofit);

        val fromSpinner=binding.fromSpinner;
        //val toSpinner=binding.toSpinner;

        ArrayAdapter.createFromResource(
            context!!,
            R.array.fromCurrency_array,
            android.R.layout.simple_spinner_item
        ).also { adapterr ->
            // Specify the layout to use when the list of choices appears
            adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            fromSpinner.adapter = adapterr
        }
        fromSpinner.id=1;
        fromSpinner.onItemSelectedListener = this

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        parent!!.getItemAtPosition(pos)
        val selected_curency_name=parent.getItemAtPosition(pos);
        currencyValue="USD${selected_curency_name}"
        val background:Int=resources.getColor(R.color.white)
        viewModel.getCurrency(binding.getTheGraph,background,currencyValue,retrofit);
        viewModel.getCurrentExRate(currencyValue,binding.currentEx,retrofit);
        println("Clicked On ${parent.getItemAtPosition(pos)}")

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}