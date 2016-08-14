/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.TickerSymbolEntity;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UrlEntity;

/**
 *
 * @author michaelfouche
 */
@Document(collection = "tweetdata")
public class TweetData {
    
    @Id
    private Long tweetId;
    
    private String LoggedInUser;
    private String FromUser;
    private Long FromUserId;
    private Long InReplyToStatusId;
    private Long InReplyToUserId;
    private TwitterProfile User;
    private Date CreatedAt;
    private String LanguageCode;
    private String Text;
    private String InReplyToScreenName;
    private String ProfileImageUrl;
    private String Source;
    private String UnmodifiedText;    
    private int FavoriteCount;
    private Boolean hasMedia;
    private Boolean hasMentions;
    private Boolean hasTags;
    private Boolean hasUrls;
    private int hashCode;
    private Boolean isFavorited;
    private Boolean isRetweeted;
    private Boolean isRetweet;
    private int RetweetCount;
    
    private String letsMineUser;
    private String searchQuery;
    
    private List<UrlEntity> urls;
    private List<HashTagEntity> tags;
    private List<MentionEntity> mentions;
    private List<MediaEntity> media;
    private List<TickerSymbolEntity> tickerSymbols;

    
     public TweetData() {
        //hibernate
    }
    
    public TweetData(Long tweetId, String LoggedInUser, String FromUser, Long FromUserId, Long InReplyToStatusId, Long InReplyToUserId, TwitterProfile User, Date CreatedAt, String LanguageCode, String Text, String InReplyToScreenName, String ProfileImageUrl, String Source, String UnmodifiedText/*, Entities entities*/, int FavoriteCount, Boolean hasMedia, Boolean hasMentions, Boolean hasTags, Boolean hasUrls, int hashCode, Boolean isFavorited, Boolean isRetweeted, Boolean isRetweet, int RetweetCount,String letsMineUser, String searchQuery, List<UrlEntity> urls, List<HashTagEntity> tags,List<MentionEntity> mentions,List<MediaEntity> media,List<TickerSymbolEntity> tickerSymbols) {
        this.tweetId = tweetId;
        this.LoggedInUser = LoggedInUser;
        this.FromUser = FromUser;
        this.FromUserId = FromUserId;
        this.InReplyToStatusId = InReplyToStatusId;
        this.InReplyToUserId = InReplyToUserId;
        this.User = User;
        this.CreatedAt = CreatedAt;
        this.LanguageCode = LanguageCode;
        this.Text = Text;
        this.InReplyToScreenName = InReplyToScreenName;
        this.ProfileImageUrl = ProfileImageUrl;
        this.Source = Source;
        this.UnmodifiedText = UnmodifiedText;
        this.FavoriteCount = FavoriteCount;
        this.hasMedia = hasMedia;
        this.hasMentions = hasMentions;
        this.hasTags = hasTags;
        this.hasUrls = hasUrls;
        this.hashCode = hashCode;
        this.isFavorited = isFavorited;
        this.isRetweeted = isRetweeted;
        this.isRetweet = isRetweet;
        this.RetweetCount = RetweetCount;
        this.letsMineUser = letsMineUser;
        this.searchQuery = searchQuery;
        this.urls = urls;
        this.tags = tags;
        this.mentions = mentions;
        this.media = media;
        this.tickerSymbols = tickerSymbols;
    }

    
    
    public String getLetsMineUser() {
        return letsMineUser;
    }

    public void setLetsMineUser(String letsMineUser) {
        this.letsMineUser = letsMineUser;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<UrlEntity> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlEntity> urls) {
        this.urls = urls;
    }

    public List<HashTagEntity> getTags() {
        return tags;
    }

    public void setTags(List<HashTagEntity> tags) {
        this.tags = tags;
    }

    public List<MentionEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<MentionEntity> mentions) {
        this.mentions = mentions;
    }

    public List<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(List<MediaEntity> media) {
        this.media = media;
    }

    public List<TickerSymbolEntity> getTickerSymbols() {
        return tickerSymbols;
    }

    public void setTickerSymbols(List<TickerSymbolEntity> tickerSymbols) {
        this.tickerSymbols = tickerSymbols;
    }
    
    
    
    public String getLoggedInUser() {
        return LoggedInUser;
    }

    public void setLoggedInUser(String LoggedInUser) {
        this.LoggedInUser = LoggedInUser;
    }
    
    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getFromUser() {
        return FromUser;
    }

    public void setFromUser(String FromUser) {
        this.FromUser = FromUser;
    }

    public Long getFromUserId() {
        return FromUserId;
    }

    public void setFromUserId(Long FromUserId) {
        this.FromUserId = FromUserId;
    }

    public Long getInReplyToStatusId() {
        return InReplyToStatusId;
    }

    public void setInReplyToStatusId(Long InReplyToStatusId) {
        this.InReplyToStatusId = InReplyToStatusId;
    }

    public Long getInReplyToUserId() {
        return InReplyToUserId;
    }

    public void setInReplyToUserId(Long InReplyToUserId) {
        this.InReplyToUserId = InReplyToUserId;
    }

    public TwitterProfile getUser() {
        return User;
    }

    public void setUser(TwitterProfile User) {
        this.User = User;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String LanguageCode) {
        this.LanguageCode = LanguageCode;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getInReplyToScreenName() {
        return InReplyToScreenName;
    }

    public void setInReplyToScreenName(String InReplyToScreenName) {
        this.InReplyToScreenName = InReplyToScreenName;
    }

    public String getProfileImageUrl() {
        return ProfileImageUrl;
    }

    public void setProfileImageUrl(String ProfileImageUrl) {
        this.ProfileImageUrl = ProfileImageUrl;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getUnmodifiedText() {
        return UnmodifiedText;
    }

    public void setUnmodifiedText(String UnmodifiedText) {
        this.UnmodifiedText = UnmodifiedText;
    }

   /* public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }*/

    public int getFavoriteCount() {
        return FavoriteCount;
    }

    public void setFavoriteCount(int FavoriteCount) {
        this.FavoriteCount = FavoriteCount;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public Boolean getHasMentions() {
        return hasMentions;
    }

    public void setHasMentions(Boolean hasMentions) {
        this.hasMentions = hasMentions;
    }

    public Boolean getHasTags() {
        return hasTags;
    }

    public void setHasTags(Boolean hasTags) {
        this.hasTags = hasTags;
    }

    public Boolean getHasUrls() {
        return hasUrls;
    }

    public void setHasUrls(Boolean hasUrls) {
        this.hasUrls = hasUrls;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public Boolean getIsFavorited() {
        return isFavorited;
    }

    public void setIsFavorited(Boolean isFavorited) {
        this.isFavorited = isFavorited;
    }

    public Boolean getIsRetweeted() {
        return isRetweeted;
    }

    public void setIsRetweeted(Boolean isRetweeted) {
        this.isRetweeted = isRetweeted;
    }

    public Boolean getIsRetweet() {
        return isRetweet;
    }

    public void setIsRetweet(Boolean isRetweet) {
        this.isRetweet = isRetweet;
    }

    public int getRetweetCount() {
        return RetweetCount;
    }

    public void setRetweetCount(int RetweetCount) {
        this.RetweetCount = RetweetCount;
    }

    @Override
    public String toString() {
        return "TweetData{" + "tweetId=" + tweetId + ", LoggedInUser=" + LoggedInUser + ", FromUser=" + FromUser + ", FromUserId=" + FromUserId + ", InReplyToStatusId=" + InReplyToStatusId + ", InReplyToUserId=" + InReplyToUserId + ", User=" + User + ", CreatedAt=" + CreatedAt + ", LanguageCode=" + LanguageCode + ", Text=" + Text + ", InReplyToScreenName=" + InReplyToScreenName + ", ProfileImageUrl=" + ProfileImageUrl + ", Source=" + Source + ", UnmodifiedText=" + UnmodifiedText /*+ ", entities=" + entities */+ ", FavoriteCount=" + FavoriteCount + ", hasMedia=" + hasMedia + ", hasMentions=" + hasMentions + ", hasTags=" + hasTags + ", hasUrls=" + hasUrls + ", hashCode=" + hashCode + ", isFavorited=" + isFavorited + ", isRetweeted=" + isRetweeted + ", isRetweet=" + isRetweet + ", RetweetCount=" + RetweetCount + '}';
    }
    
    
    
}
