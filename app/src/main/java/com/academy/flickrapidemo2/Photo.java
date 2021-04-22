package com.academy.flickrapidemo2;

import android.os.Parcel;
import android.os.Parcelable;

class Photo implements Parcelable {
    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mTags;
    //stores url of small image
    private String mImage;
    //stores url of big image
    private String mLink;

    public Photo(String mTitle, String mAuthor, String mAuthorId, String mTags, String mImage, String mLink) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAuthorId = mAuthorId;
        this.mTags = mTags;
        this.mImage = mImage;
        this.mLink = mLink;
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {

        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Photo(Parcel in) {
        this.mLink = in.readString();
        this.mTitle = in.readString();
        this.mAuthor = in.readString();
        this.mTags = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLink);
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
        dest.writeString(mTags);
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    String getAuthor() {
        return mAuthor;
    }

    void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    String getAuthorId() {
        return mAuthorId;
    }

    void setAuthorId(String mAuthorId) {
        this.mAuthorId = mAuthorId;
    }

    String getTags() {
        return mTags;
    }

    void setTags(String mTags) {
        this.mTags = mTags;
    }

    String getImage() {
        return mImage;
    }

    void setImage(String mImage) {
        this.mImage = mImage;
    }

   String getLink() {
        return mLink;
    }

   void setLink(String mLink) {
        this.mLink = mLink;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorId='" + mAuthorId + '\'' +
                ", mTags='" + mTags + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mLink='" + mLink + '\'' +
                '}';
    }
}
