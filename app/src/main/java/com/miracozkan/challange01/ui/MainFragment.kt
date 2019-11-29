package com.miracozkan.challange01.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miracozkan.challange01.R
import com.miracozkan.challange01.adapter.DataListAdapter
import com.miracozkan.challange01.datalayer.local.ProjectDatabase
import com.miracozkan.challange01.utils.DependencyUtil
import com.miracozkan.challange01.utils.ViewModelFactory
import com.miracozkan.challange01.vm.MainViewModel
import kotlinx.android.synthetic.main.dialog_bankers.view.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), SearchView.OnQueryTextListener, View.OnClickListener {

    //TODO UI
    //TODO timeFilter

    private val dialogBuilder by lazy { AlertDialog.Builder(activity) }
    private val dialog by lazy { dialogBuilder.create() }

    private var maxValue = -1
    private var bankerBarAmount: Int = -1
    private var isAlphabet: Boolean = false
    private var newText: String = ""

    private val mainRepository by lazy {
        DependencyUtil.getMainRepository(
            ProjectDatabase.getInstance(
                context!!
            ).projectDao()
        )
    }

    private val mainViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(recycItemList) {
            layoutManager = LinearLayoutManager(context!!)
            adapter = DataListAdapter {

            }
        }
        runObserver()
        setNewText()
        setMaxValue()
        srcView.setOnQueryTextListener(this)
        fltAlphaOrDate.setOnClickListener(this)
        fltBankerRange.setOnClickListener(this)
    }

    private fun setMaxValue() {
        mainViewModel.getMaxValueFromDb.observe(this, Observer {
            maxValue = it
        })
    }

    private fun setNewText() {
        mainViewModel.filteredText.observe(this, Observer {
            newText = it
        })
    }

    private fun runObserver() {
        mainViewModel.fetchList?.observe(this, Observer {
            (recycItemList.adapter as DataListAdapter).submitList(it)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mainViewModel.triggerSource(newText ?: "", isAlphabet, bankerBarAmount)
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fltAlphaOrDate -> {
                isAlphabet = !isAlphabet
                mainViewModel.triggerSource(newText, isAlphabet, bankerBarAmount)
            }
            R.id.fltBankerRange -> {
                initDialog()
                setDialogButtons()
            }
        }
    }

    private fun initDialog() {
        val screen = layoutInflater.inflate(R.layout.dialog_bankers, null, false)
        dialogBuilder.setView(screen)
        val seekBar = screen.skBankers
        screen.txtSize.text = bankerBarAmount.toString()
        seekBar.progress = bankerBarAmount
        seekBar.max = maxValue

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                bankerBarAmount = progress
                screen.txtSize.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setDialogButtons() {
        dialog.setButton(
            DialogInterface.BUTTON_NEGATIVE, "No"
        ) { _, _ ->
            dialog.cancel()
        }
        dialog.setButton(
            DialogInterface.BUTTON_POSITIVE, "Yes"
        ) { _, _ ->
            mainViewModel.triggerSource(
                bankersSize = bankerBarAmount,
                state = isAlphabet,
                text = newText
            )
        }
        dialog.show()
    }
}
