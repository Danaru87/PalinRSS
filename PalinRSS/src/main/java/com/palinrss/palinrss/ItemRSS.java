package com.palinrss.palinrss;

/**
 * Created by danaru on 25/03/15.
 */
public class ItemRSS {
    public String title;
    public String link;
    public String description;

    public ItemRSS(String ptitle, String pDescription, String pLink)
    {
        title = ptitle;
        description = pDescription;
        link = pLink;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
