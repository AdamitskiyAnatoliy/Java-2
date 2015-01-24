package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Anatoliy on 1/20/15.
 */
public class Person implements Parcelable, Serializable {

    private String name;
    private String number;
    private String position;

    public Person (String _name, String _number, String _position) {
        name = _name;
        number = _number;
        position = _position;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public Person(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.name = data[0];
        this.number = data[1];
        this.position = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.number,
                this.position});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

}
