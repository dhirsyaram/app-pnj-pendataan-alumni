package dts.pnj.pendataanalumni.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsItem implements Parcelable {

	@SerializedName("summary")
	private String summary;

	@SerializedName("news_site")
	private String newsSite;

	@SerializedName("featured")
	private boolean featured;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("published_at")
	private String publishedAt;

	@SerializedName("url")
	private String url;

	@SerializedName("launches")
	private List<LaunchesItem> launches;

	@SerializedName("events")
	private List<Object> events;

	protected ResultsItem(Parcel in) {
		summary = in.readString();
		newsSite = in.readString();
		featured = in.readByte() != 0;
		updatedAt = in.readString();
		imageUrl = in.readString();
		id = in.readInt();
		title = in.readString();
		publishedAt = in.readString();
		url = in.readString();
		launches = in.createTypedArrayList(LaunchesItem.CREATOR);
		events = in.readArrayList(Object.class.getClassLoader());
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setNewsSite(String newsSite){
		this.newsSite = newsSite;
	}

	public String getNewsSite(){
		return newsSite;
	}

	public void setFeatured(boolean featured){
		this.featured = featured;
	}

	public boolean isFeatured(){
		return featured;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setLaunches(List<LaunchesItem> launches){
		this.launches = launches;
	}

	public List<LaunchesItem> getLaunches(){
		return launches;
	}

	public void setEvents(List<Object> events){
		this.events = events;
	}

	public List<Object> getEvents(){
		return events;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(summary);
		dest.writeString(newsSite);
		dest.writeByte((byte) (featured ? 1 : 0));
		dest.writeString(updatedAt);
		dest.writeString(imageUrl);
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(publishedAt);
		dest.writeString(url);
		dest.writeTypedList(launches);
	}
}