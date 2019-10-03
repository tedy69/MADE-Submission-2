package tech.tedybearblog.tablayout.show;

import android.os.Parcel;
import android.os.Parcelable;

public class Show implements Parcelable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    private String from;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    private int photo;

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    private int poster;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    private String type;
    private String genre;
    private String language;
    private String runtime;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.from);
        dest.writeString(this.content);
        dest.writeInt(this.photo);
        dest.writeInt(this.poster);
        dest.writeString(this.language);
        dest.writeString(this.runtime);
        dest.writeString(this.type);
        dest.writeString(this.genre);
    }


    public Show() {
    }

    protected Show(Parcel in) {
        this.name = in.readString();
        this.from = in.readString();
        this.content = in.readString();
        this.photo = in.readInt();
        this.poster = in.readInt();
        this.language = in.readString();
        this.runtime = in.readString();
        this.type = in.readString();
        this.genre = in.readString();
    }

    public static final Creator<Show> CREATOR = new Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
