/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 810783
 */
@Entity
@Table(name = "notes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n")
    , @NamedQuery(name = "Notes.findByNoteid", query = "SELECT n FROM Notes n WHERE n.noteid = :noteid")
    , @NamedQuery(name = "Notes.findByDatecreated", query = "SELECT n FROM Notes n WHERE n.datecreated = :datecreated")
    , @NamedQuery(name = "Notes.findByTitle", query = "SELECT n FROM Notes n WHERE n.title = :title")
    , @NamedQuery(name = "Notes.findByContents", query = "SELECT n FROM Notes n WHERE n.contents = :contents")})
public class Notes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noteid")
    private Integer noteid;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "contents")
    private String contents;
    @JoinColumn(name = "owner", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users owner;

    public Notes() {
    }

    public Notes(Integer noteid) 
    {
        this.noteid = noteid;
    }

    public Notes(Integer noteid, Date datecreated, String title, String contents) 
    {
        this.noteid = noteid;
        this.datecreated = datecreated;
        this.title = title;
        this.contents = contents;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteid != null ? noteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notes)) {
            return false;
        }
        Notes other = (Notes) object;
        if ((this.noteid == null && other.noteid != null) || (this.noteid != null && !this.noteid.equals(other.noteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return "Note ID: " + this.noteid + "\n" +
               "Note title: " + this.title + "\n" + 
               "Note contents: " + this.contents + "\n";
    }
    
}
