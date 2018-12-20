package com.frankc.hellomongo.shortlink.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;

import com.frankc.hellomongo.shortlink.ShortLink;

@Profile("mongo")
public class MongoShortLink extends ShortLink {

    @Id
    private String id;

    private String srcLink;

    public MongoShortLink() {
        System.out.println("MONGO SHORT LINK EMPTY CONTRUCTOR");
    }

    public MongoShortLink(final String srcLink) {
        this.setSrcLink(srcLink);
    }

    public MongoShortLink(final String id, final String srcLink) {
        this.setId(id);
        this.setSrcLink(srcLink);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getSrcLink() {
        return srcLink;
    }

    public void setSrcLink(final String srcLink) {
        this.srcLink = srcLink;
    }
}
