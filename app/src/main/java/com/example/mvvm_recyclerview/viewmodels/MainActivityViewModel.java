package com.example.mvvm_recyclerview.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_recyclerview.models.NicePlace;
import com.example.mvvm_recyclerview.repositories.NicePlaceRepo;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> mutableLiveData;
    private NicePlaceRepo nicePlaceRepo;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public  void addNewValue(final NicePlace nicePlace){
        isUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mutableLiveData.getValue();
                currentPlaces.add(nicePlace);
                mutableLiveData.postValue(currentPlaces);
                isUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public LiveData<Boolean> getIsUpdating(){
        return  isUpdating;
    }
    public LiveData<List<NicePlace>> getNicePlaces(){
        return mutableLiveData;
    }

    public void init(){
        if(mutableLiveData != null)
            return;
        nicePlaceRepo = NicePlaceRepo.getInstance();
        mutableLiveData = nicePlaceRepo.getNicePlace();
    }
}
