package com.academy.flickrapidemo2;

class Photo {
    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mTags;
    private String mImage;
    private String mLink;

    public Photo(String mTitle, String mAuthor, String mAuthorId, String mTags, String mImage, String mLink) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mAuthorId = mAuthorId;
        this.mTags = mTags;
        this.mImage = mImage;
        this.mLink = mLink;
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
