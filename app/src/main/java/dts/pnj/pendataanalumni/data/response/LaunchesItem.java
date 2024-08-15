package dts.pnj.pendataanalumni.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LaunchesItem implements Parcelable {

	@SerializedName("launch_id")
	private String launchId;

	@SerializedName("provider")
	private String provider;

	protected LaunchesItem(Parcel in) {
		launchId = in.readString();
		provider = in.readString();
	}

	public static final Creator<LaunchesItem> CREATOR = new Creator<LaunchesItem>() {
		@Override
		public LaunchesItem createFromParcel(Parcel in) {
			return new LaunchesItem(in);
		}

		@Override
		public LaunchesItem[] newArray(int size) {
			return new LaunchesItem[size];
		}
	};

	public void setLaunchId(String launchId){
		this.launchId = launchId;
	}

	public String getLaunchId(){
		return launchId;
	}

	public void setProvider(String provider){
		this.provider = provider;
	}

	public String getProvider(){
		return provider;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(launchId);
		dest.writeString(provider);
	}
}