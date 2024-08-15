package dts.pnj.pendataanalumni.data.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ArticlesResponse implements Parcelable {

	@SerializedName("next")
	private String next;

	@SerializedName("previous")
	private Object previous;

	@SerializedName("count")
	private int count;

	@SerializedName("results")
	private List<ResultsItem> results;

	protected ArticlesResponse(Parcel in) {
		next = in.readString();
		previous = in.readValue(Object.class.getClassLoader());
		count = in.readInt();
		results = in.createTypedArrayList(ResultsItem.CREATOR);
	}

	public static final Creator<ArticlesResponse> CREATOR = new Creator<ArticlesResponse>() {
		@Override
		public ArticlesResponse createFromParcel(Parcel in) {
			return new ArticlesResponse(in);
		}

		@Override
		public ArticlesResponse[] newArray(int size) {
			return new ArticlesResponse[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(next);
		dest.writeValue(previous);
		dest.writeInt(count);
		dest.writeTypedList(results);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setPrevious(Object previous){
		this.previous = previous;
	}

	public Object getPrevious(){
		return previous;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}
}
