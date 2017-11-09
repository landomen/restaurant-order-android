package si.lanisnik.restaurantorder.ui.address

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import kotlinx.android.synthetic.main.activity_addresses.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.address.adapter.AddressRecyclerAdapter
import si.lanisnik.restaurantorder.ui.address.dialog.AddAddressDialogFragment
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import javax.inject.Inject


class AddressesActivity : BaseActivity(),
        AddressRecyclerAdapter.AddressListener,
        LoadingStateView.RetryListener,
        AddAddressDialogFragment.AddAddressDialogListener {

    companion object {
        private const val REQUEST_CODE_PLACE_AUTOCOMPLETE = 1975
    }

    @Inject lateinit var viewModelFactory: AddressViewModelFactory
    @Inject lateinit var adapter: AddressRecyclerAdapter
    private lateinit var viewModel: AddressViewModel

    override fun getContentView(): Int = R.layout.activity_addresses

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.addresses_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initUi() {
        setupRecyclerView()
        addressesLoadingStateView.retryListener = this
        addressesAddFab.setOnClickListener {
            openAddressChooser()
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {
        viewModel.getObservable().observe(this, Observer {
            it?.let {
                handleDataState(it)
            }
        })
        viewModel.getUpdateObservable().observe(this, Observer {
            handleUpdateState(it!!)
        })
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onAddressSelected(addressId: Int) {
        viewModel.selectAddress(addressId)
    }

    override fun onAddressDelete(addressId: Int) {
        viewModel.deleteAddress(addressId)
    }

    override fun onAddressCreated(address: String, note: String, default: Boolean) {
        viewModel.addAddress(address, note, default)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PLACE_AUTOCOMPLETE && resultCode == Activity.RESULT_OK) {
            val place = PlaceAutocomplete.getPlace(this, data)
            AddAddressDialogFragment.show(this, place.address.toString())
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setupRecyclerView() {
        adapter.listener = this
        addressesRecyclerView.adapter = adapter
        addressesRecyclerView.enableItemDividers()
    }

    private fun openAddressChooser() {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(AutocompleteFilter.Builder()
                            .setCountry("SI")
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                            .build())
                    .build(this)
            startActivityForResult(intent, REQUEST_CODE_PLACE_AUTOCOMPLETE)
        } catch (e: GooglePlayServicesRepairableException) {
            addressesAddFab.snackbar(R.string.error_general)
        } catch (e: GooglePlayServicesNotAvailableException) {
            addressesAddFab.snackbar(R.string.error_general)
        }
    }

    // region Initial loading

    private fun handleDataState(resource: Resource<List<AddressModel>>) {
        when (resource.status) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showAddresses(resource.data!!)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showAddresses(addresses: List<AddressModel>) {
        if (addresses.isNotNullAndEmpty()) {
            adapter.addresses = addresses.toMutableList()
            addressesRecyclerView.show()
            addressesLoadingStateView.hide()
        } else {
            showLoadingState(LoadingStateView.State.EMPTY)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        addressesLoadingStateView.state = state
        addressesLoadingStateView.show()
        addressesRecyclerView.hide()
    }

    // endregion

    // region Action loading

    private fun handleUpdateState(state: SimpleResource) {
        when (state.status) {
            ResourceState.LOADING -> showUpdateLoadingState()
            ResourceState.SUCCESS -> showUpdateSuccessState()
            ResourceState.ERROR -> showUpdateErrorState(state.errorMessage!!)
        }
    }

    private fun showUpdateLoadingState() {
        showLoadingDialog()
    }

    private fun showUpdateSuccessState() {
        hideLoadingDialog()
    }

    private fun showUpdateErrorState(errorMessage: Int) {
        hideLoadingDialog()
        addressesRecyclerView.snackbar(errorMessage)
    }

    // endregion
}