package com.example.webmasters.models.shared;

import androidx.databinding.BaseObservable;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;

public class ObservableLiveData<T extends BaseObservable> extends MutableLiveData<T> {

    public ObservableLiveData(T initialValue) {
        super(initialValue);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        //listen to property changes
        value.addOnPropertyChangedCallback(callback);
    }

    Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            //Trigger LiveData observer on change of any property in object
            setValue(getValue());
        }
    };


}
