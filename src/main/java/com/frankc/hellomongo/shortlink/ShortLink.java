package com.frankc.hellomongo.shortlink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ShortLink {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "PR_KEY", length = 100)
    private String id;

    private String srcLink;

    public ShortLink() {
        System.out.println("GENERIC SHORT LINK EMPTY CONTRUCTOR");
    }

    public ShortLink(final String srcLink) {
        this.setSrcLink(srcLink);
    }

    public ShortLink(final String id, final String srcLink) {
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
        return this.srcLink;
    }
    public void setSrcLink(final String srcLink) {
        this.srcLink = srcLink;
    }
}
