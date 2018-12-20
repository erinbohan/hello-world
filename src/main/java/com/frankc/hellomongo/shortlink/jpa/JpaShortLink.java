package com.frankc.hellomongo.shortlink.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Profile;

import com.frankc.hellomongo.shortlink.ShortLink;

@Entity
@Profile("jpa")
public class JpaShortLink extends ShortLink {

    @Id
    private Long id;

    private String srcLink;

    public JpaShortLink() {
        System.out.println("JPA SHORT LINK EMPTY CONTRUCTOR");
    }

    public JpaShortLink(final String srcLink) {
        this.setSrcLink(srcLink);
    }

    public JpaShortLink(final String id, final String srcLink) {
        this.setId(id);
        this.setSrcLink(srcLink);
    }

    public String getId() {
        return id.toString();
    }

    public void setId(final String id) {
        this.id = Long.valueOf(id);
    }

    public String getSrcLink() {
        return srcLink;
    }

    public void setSrcLink(final String srcLink) {
        this.srcLink = srcLink;
    }
}
