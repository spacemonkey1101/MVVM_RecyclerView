package com.example.mvvm_recyclerview.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_recyclerview.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

// singleton pattern
public class NicePlaceRepo {
    private static NicePlaceRepo instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepo getInstance(){
        if(instance == null){
            instance = new NicePlaceRepo();
        }
        return instance;
    }

    //assuming we are retrieving data from an api/web resources
    public MutableLiveData<List<NicePlace>> getNicePlace(){
        setNicePlaces();
        MutableLiveData<List<NicePlace>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(dataSet);
        return mutableLiveData;
    }

    private void  setNicePlaces(){
        dataSet.add(
                new NicePlace("Havasu Falls","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"
                        )
        );
        dataSet.add(
                new NicePlace("Trondheim","https://i.redd.it/tpsnoz5bzo501.jpg"
                        )
        );
        dataSet.add(
                new NicePlace("Portugal","https://i.redd.it/qn7f9oqu7o501.jpg"
                        )
        );
        dataSet.add(
                new NicePlace("Rocky Mountain National Park","https://i.redd.it/j6myfqglup501.jpg"
                        )
        );
        dataSet.add(
                new NicePlace(
                        "Havasu Falls","https://i.redd.it/0h2gm1ix6p501.jpg")
        );
        dataSet.add(
                new NicePlace("Mahahual","https://i.redd.it/k98uzl68eh501.jpg"
                        )
        );
        dataSet.add(
                new NicePlace("Frozen Lake","https://files.tripstodiscover.com/files/2017/12/havasu-falls-grand-canyon-a.jpg"
                        )
        );
        dataSet.add(
                new NicePlace("Austrailia","https://i.redd.it/obx4zydshg601.jpg"
                        )
        );
    }
}
